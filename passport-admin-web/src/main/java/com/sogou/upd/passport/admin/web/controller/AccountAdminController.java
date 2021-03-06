package com.sogou.upd.passport.admin.web.controller;


import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sogou.upd.passport.admin.common.CommonConstant;
import com.sogou.upd.passport.admin.common.utils.IPUtil;
import com.sogou.upd.passport.admin.common.utils.RequestUtils;
import com.sogou.upd.passport.admin.common.utils.UuidUtil;
import com.sogou.upd.passport.admin.manager.accountAdmin.AccountAdminManager;
import com.sogou.upd.passport.admin.manager.form.AccountSearchParam;
import com.sogou.upd.passport.admin.manager.model.AccountDetailInfo;
import com.sogou.upd.passport.admin.web.BaseController;
import com.sogou.upd.passport.admin.web.util.UserOperationLogUtil;
import com.sogou.upd.passport.common.model.useroperationlog.UserOperationLog;
import com.sogou.upd.passport.common.parameter.AccountDomainEnum;
import com.sogou.upd.passport.common.result.APIResultSupport;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.ErrorUtil;
import com.sogou.upd.passport.dao.account.AccountDAO;
import com.sogou.upd.passport.dao.account.AccountInfoDAO;
import com.sogou.upd.passport.model.account.Account;
import com.sogou.upd.passport.model.account.AccountInfo;
import com.sogou.upd.passport.model.operatelog.OperateHistoryLog;
import com.sogou.upd.passport.service.account.AccountService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-5-27 Time: 下午7:37 To change this template
 * use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes("account")
public class AccountAdminController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AccountAdminController.class);

    @Autowired
    private AccountAdminManager accountAdminManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountInfoDAO accountInfoDAO;
