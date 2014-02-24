package com.sogou.upd.passport.admin.manager.security;

import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.model.black.BlackItem;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-8-9
 * Time: 上午10:47
 * To change this template use File | Settings | File Templates.
 */
public interface BlackItemManager {

    /**
     * 查询当前黑名单列表
     * @return
     * @throws Exception
     */
    public List<BlackItem> queryCurrentLoginBlackIpList() throws Exception;

    /**
     * 查询当前黑名单用户列表
     * @return
     * @throws Exception
     */
    public List<BlackItem> queryCurrentLoginBlackUserList() throws Exception;

    /**
     * 根据ip获取当前黑名单IP
     * @param name
     * @return
     * @throws Exception
     */
    public List<BlackItem> getCurrentLoginBlackIpByName(String name) throws Exception;

    /**
     * 根据username获取当前黑名单username
     * @param name
     * @return
     * @throws Exception
     */
    public List<BlackItem>  getCurrentLoginBlackNameByName(String name) throws Exception;

    /**
     * 将ip从当前黑名中移除
     * @param ip
     * @param id
     * @return
     * @throws Exception
     */
    public Result delIpFromCurLoginListByName(String ip,long id) throws Exception;

    /**
     * 将username从当前黑名单中移除
     * @param username
     * @param id
     * @return
     * @throws Exception
     */
    public Result delUserFromCurLoginListByName(String username,long id) throws Exception;

    /**
     * 根据id删除黑名单项
     * @param id
     * @return
     * @throws Exception
     */
    public int delCurLoginBlackItemById(long id) throws Exception;

    /**
     * 将ip或者user加入到黑名单中去
     * @param ipOrUser
     * @param isIp
     * @return
     * @throws Exception
     */
    public Result addIpOrUserToLoginBlack(String ipOrUser,boolean isIp) throws Exception;

    /**
     * 根据查询条件查询历史黑名单
     * @param startDate
     * @param endDate
     * @param sort
     * @param name
     * @param flag_success_limit
     * @param minTime
     * @param maxTime
     * @param scope
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public List<BlackItem>  getBlackList(Date startDate,Date endDate,Integer sort,
                                         String name,Integer flag_success_limit,
                                         Double minTime,Double maxTime,
                                         Integer scope,Integer start,Integer end) throws Exception;

    /**
     * 根据查询条件查询历史黑名单数目
     * @param startDate
     * @param endDate
     * @param sort
     * @param name
     * @param flag_success_limit
     * @param minTime
     * @param maxTime
     * @param scope
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public int getBlackItemCount(Date startDate, Date endDate, Integer sort,
                                 String name, Integer flag_success_limit,
                                 Double minTime, Double maxTime,
                                 Integer scope, Integer start, Integer end) throws Exception;

    /**
     * 通过id删除黑名单项
     * @param id
     * @return
     * @throws Exception
     */
    public Result delBlackItem(long id) throws Exception;

}
