package com.sogou.upd.passport.admin.manager.blackList;

import com.sogou.upd.passport.admin.common.model.Page;
import com.sogou.upd.passport.admin.model.blackList.BlackList;
import com.sogou.upd.passport.common.result.Result;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-7
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
public interface BlackListManager {

    /**
     * 新增黑名单
     * @param passportId
     * @param expire_time
     * @return
     * @throws Exception
     */
    public boolean insertBlackList(String passportId,Long expire_time) throws Exception;

    /**
     * 获取黑名单列表（模糊查询）
     * @param userid
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public Page<BlackList> getBlackList(String userid, int start, int end) throws Exception;

    /**
     * 更新黑名单
     * @param passportId 用户passportId
     * @param status 状态
     * @return
     * @throws Exception
     */
    public boolean updateBlackListStatus(String passportId,boolean status) throws Exception;

}
