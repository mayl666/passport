package com.sogou.upd.passport.admin.manager.security;

import com.sogou.upd.passport.common.result.Result;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 14-2-20
 * Time: 下午5:25
 * To change this template use File | Settings | File Templates.
 */
public interface WhiteItemManager {

    /**
     * 遍历白名单Set
     *
     * @return
     * @throws Exception
     */
    public Set<String> queryLoginWhiteList() throws Exception;

    /**
     * 从白名单中删除
     *
     * @param ipOrUsername
     * @return
     * @throws Exception
     */
    public Result delFromWhiteList(String ipOrUsername) throws Exception;

    /**
     * 增加ipOrUsername到白名单列表
     *
     * @param ipOrUsername
     * @return
     * @throws Exception
     */
    public Result addToWhiteList(String ipOrUsername) throws Exception;

    /**
     * 根据name取白名单列表
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Set<String> getWhiteListByName(String name) throws Exception;


    /**
     * 根据缓存key 获取对应的白名单列表
     *
     * @param cacheKey
     * @return
     * @throws Exception
     */
    public Set<String> getWhiteList(String cacheKey) throws Exception;

    /**
     * 增加相应业务的白名单
     *
     * @param cacheKey
     * @param cacheValue
     * @return
     */
    public Result addWhiteList(String cacheKey, String cacheValue);


    /**
     * 检查白名单中是否存在
     *
     * @param cacheKey
     * @param cacheValue
     * @return
     * @throws Exception
     */
    public boolean checkWhiteListExist(String cacheKey, String cacheValue) throws Exception;


}
