package com.sogou.upd.passport.admin.manager.accountAdmin;

import com.sogou.upd.passport.admin.manager.form.AccountSearchParam;
import com.sogou.upd.passport.admin.manager.model.AccountDetailInfo;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.model.account.Account;
import com.sogou.upd.passport.model.operatelog.OperateHistoryLog;

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
     * @param accountSearchParam
     * @return
     */
    public AccountDetailInfo getAccountDetailInfo(AccountSearchParam accountSearchParam);


    /**
     * 重置用户密码
     *
     * @param needMD5
     * @param operateHistoryLog 操作历史记录
     * @return
     */
    public Result resetUserPassword(boolean needMD5, OperateHistoryLog operateHistoryLog);

    /**
     * 解绑绑定手机
     *
     * @param mobile
     * @param operateHistoryLog
     * @return
     */
    public Result unbundlingMobile(String mobile, OperateHistoryLog operateHistoryLog);

    /**
     * 解绑绑定邮箱
     *
     * @param operateHistoryLog
     * @return
     */
    public Result unbundlingEmail(OperateHistoryLog operateHistoryLog);


    /**
     * 批量解除手机绑定
     *
     * @param mobileList
     * @return
     */
    public Result unBindMobiles(List<String> mobileList);


    /**
     * 批量删除注册手机号
     *
     * @param regMobileList
     * @return
     */
    public Result deleteRegMobiles(List<String> regMobileList);


}
