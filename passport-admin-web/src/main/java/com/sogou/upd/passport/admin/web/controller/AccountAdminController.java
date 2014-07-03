package com.sogou.upd.passport.admin.web.controller;


import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sogou.upd.passport.admin.common.CommonConstant;
import com.sogou.upd.passport.admin.manager.accountAdmin.AccountAdminManager;
import com.sogou.upd.passport.admin.manager.model.AccountDetailInfo;
import com.sogou.upd.passport.admin.web.BaseController;
import com.sogou.upd.passport.common.result.APIResultSupport;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.ErrorUtil;
import com.sogou.upd.passport.model.account.Account;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 用户账号信息查询
     *
     * @param username
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/alterAccount/queryAccount", method = RequestMethod.POST)
    public String queryAccount(@RequestParam("username") String username, Model model) throws Exception {
        AccountDetailInfo account = accountAdminManager.getAccountDetailInfo(username);
        if (account == null) {
            model.addAttribute("exist", false);
            return "/pages/admin/account/accountAdmin.jsp";
        }
        model.addAttribute("account", account);
        return "/pages/admin/account/accountAdmin.jsp";
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
    public String resetPwd(@RequestParam("passportId") String passportId, Model model) throws Exception {
        try {
            Result result = accountAdminManager.resetUserPassword(passportId, true);
            if (result.isSuccess()) {
                model.addAttribute("newPwd", result.getModels().get("newPassword"));
                model.addAttribute("passportId", passportId);
            }
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
            AccountDetailInfo account = accountAdminManager.getAccountDetailInfo(passportId);
            if (account == null) {
                model.addAttribute("exist", false);
                return "/pages/admin/account/unbind.jsp";
            }
            model.addAttribute("account", account);

        } catch (Exception e) {
            logger.error("unBind error.", e);
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
    public Object unBindEmail(@RequestParam("passportId") String passportId, Model model) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            result = accountAdminManager.unbundlingEmail(passportId);
            if (result.isSuccess()) {
                model.addAttribute("msg", CommonConstant.UN_BIND_SUCCESS);
            } else {
                model.addAttribute("msg", CommonConstant.UN_BIND_FAILURE);
            }
            model.addAttribute("passportId", passportId);
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
    public Object unBindPhone(@RequestParam("passportId") String passportId, @RequestParam("mobile") String mobile, Model model) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            result = accountAdminManager.unbundlingMobile(passportId, mobile);
            if (result.isSuccess()) {
                model.addAttribute("msg", CommonConstant.UN_BIND_SUCCESS);
            } else {
                model.addAttribute("msg", CommonConstant.UN_BIND_FAILURE);
            }
            model.addAttribute("passportId", passportId);
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
    public String unBindPhones(@RequestParam("mobiles") String mobiles, Model model) throws Exception {
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
        } catch (Exception e) {
            logger.error("unBindPhones error.", e);
        }
        return "/pages/admin/account/unbindMobiles.jsp";
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
