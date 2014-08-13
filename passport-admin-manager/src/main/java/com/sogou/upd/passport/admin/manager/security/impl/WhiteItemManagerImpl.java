package com.sogou.upd.passport.admin.manager.security.impl;

import com.sogou.upd.passport.admin.manager.security.WhiteItemManager;
import com.sogou.upd.passport.common.CacheConstant;
import com.sogou.upd.passport.common.result.APIResultSupport;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.ErrorUtil;
import com.sogou.upd.passport.common.utils.RedisUtils;
import com.sogou.upd.passport.model.black.BlackItem;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 14-2-20
 * Time: 下午5:26
 * To change this template use File | Settings | File Templates.
 */
@Component
public class WhiteItemManagerImpl implements WhiteItemManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhiteItemManagerImpl.class);

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Set<String> queryLoginWhiteList() throws Exception {
        String whiteListKey = CacheConstant.CACHE_PREFIX_LOGIN_WHITELIST;
        return redisUtils.smember(whiteListKey);
    }

    @Override
    public Result delFromWhiteList(String ipOrUsername) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            redisUtils.srem(CacheConstant.CACHE_PREFIX_LOGIN_WHITELIST, ipOrUsername);
            result.setSuccess(true);
            result.setMessage("删除成功");
        } catch (Exception e) {
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }

    @Override
    public Result addToWhiteList(String ipOrUsername) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            redisUtils.sadd(CacheConstant.CACHE_PREFIX_LOGIN_WHITELIST, ipOrUsername);
            result.setSuccess(true);
            result.setMessage("添加成功");
        } catch (Exception e) {
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }


    @Override
    public Set<String> getWhiteListByName(String name) throws Exception {
        boolean res = redisUtils.isMember(CacheConstant.CACHE_PREFIX_LOGIN_WHITELIST, name);
        Set<String> set = null;
        if (res) {
            set.add(name);
        }
        return set;
    }

    /**
     * 根据缓存key 获取白名单
     *
     * @param cacheKey
     * @return
     * @throws Exception
     */
    @Override
    public Set<String> getWhiteList(String cacheKey) throws Exception {
        return redisUtils.smember(cacheKey);
    }

    /**
     * 添加相应业务白名单
     *
     * @param cacheKey
     * @param cacheValue
     * @return
     */
    @Override
    public Result addWhiteList(String cacheKey, String cacheValue) {
        Result result = new APIResultSupport(false);
        try {
            redisUtils.sadd(cacheKey, cacheValue);
            result.setSuccess(true);
            result.setMessage("添加成功");
        } catch (Exception e) {
            LOGGER.error("addWhiteList error. cacheKey:{},cacheValue:{}", cacheKey, cacheValue);
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }

    /**
     * 检查是否在白名单中
     *
     * @param cacheKey
     * @param cacheValue
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkWhiteListExist(String cacheKey, String cacheValue) throws Exception {
        return redisUtils.isMember(cacheKey, cacheValue);
    }
}
