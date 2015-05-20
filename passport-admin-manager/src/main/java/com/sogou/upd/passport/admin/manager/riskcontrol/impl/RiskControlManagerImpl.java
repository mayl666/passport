package com.sogou.upd.passport.admin.manager.riskcontrol.impl;

import com.sogou.upd.passport.admin.manager.riskcontrol.RiskControlManager;
import com.sogou.upd.passport.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 风险控制，解除封禁
 * User: chengang
 * Date: 15-5-19
 * Time: 下午8:01
 */
public class RiskControlManagerImpl implements RiskControlManager {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean openRiskControl(String ip) {
        return false;
    }
}
