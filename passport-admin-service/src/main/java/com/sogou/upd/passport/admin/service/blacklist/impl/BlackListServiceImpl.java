package com.sogou.upd.passport.admin.service.blacklist.impl;

import com.sogou.upd.passport.admin.dao.blackList.BlackListDAO;
import com.sogou.upd.passport.admin.model.blackList.BlackList;
import com.sogou.upd.passport.admin.service.blacklist.BlackListService;
import com.sogou.upd.passport.common.utils.RedisUtils;
import com.sogou.upd.passport.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-7
 * Time: 下午12:29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BlackListServiceImpl implements BlackListService {
    private static final Logger logger = LoggerFactory.getLogger(BlackListServiceImpl.class);

    @Autowired
    private BlackListDAO blackListDAO;


    public int insertBlackList(BlackList blackList) throws ServiceException {
        return  blackListDAO.insertBlackList(blackList);
    }

    @Override
    public List<BlackList> getBlackList(String userid, Integer start, Integer end) throws ServiceException {
        return blackListDAO.getBlackList(userid, start, end);
    }

    @Override
    public int getBlackListCount(String userid) throws ServiceException {
        return blackListDAO.getBlackListCount(userid);
    }

    @Override
    public BlackList getBlackListByUserID(String userid) throws ServiceException {
        return blackListDAO.getBlackListByUserID(userid);
    }

    @Override
    public int updateBlackList(BlackList blackList) throws ServiceException {
        return blackListDAO.updateBlackList(blackList);
    }

    @Override
    public List<BlackList> getBlackListByValid() {
        return blackListDAO.getBlackListByValid();
    }
}
