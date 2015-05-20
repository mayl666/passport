package com.sogou.upd.passport.admin.manager.riskcontrol;

/**
 * Created with IntelliJ IDEA.
 * User: chengang
 * Date: 15-5-19
 * Time: 下午8:00
 */
public interface RiskControlManager {


    /**
     * 风控 解除封禁
     *
     * @param ip
     * @return
     */
    public boolean openRiskControl(String ip);
}
