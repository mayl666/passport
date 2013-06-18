package com.sogou.upd.passport.admin.web.controller;


import com.sogou.upd.passport.admin.manager.accountAdmin.AccountAdminManager;
import com.sogou.upd.passport.admin.web.BaseController;
import com.sogou.upd.passport.model.account.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
  /*
   后台账号查询页面
  */
  @RequestMapping(value ="/alterAccount/queryAccount", method = RequestMethod.POST)
  public String queryAccount(@RequestParam("username") String username,Model model) throws Exception {
    Account account =  accountAdminManager.queryAccountByUserName(username);
    if (account == null){
      model.addAttribute("exist", false);
      return "/pages/admin/account/accountAdmin.jsp";
    }
    model.addAttribute("account", account);
    return "/pages/admin/account/accountAdmin.jsp";
  }

  /*
  解/封禁
  */
  @RequestMapping(value ="/alterAccount/updateState", method = RequestMethod.POST)
  public String updateState(@ModelAttribute("account")  Account account,@RequestParam("newState") int newState,Model model) throws Exception {
    if (account == null){
      model.addAttribute("exist", false);
      return "/pages/admin/account/accountAdmin.jsp";
    }
    boolean result = accountAdminManager.updateState(account,newState);
    if (result){
      model.addAttribute("msg", "解/封禁成功！");
    }else {
      model.addAttribute("msg", "解/封禁失败！");
    }
    return "/pages/admin/account/accountAdmin.jsp";
  }

  /*
   重置密码
  */
  @RequestMapping(value ="/alterAccount/resetPassword", method = RequestMethod.POST)
  public String resetPassword(@ModelAttribute("account")  Account account,@RequestParam("newPasswd") String newPasswd,Model model) throws Exception {
    if (account == null){
      model.addAttribute("exist", false);
      return "/pages/admin/account/accountAdmin.jsp";
    }
    boolean result = accountAdminManager.resetPassword(account,newPasswd,true);
    if (result){
      model.addAttribute("msg", "重置密码成功！");
    }else {
      model.addAttribute("msg", "重置密码失败！");
    }
    return "/pages/admin/account/accountAdmin.jsp";
  }
}
