package com.sogou.upd.passport.admin.manager.blackList.impl;

import com.sogou.upd.passport.admin.common.CommonConstant;
import com.sogou.upd.passport.admin.common.model.Page;
import com.sogou.upd.passport.admin.manager.accountAdmin.AccountAdminManager;
import com.sogou.upd.passport.admin.manager.blackList.BlackListManager;
import com.sogou.upd.passport.admin.model.blackList.BlackList;
import com.sogou.upd.passport.admin.service.blacklist.BlackListService;
import com.sogou.upd.passport.common.CacheConstant;
import com.sogou.upd.passport.common.parameter.AccountDomainEnum;
import com.sogou.upd.passport.common.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-7
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BlackListManagerImpl implements BlackListManager {

    private static Logger logger = LoggerFactory.getLogger(BlackListManagerImpl.class);
    @Autowired
    private BlackListService blackListService;

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public boolean insertBlackList(String passportId, Long expire_time) throws Exception {
        BlackList tmp = blackListService.getBlackListByUserID(passportId);
        if (tmp != null) {
            return false;
        } else {
            BlackList blackList = new BlackList();
            int accounttype = AccountDomainEnum.getAccountDomain(passportId).getValue();
            blackList.setUserid(passportId);
            blackList.setAccount_type(accounttype);
            blackList.setUpdate_time(null);
            blackList.setExpire_time(expire_time);
            redisUtils.sadd(CommonConstant.CACHE_KEY_BLACKLIST, blackList.getUserid() + "_" + blackList.getExpire_time());
            blackListService.insertBlackList(blackList);
            return true;
        }
    }

    @Override
    public Page<BlackList> getBlackList(String userid, int pageNo, int pageSize) throws Exception {
        blackListService.updateBlackListExpire();
        Page<BlackList> page = new Page<BlackList>(pageNo, pageSize);
        List<BlackList> list = blackListService.getBlackList(userid, pageSize * (pageNo - 1), pageSize);
        int total = blackListService.getBlackListCount(userid);
        page.setTotal(total);
        page.setItems(list);
        return page;
    }

    @Override
    public boolean updateBlackListStatus(String id, boolean status) throws Exception {
        BlackList tmp = new BlackList();
        tmp.setId(Integer.valueOf(id));
        tmp.setUpdate_time(new Date());
        tmp.setStatus(status);
        int i = blackListService.updateBlackList(tmp);
        if (i > 0) {
            redisUtils.delete(CommonConstant.CACHE_KEY_BLACKLIST);
            List<BlackList> list = blackListService.getBlackListByValid();
            for (BlackList temp : list) {
                redisUtils.sadd(CommonConstant.CACHE_KEY_BLACKLIST, temp.getUserid() + "_" + temp.getExpire_time());
            }
            return true;
        } else
            return false;
    }

    public boolean isExpire(String id) throws Exception {
        BlackList tmp = blackListService.getBlackListByID(id);
        if (tmp.getExpire_time() < System.currentTimeMillis() / 1000) {
            return false;
        }
        return true;
    }

}