//
//    @Autowired
//    private HttpServletRequest request;

    /**
     * 用户账号信息查询
     * <p/>
     * 查询条件：用户账号（手机号）或者用户昵称
     *
     * @param accountSearchParam
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/alterAccount/queryAccount", method = RequestMethod.POST)
    public String queryAccount(AccountSearchParam accountSearchParam, Model model) throws Exception {
        AccountDetailInfo account = accountAdminManager.getAccountDetailInfo(accountSearchParam);
        if (account == null) {
            model.addAttribute("exist", false);
            return "/pages/admin/account/accountAdmin.jsp";
        }
        model.addAttribute("account", account);
        return "/pages/admin/account/accountAdmin.jsp";
    }

    @RequestMapping(value = "/initsohu", method = RequestMethod.GET)
    @ResponseBody
    public String initSohuAccount(HttpServletRequest request, Model model, @RequestParam("sohuId") String sohuId) {
        try {
            //操作者ip
            String userIp = IPUtil.getIP(request);
            //操作者
            String operator = RequestUtils.getPassportEmail(request);

            if (!checkUserOrIpInWhiteList(operator, userIp)) {
                logger.warn("resetPwd user hasn't power operate! userIp:" + userIp);
                return CommonConstant.NO_OPERATE_POWER;
            }

            OperateHistoryLog operateHistoryLog = buildOperateHistoryLog(request, sohuId);

            AccountDomainEnum accountDomain = AccountDomainEnum.getAccountDomain(sohuId);
            if (AccountDomainEnum.SOHU != accountDomain) {
                return "not a sohu account.";
            }

            Account account = accountService.queryAccountByPassportId(sohuId);
            if (account != null) {
                return "account already exist";
            }

            Account newSohuAccount = new Account();
            newSohuAccount.setPasswordtype(5);
            newSohuAccount.setPassportId(sohuId);
            newSohuAccount.setFlag(1);
            newSohuAccount.setAccountType(9);

            String newPwd = UuidUtil.generatePassword();
            newSohuAccount.setPassword(newPwd);

            if (accountDAO.insertAccount(sohuId, newSohuAccount) > 0) {
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.setPassportId(sohuId);
                accountInfoDAO.insertAccountInfo(sohuId, accountInfo);
            } else {
                return "init account failed";
            }

            UserOperationLog userOperationLog = new UserOperationLog(sohuId, StringUtils.EMPTY, ErrorUtil.SUCCESS, userIp);
            userOperationLog.putOtherMessage("operator", operator);
            UserOperationLogUtil.log(userOperationLog);

            return "init success, new password: " + newPwd;
        } catch (Exception e) {
            logger.error("exception", e);
            return "execption occures";
        }
    }

    /**
     * 重置密码
     *
     * @param passportId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPwd(@RequestParam("passportId") String passportId, Model model, HttpServletRequest request) throws Exception {
        try {
            //操作者ip
            String userIp = IPUtil.getIP(request);
            //操作者
            String operator = RequestUtils.getPassportEmail(request);

            //TODO 安全起见 增加操作人IP白名单限制
//            if (!checkHasOperatePower(userIp)) {
//                logger.warn("resetPwd user hasn't power operate! userIp:" + userIp);
//                return CommonConstant.NO_OPERATE_POWER;
//            }


            if (!checkUserOrIpInWhiteList(operator, userIp)) {
                logger.warn("resetPwd user hasn't power operate! userIp:" + userIp);
                return CommonConstant.NO_OPERATE_POWER;
            }

            OperateHistoryLog operateHistoryLog = buildOperateHistoryLog(request, passportId);
            Result result = accountAdminManager.resetUserPassword(Boolean.TRUE, operateHistoryLog);
            if (result.isSuccess()) {
                model.addAttribute("newPwd", result.getModels().get("newPassword"));
                model.addAttribute("passportId", passportId);
            } else {
                if (result.getMessage().equals(CommonConstant.NOT_HANDLED_REST_PWD_FOR_SOHU)) {
                    model.addAttribute("msg", CommonConstant.NOT_HANDLED_REST_PWD_FOR_SOHU);
                    model.addAttribute("data", result.toString());
                }
            }

            UserOperationLog userOperationLog = new UserOperationLog(passportId, StringUtils.EMPTY, result.getCode(), userIp);
            userOperationLog.putOtherMessage("operator", operator);
            UserOperationLogUtil.log(userOperationLog);

        } catch (Exception e) {
            logger.error("resetPassword error.", e);
        }
        return "/pages/admin/account/resetPwd.jsp";
    }

    /**
     * 解除绑定
     *
     * @param passportId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unBind", method = RequestMethod.POST)
    public String unBind(@RequestParam("passportId") String passportId, Model model) throws Exception {
        try {

            //TODO 安全起见 增加操作人IP白名单限制

            AccountSearchParam accountSearchParam = new AccountSearchParam();
            accountSearchParam.setUserName(passportId);
            AccountDetailInfo account = accountAdminManager.getAccountDetailInfo(accountSearchParam);
            if (account == null) {
                model.addAttribute("exist", false);
                return "/pages/admin/account/unbind.jsp";
            }
            model.addAttribute("account", account);

//            UserOperationLog userOperationLog = new UserOperationLog(passportId, "", result.getCode(), IPUtil.getIP(request));
//            userOperationLog.putOtherMessage("operator", RequestUtils.getPassportEmail(request));
//            UserOperationLogUtil.log(userOperationLog);

        } catch (Exception e) {
            logger.error("unBind error.passportId:" + passportId, e);
        }
        return "/pages/admin/account/unbind.jsp";
    }


    /**
     * 解除绑定邮箱
     *
     * @param passportId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unBindEmail", method = RequestMethod.POST)
    @ResponseBody
    public Object unBindEmail(@RequestParam("passportId") String passportId, Model model, HttpServletRequest request) throws Exception {
        Result result = new APIResultSupport(false);
        try {

            //操作者ip
            String userIp = IPUtil.getIP(request);
            //操作者
            String operator = RequestUtils.getPassportEmail(request);
//            if (!checkHasOperatePower(userIp)) {
//                logger.warn(" unBindEmail user hasn't power operate! userIp:" + userIp);
//                result.setMessage(CommonConstant.NO_OPERATE_POWER);
//                return result;
//            }

            if (!checkUserOrIpInWhiteList(operator, userIp)) {
                logger.warn(" unBindEmail user hasn't power operate! userIp:" + userIp);
                result.setMessage(CommonConstant.NO_OPERATE_POWER);
                return result;
            }

            OperateHistoryLog operateHistoryLog = buildOperateHistoryLog(request, passportId);
            result = accountAdminManager.unbundlingEmail(operateHistoryLog);
            if (result.isSuccess()) {
                model.addAttribute("msg", CommonConstant.UN_BIND_SUCCESS);
            } else {
                model.addAttribute("msg", CommonConstant.UN_BIND_FAILURE);
            }
            model.addAttribute("passportId", passportId);

            UserOperationLog userOperationLog = new UserOperationLog(passportId, StringUtils.EMPTY, result.getCode(), userIp);
            userOperationLog.putOtherMessage("operator", operator);
            UserOperationLogUtil.log(userOperationLog);
        } catch (Exception e) {
            logger.error("unBindEmail error.", e);
        }
        return result.toString();
    }


    /**
     * 解除绑定手机
     *
     * @param passportId
     * @param mobile
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unBindPhone", method = RequestMethod.POST)
    @ResponseBody
    public Object unBindPhone(@RequestParam("passportId") String passportId, @RequestParam("mobile") String mobile, Model model, HttpServletRequest request) throws Exception {
        Result result = new APIResultSupport(false);
        try {

            //操作者ip
            String userIp = IPUtil.getIP(request);
            //操作者
            String operator = RequestUtils.getPassportEmail(request);
//            if (!checkHasOperatePower(userIp)) {
//                logger.warn(" unBindPhone user hasn't power operate! userIp:" + userIp);
//                result.setMessage(CommonConstant.NO_OPERATE_POWER);
//                return result;
//            }

            if (!checkUserOrIpInWhiteList(operator, userIp)) {
                logger.warn(" unBindPhone user hasn't power operate! userIp:" + userIp);
                result.setMessage(CommonConstant.NO_OPERATE_POWER);
                return result;
            }

            OperateHistoryLog operateHistoryLog = buildOperateHistoryLog(request, passportId);
            result = accountAdminManager.unbundlingMobile(mobile, operateHistoryLog);
            if (result.isSuccess()) {
                model.addAttribute("msg", CommonConstant.UN_BIND_SUCCESS);
            } else {
                model.addAttribute("msg", CommonConstant.UN_BIND_FAILURE);
            }
            model.addAttribute("passportId", passportId);

            UserOperationLog userOperationLog = new UserOperationLog(passportId, StringUtils.EMPTY, result.getCode(), userIp);
            userOperationLog.putOtherMessage("operator", operator);
            userOperationLog.putOtherMessage("mobile", mobile);
            UserOperationLogUtil.log(userOperationLog);

        } catch (Exception e) {
            logger.error("unBindEmail error.", e);
        }
        return result.toString();
    }


    /**
     * 批量解绑手机
     *
     * @param mobiles
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unBindPhones", method = RequestMethod.POST)
    public String unBindPhones(@RequestParam("mobiles") String mobiles, Model model, HttpServletRequest request) throws Exception {
        try {
            if (Strings.isNullOrEmpty(mobiles)) {
                model.addAttribute("msg", ErrorUtil.getERR_CODE_MSG_MAP().get("ERR_CODE_COM_REQURIE"));
                return "/pages/admin/account/unbindMobiles.jsp";
            }

            List<String> mobileList = Lists.newArrayList();
            String[] mobileArrays = StringUtils.split(mobiles, CommonConstant.COMMON_STRING_SPLIT_RETURN);
            if (mobileArrays.length > 0) {
                for (String mobile : mobileArrays) {
                    if (!Strings.isNullOrEmpty(mobile)) {
                        mobileList.add(mobile);
                    }
                }
            }
            Result result = accountAdminManager.unBindMobiles(mobileList);
            //解除绑定失败结果
            String failUnBind = StringUtils.EMPTY;
            if (result.isSuccess()) {
                failUnBind = String.valueOf(result.getModels().get("failed"));
            }
            model.addAttribute("msg", CommonConstant.DONE_UN_BIND_MOBILE);
            model.addAttribute("failed", failUnBind);

            UserOperationLog userOperationLog = new UserOperationLog(StringUtils.EMPTY, StringUtils.EMPTY, result.getCode(), IPUtil.getIP(request));
            userOperationLog.putOtherMessage("operator", RequestUtils.getPassportEmail(request));
            userOperationLog.putOtherMessage("unBindPhones", mobileList.toString());
            UserOperationLogUtil.log(userOperationLog);

        } catch (Exception e) {
            logger.error("unBindPhones error.", e);
        }
        return "/pages/admin/account/unbindMobiles.jsp";
    }


    /**
     * 批量删除注册手机号，仅供开发和测试同学使用，不对外开放
     *
     * @param mobiles
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteRegMobiles", method = RequestMethod.POST)
    public String deleteRegMobiles(@RequestParam("mobiles") String mobiles, Model model, HttpServletRequest request) throws Exception {
        try {
            if (Strings.isNullOrEmpty(mobiles)) {
                model.addAttribute("msg", ErrorUtil.getERR_CODE_MSG_MAP().get("ERR_CODE_COM_REQURIE"));
                return "/pages/admin/account/deleteRegMobiles.jsp";
            }

            //TODO 安全起见 增加手机号白名单限制，只是处理在白名单之内的手机号
            //TODO 安全起见 增加操作人IP白名单限制，待提供开发测试同学IP

            //操作者ip
            String userIp = IPUtil.getIP(request);
            //操作者
             String operator = RequestUtils.getPassportEmail(request);

             if (!checkUserOrIpInWhiteList(operator, userIp)) {
                 logger.warn("resetPwd user hasn't power operate! userIp:" + userIp);
                 model.addAttribute("msg", CommonConstant.NO_OPERATE_POWER);
                 return "/pages/admin/account/deleteRegMobiles.jsp";
             }

            List<String> mobileList = Lists.newArrayList();
            String[] mobileArrays = StringUtils.split(mobiles, CommonConstant.COMMON_STRING_SPLIT_RETURN);
            if (mobileArrays.length > 0) {
                for (String mobile : mobileArrays) {
                    if (!Strings.isNullOrEmpty(mobile)) {
                        mobileList.add(mobile.trim());
                    }
                }
            }
            Result result = accountAdminManager.deleteRegMobiles(mobileList);
            //解除绑定失败结果
            String failUnBind = StringUtils.EMPTY;
            if (result.isSuccess()) {
                failUnBind = String.valueOf(result.getModels().get("failed"));
            }
            model.addAttribute("msg", CommonConstant.DONE_UN_BIND_MOBILE);
            model.addAttribute("failed", failUnBind);

            UserOperationLog userOperationLog = new UserOperationLog(StringUtils.EMPTY, StringUtils.EMPTY, result.getCode(), userIp);
            userOperationLog.putOtherMessage("operator", operator);
            userOperationLog.putOtherMessage("deleteMobiles", mobileList.toString());
            UserOperationLogUtil.log(userOperationLog);

        } catch (Exception e) {
            logger.error("deleteRegMobiles error.", e);
        }
        return "/pages/admin/account/deleteRegMobiles.jsp";
    }

    /**
     * 删除账号，仅供开发和测试同学使用，不对外开放
     *
     * @param passportid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAccount(HttpServletRequest request, @RequestParam("passportid") String passportid) {
        try {
            //操作者ip
            String userIp = IPUtil.getIP(request);
            //操作者
            String operator = RequestUtils.getPassportEmail(request);

            if (!checkUserOrIpInWhiteList(operator, userIp)) {
                logger.warn("deleteAccount user hasn't power operate! userIp:" + userIp);
                return CommonConstant.NO_OPERATE_POWER;
            }

            if (!accountService.deleteAccountByPassportId(passportid)) {
                return "delete account failed";
            }

            UserOperationLog userOperationLog = new UserOperationLog(passportid, StringUtils.EMPTY, ErrorUtil.SUCCESS, userIp);
            userOperationLog.putOtherMessage("operator", operator);
            UserOperationLogUtil.log(userOperationLog);
            return "delete account success";
        }
        catch (Exception e) {
            logger.error("deleteAccount error.", e);
            return "execption occures";
        }
    }

    /**
     * 解除封禁
     *
     * @param account
     * @param newState
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/alterAccount/updateState", method = RequestMethod.POST)
    public String updateState(@ModelAttribute("account") Account account, @RequestParam("newState") int newState, Model model) throws Exception {
        if (account == null) {
            model.addAttribute("exist", false);
            return "/pages/admin/account/accountAdmin.jsp";
        }
        boolean result = accountAdminManager.updateState(account, newState);
        if (result) {
            model.addAttribute("msg", "解/封禁成功！");
        } else {
            model.addAttribute("msg", "解/封禁失败！");
        }
        return "/pages/admin/account/accountAdmin.jsp";
    }
}
