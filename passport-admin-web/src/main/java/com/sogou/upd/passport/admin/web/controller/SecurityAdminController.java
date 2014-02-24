package com.sogou.upd.passport.admin.web.controller;


import com.google.common.base.Strings;
import com.sogou.upd.passport.admin.manager.ProblemVO.ProblemVOManager;
import com.sogou.upd.passport.admin.manager.form.BlackItemQueryParam;
import com.sogou.upd.passport.admin.manager.form.ProblemQueryParam;
import com.sogou.upd.passport.admin.manager.problem.ProblemAnswerManager;
import com.sogou.upd.passport.admin.manager.problem.ProblemManager;
import com.sogou.upd.passport.admin.manager.problem.ProblemTypeManager;
import com.sogou.upd.passport.admin.manager.security.BlackItemManager;
import com.sogou.upd.passport.admin.manager.security.WhiteItemManager;
import com.sogou.upd.passport.admin.model.config.InterfaceLevelMapping;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.admin.model.problemVO.ProblemVO;
import com.sogou.upd.passport.admin.web.BaseController;
import com.sogou.upd.passport.common.result.APIResultSupport;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.DateUtil;
import com.sogou.upd.passport.model.black.BlackItem;
import com.sogou.upd.passport.model.problem.ProblemType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-5-27 Time: 下午7:37 To change this template
 * use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
