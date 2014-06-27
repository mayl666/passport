package com.sogou.upd.passport.admin.manager.accountAdmin;

import com.sogou.upd.passport.admin.manager.model.AccountDetailInfo;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.model.account.Account;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-14
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public interface AccountAdminManager {
    public Account queryAccountByUserName(String username);

    public boolean updateState(Account account, int newState);

    public boolean resetPassword(Account account, String password, boolean needMD5);


    /**
     * 根据用户名获取用户账号信息
     *
     * @param username
     * @return
     */
    public AccountDetailInfo getAccountDetailInfo(String username);


    /**
     * 重置用户密码
     *
     * @param passportId
     * @param needMD5
     * @return
     */
    public Result resetUserPassword(String passportId, boolean needMD5);

    /**
     * 解绑绑定手机
     *
     * @param passportId
     * @param mobile
     * @return
     */
    public Result unbundlingMobile(String passportId, String mobile);

    /**
     * 解绑绑定邮箱
     *
     * @param passportId
     * @return
     */
    public Result unbundlingEmail(String passportId);


    /**
     * 批量解除手机绑定
     *
     * @param mobileList
     * @return
     */
    public Result unBindMobiles(List<String> mobileList);


}
