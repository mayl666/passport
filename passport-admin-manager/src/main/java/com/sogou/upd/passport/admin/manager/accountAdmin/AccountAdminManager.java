package com.sogou.upd.passport.admin.manager.accountAdmin;

import com.sogou.upd.passport.model.account.Account;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-14
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public interface AccountAdminManager {
    public Account queryAccountByUserName(String username);

    public boolean updateState(Account account,int newState);

    public boolean resetPassword(Account account, String password, boolean needMD5);
}
