package com.sogou.upd.passport.admin.manager.operate;

/**
 * Created with IntelliJ IDEA.
 * User: chengang
 * Date: 14-10-24
 * Time: 上午11:38
 */
public interface OperateManager {

    /**
     * 检查用户名或者用户ip是否在白名单中
     *
     * @param username
     * @param loginIp
     * @return
     */
    public boolean checkUserOrIpInWhiteList(String username, String loginIp);
}
