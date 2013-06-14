package com.sogou.upd.passport.admin.manager.accountAdmin.impl;

import com.sogou.upd.passport.admin.manager.accountAdmin.AccountAdminManager;
import com.sogou.upd.passport.common.parameter.AccountDomainEnum;
import com.sogou.upd.passport.model.account.Account;
import com.sogou.upd.passport.service.account.AccountService;
import com.sogou.upd.passport.service.account.MobilePassportMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-14
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AccountAdminManagerImpl implements AccountAdminManager {
    private static final Logger logger = LoggerFactory.getLogger(AccountAdminManagerImpl.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private MobilePassportMappingService mobilePassportMappingService;

    @Override
    public Account queryAccountByUserName(String username) {
        try {
            String passportId = username;
            //判断登录用户类型
            AccountDomainEnum accountDomainEnum = AccountDomainEnum.getAccountDomain(username);
            //默认是sogou.com
            if (AccountDomainEnum.PHONE.equals(accountDomainEnum)) {
                passportId = mobilePassportMappingService.queryPassportIdByUsername(username);
            } else if (AccountDomainEnum.UNKNOWN.equals(accountDomainEnum)) {
                passportId = passportId + "@sogou.com";
            }

            return accountService.queryAccountByPassportId(passportId);
        } catch (Exception e) {
            logger.error("queryAccountByUserName fail,userId:" + username, e);
            return null;
        }
    }

    @Override
    public boolean updateState(Account account, int newState) {
        try {
            return accountService.updateState(account, newState);
        } catch (Exception e) {
            logger.error("updateState fail,userId:" + account.getPassportId(), e);
            return false;
        }
    }

    @Override
    public boolean resetPassword(Account account, String password, boolean needMD5) {
        try {
            return accountService.resetPassword(account, password, needMD5);
        } catch (Exception e) {
            logger.error("resetPassword fail,userId:" + account.getPassportId(), e);
            return false;
        }
    }
}
