package com.sogou.upd.passport.admin.service.blacklist;

import com.sogou.upd.passport.admin.model.blackList.BlackList;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.exception.ServiceException;
import net.paoding.rose.jade.annotation.SQLParam;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-6-4 Time: 下午3:24 To change this template
 * use File | Settings | File Templates.
 */
public interface BlackListService {

    /**
     * 新增黑名单
     *
     * @param blackList
     * @return
     * @throws ServiceException
     */
    public int insertBlackList(BlackList blackList) throws ServiceException;

    /**
     * 获取黑名单列表
     *
     * @param userid 用户passportId
     * @param start  开始位置
     * @param end    每页个数
     * @return
     * @throws ServiceException
     */
    public List<BlackList> getBlackList(String userid, Integer start, Integer end) throws ServiceException;

    /**
     * 获取黑名单总数
     *
     * @param userid 用户passportId
     * @return
     * @throws ServiceException
     */
    public int getBlackListCount(String userid) throws ServiceException;

    /**
     * 根据用户passportId获取用户信息
     *
     * @param userid 用户passportId
     * @return
     * @throws ServiceException
     */
    public BlackList getBlackListByUserID(String userid) throws ServiceException;

    /**
     * 更新黑名单
     *
     * @param blackList 需要更新的黑名单对象
     * @return
     * @throws ServiceException
     */
    public int updateBlackList(BlackList blackList) throws ServiceException;

    public List<BlackList> getBlackListByValid() throws ServiceException;

    public int updateBlackListExpire() throws ServiceException;

    public BlackList getBlackListByID(String id) throws ServiceException;

}
