package com.sogou.upd.passport.admin.manager.operate.impl;

import com.sogou.upd.passport.admin.manager.operate.OperateManager;
import com.sogou.upd.passport.service.account.OperateTimesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户操作manager实现
 * User: chengang
 * Date: 14-10-24
 * Time: 上午11:41
 */
public class OperateManagerImpl implements OperateManager {

    @Autowired
    private OperateTimesService operateTimesService;

    @Override
    public boolean checkUserOrIpInWhiteList(String username, String loginIp) {
        return operateTimesService.checkLoginUserInWhiteList(username, loginIp);
    }
}
