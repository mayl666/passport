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
     * @return
     * @throws Exception
     */
    public Set<String> queryLoginWhiteList() throws Exception;

    /**
     * 从白名单中删除
     * @param ipOrUsername
     * @return
     * @throws Exception
     */
    public Result delFromWhiteList(String ipOrUsername) throws Exception;

    /**
     * 增加ipOrUsername到白名单列表
     * @param ipOrUsername
     * @return
     * @throws Exception
     */
    public Result addToWhiteList(String ipOrUsername) throws Exception;

    /**
     * 根据name取白名单列表
     * @param name
     * @return
     * @throws Exception
     */
    public Set<String> getWhiteListByName(String name) throws Exception;
}
