package com.sogou.upd.passport.admin.service.riskcontrol;

/**
 * Created with IntelliJ IDEA.
 * User: chengang
 * Date: 15-5-19
 * Time: 下午7:56
 */
public interface RiskControlService {


    /**
     * 解除封禁
     *
     * @param ip
     * @return
     */
    public boolean openControlDenyIp(String ip);
}