public class SecurityAdminController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityAdminController.class);

    private static final Integer PAGE_SIZE = 15;

    @Autowired
    private BlackItemManager blackItemManager;
    @Autowired
    private WhiteItemManager whiteItemManager;

    @RequestMapping(value = "/security/currentLoginBlackIplist")
    public String queryBlackIpList(Model model) throws Exception {
        List<BlackItem> loginBlackIpList = null;
        try {
            loginBlackIpList = blackItemManager.queryCurrentLoginBlackIpList();
        } catch (Exception e) {
            logger.error("queryBlackIpList error:", e);
        }
        model.addAttribute("loginBlackIpList", loginBlackIpList);
        return "/pages/admin/security/loginIpBlackListAdmin.jsp";
    }

    @RequestMapping(value = "/security/getLoginBlackIpByName", method = RequestMethod.POST)
    public String getLoginBlackIpByName(Model model,@RequestParam("ipStr") String ipStr) throws Exception {
        List<BlackItem> loginBlackIpList = null;
        try {
            loginBlackIpList = blackItemManager.getCurrentLoginBlackIpByName(ipStr);
        } catch (Exception e) {
            logger.error("queryBlackIpList error:", e);
        }
        model.addAttribute("loginBlackIpList", loginBlackIpList);

        if(loginBlackIpList == null || loginBlackIpList.size() ==0 ){
            model.addAttribute("curIpExist", false);
        }
        return "/pages/admin/security/loginIpBlackListAdmin.jsp";
    }

    @RequestMapping(value = "/security/delLoginBlackIpByName", method = RequestMethod.POST)
    @ResponseBody
    public Object delLoginBlackUserByName(Model model,@RequestParam("ipStr") String ipStr,@RequestParam("id") Long id) throws Exception {
        Result result = new APIResultSupport(false);
        result = blackItemManager.delIpFromCurLoginListByName(ipStr, id);
        return result.toString();
    }

    @RequestMapping(value = "/security/addLoginBlackIp", method = RequestMethod.POST)
    @ResponseBody
    public Object addLoginBlackIp(Model model,@RequestParam("ipStr") String ipStr) throws Exception {
        Result result = new APIResultSupport(false);
        result = blackItemManager.addIpOrUserToLoginBlack(ipStr,true);
        return result.toString();
    }

    @RequestMapping(value = "/security/addLoginBlackUser", method = RequestMethod.POST)
    @ResponseBody
    public Object addLoginBlackUser(Model model,@RequestParam("userName") String userName) throws Exception {
        Result result = new APIResultSupport(false);
        result = blackItemManager.addIpOrUserToLoginBlack(userName,false);
        return result.toString();
    }

    @RequestMapping(value = "/security/currentLoginBlackUserlist")
    public String queryBlackUserList(Model model) throws Exception {
        List<BlackItem> loginBlackUserList = null;
        try {
            loginBlackUserList = blackItemManager.queryCurrentLoginBlackUserList();
        } catch (Exception e) {
            logger.error("queryBlackIpList error:", e);
        }
        model.addAttribute("loginBlackIpList", loginBlackUserList);
        return "/pages/admin/security/loginUsernameBlackListAdmin.jsp";
    }

    @RequestMapping(value = "/security/getLoginBlackUserByName", method = RequestMethod.POST)
    public String getLoginBlackUserByName(Model model,@RequestParam("userName") String userName) throws Exception {
        List<BlackItem> loginBlackUserList = null;
        try {
            loginBlackUserList = blackItemManager.getCurrentLoginBlackNameByName(userName);
        } catch (Exception e) {
            logger.error("queryBlackIpList error:", e);
        }
        model.addAttribute("loginBlackIpList", loginBlackUserList);

        if(loginBlackUserList == null || loginBlackUserList.size() ==0 ){
            model.addAttribute("curUserExist", false);
        }
        return "/pages/admin/security/loginUsernameBlackListAdmin.jsp";
    }

    @RequestMapping(value = "/security/delLoginBlackUserByName", method = RequestMethod.POST)
    @ResponseBody
    public Object delLoginBlackIpByName(Model model,@RequestParam("userName") String userName,@RequestParam("id") Long id) throws Exception {
        Result result = new APIResultSupport(false);
        result = blackItemManager.delUserFromCurLoginListByName(userName, id);
        return result.toString();
    }

    @RequestMapping(value = "/security/loginWhiteList")
    public String queryLoginWhiteList(Model model) throws Exception {
        Set<String> loginWhiteList = null;
        try {
            loginWhiteList = whiteItemManager.queryLoginWhiteList();
        } catch (Exception e) {
            logger.error("queryLoginWhiteList error:", e);
        }
        model.addAttribute("loginWhiteList", loginWhiteList);
        return "/pages/admin/security/loginWhiteListAdmin.jsp";
    }

    @RequestMapping(value = "/security/delLoginWhiteItem", method = RequestMethod.POST)
    @ResponseBody
    public Object delLoginWhiteItem(Model model,@RequestParam("ipOrUsername") String ipOrUsername) throws Exception {
        Result result = new APIResultSupport(false);
        result =whiteItemManager.delFromWhiteList(ipOrUsername);
        String a =new String();
        return result.toString();
    }
    @RequestMapping(value = "/security/addWhiteItem", method = RequestMethod.POST)
    @ResponseBody
    public Object addWhiteItem(Model model,@RequestParam("ipOrUsername") String ipOrUsername) throws Exception {
        Result result = new APIResultSupport(false);
        result = whiteItemManager.addToWhiteList(ipOrUsername);
        return result.toString();
    }

    @RequestMapping(value = "/security/getWhiteItemByName", method = RequestMethod.POST)
    public String getWhiteListByName(Model model,@RequestParam("ipOrUsername") String ipOrUsername) throws Exception {
        Set<String> loginWhiteList = null;
        try {
            loginWhiteList = whiteItemManager.getWhiteListByName(ipOrUsername);
        } catch (Exception e) {
            logger.error("queryLoginWhiteList error:", e);
        }
        model.addAttribute("loginWhiteList", loginWhiteList);

        if(loginWhiteList == null || loginWhiteList.size() ==0 ){
            model.addAttribute("exist", false);
        }
        return "/pages/admin/security/loginWhiteListAdmin.jsp";

    }


    @RequestMapping(value = "/security/queryHistroyLoginBlackItem", method = RequestMethod.POST)
    public String queryProblem(BlackItemQueryParam blackItemQueryParam,Model model) throws Exception {
        Integer sort = null, type = null,queryStart = null, queryEnd = null;
        Double maxDuration = null,minDuration = null;
        Date startDate = null, endDate = null;
        String name=null;
        if (blackItemQueryParam.getSortId() >=0) {
            sort = blackItemQueryParam.getSortId();
        }
        if (blackItemQueryParam.getTypeId() >=0) {
            type = blackItemQueryParam.getTypeId();
        }
        if ((!Strings.isNullOrEmpty(blackItemQueryParam.getStartDateStr())
                && (!Strings.isNullOrEmpty(blackItemQueryParam.getEndDateStr())))) {
            startDate = DateUtil.parse(blackItemQueryParam.getStartDateStr(), DateUtil.DATE_FMT_3);
            endDate = DateUtil.parse(blackItemQueryParam.getEndDateStr(), DateUtil.DATE_FMT_3);
        }
        if (blackItemQueryParam.getMaxDuration() != null) {
            maxDuration = blackItemQueryParam.getMaxDuration();
        }
        if (blackItemQueryParam.getMinDuration() != null) {
            minDuration = blackItemQueryParam.getMinDuration();
        }
        if(!StringUtils.isBlank(blackItemQueryParam.getName())){
            name = blackItemQueryParam.getName();
        }
        int currentPage = 1;
        if (blackItemQueryParam.getPageNum() > 1) {
            queryStart = (blackItemQueryParam.getPageNum() - 1) * PAGE_SIZE ;
            currentPage =  blackItemQueryParam.getPageNum();
        } else {
            queryStart = 0;
        }
        queryEnd = PAGE_SIZE;

        List<BlackItem> loginBlackIpList = null;
        try {
            loginBlackIpList = blackItemManager.getBlackList(startDate,endDate,sort,name,type,minDuration,maxDuration,BlackItem.SCOPE_LOGIN,queryStart,queryEnd);
        } catch (Exception e) {
            logger.error("queryBlackIpList error:", e);
        }

        int count = 0;
        if(loginBlackIpList != null && loginBlackIpList.size() > 0){
            count = loginBlackIpList.size();
        }
        int totalPageNum = count/PAGE_SIZE;

        if(totalPageNum%PAGE_SIZE !=0){
            totalPageNum ++;
        }
        if(totalPageNum <=0){
            totalPageNum = 1;
        }

        model.addAttribute("sortId",blackItemQueryParam.getSortId());
        model.addAttribute("typeId",blackItemQueryParam.getTypeId());
        model.addAttribute("startDateStr",blackItemQueryParam.getStartDateStr());
        model.addAttribute("endDateStr",blackItemQueryParam.getEndDateStr());
        model.addAttribute("maxDuration",blackItemQueryParam.getMaxDuration());
        model.addAttribute("minDuration",blackItemQueryParam.getMinDuration());
        model.addAttribute("name",blackItemQueryParam.getName());


        model.addAttribute("totalPageNum",totalPageNum);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("loginBlackIpList", loginBlackIpList);
        return "/pages/admin/security/historyLoginblackListAdmin.jsp";

        }

    @RequestMapping(value = "/security/delLoginBlackItemById", method = RequestMethod.POST)
    @ResponseBody
    public Object delLoginBlackItemById(Model model,@RequestParam("id") Long id) throws Exception {
        Result result = new APIResultSupport(false);
        result = blackItemManager.delBlackItem(id);
        return result.toString();
    }

    }