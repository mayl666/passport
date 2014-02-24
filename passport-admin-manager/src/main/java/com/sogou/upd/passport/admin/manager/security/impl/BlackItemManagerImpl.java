package com.sogou.upd.passport.admin.manager.security.impl;

import com.sogou.upd.passport.admin.manager.security.BlackItemManager;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.common.CacheConstant;
import com.sogou.upd.passport.common.result.APIResultSupport;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.ErrorUtil;
import com.sogou.upd.passport.common.utils.RedisUtils;
import com.sogou.upd.passport.model.black.BlackItem;
import com.sogou.upd.passport.service.account.OperateTimesService;
import com.sogou.upd.passport.service.black.BlackItemService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 14-2-18
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BlackItemManagerImpl implements BlackItemManager {

    public static final long TIME_ONEHOUR = 60 * 60 * 1000; // 时间 一小时,单位s

    @Autowired
    private BlackItemService blackItemService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<BlackItem> queryCurrentLoginBlackIpList() throws Exception {
        Date curDate = new Date();
        DateTime dateTime = new DateTime();
        Date startDate = dateTime.minus(TIME_ONEHOUR).toDate();
        List<BlackItem> list = blackItemService.getBlackItemList(startDate, curDate, BlackItem.BLACK_IP, null, null, null, null, BlackItem.SCOPE_LOGIN, null, null);
        return list;
    }

    @Override
    public List<BlackItem> queryCurrentLoginBlackUserList() throws Exception {
        Date curDate = new Date();
        DateTime dateTime = new DateTime();
        Date startDate = dateTime.minus(TIME_ONEHOUR).toDate();
        List<BlackItem> list = blackItemService.getBlackItemList(startDate, curDate, BlackItem.BLACK_USERNAME, null, null, null, null, BlackItem.SCOPE_LOGIN, null, null);
        return list;
    }


    @Override
    public List<BlackItem> getCurrentLoginBlackIpByName(String name) throws Exception {
        Date curDate = new Date();
        DateTime dateTime = new DateTime();
        Date startDate = dateTime.minus(TIME_ONEHOUR).toDate();
        List<BlackItem> list = blackItemService.getBlackItemList(startDate, curDate, BlackItem.BLACK_IP, name, null, null, null, BlackItem.SCOPE_LOGIN, null, null);
        return list;
    }

    @Override
    public Result delIpFromCurLoginListByName(String ip, long id) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            String key = buildLoginIPBlackKeyStr(ip);
            redisUtils.delete(key);

            String timesKey = buildIPLoginTimesKeyStr(ip);
            redisUtils.delete(timesKey);

            delCurLoginBlackItemById(id);

            result.setSuccess(true);
            result.setMessage("删除成功");
        } catch (Exception e) {
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }

    @Override
    public Result addIpOrUserToLoginBlack(String ipOrUser, boolean isIp) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            blackItemService.addIPOrUsernameToLoginBlackList(ipOrUser, BlackItem.Add_LIMIT, isIp);
            result.setSuccess(true);
            result.setMessage("增加成功");
        } catch (Exception e) {
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }

    @Override
    public Result delBlackItem(long id) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            delCurLoginBlackItemById(id);
            result.setSuccess(true);
            result.setMessage("删除成功");
        } catch (Exception e) {
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }

    @Override
    public Result delUserFromCurLoginListByName(String username, long id) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            String key = buildLoginUserNameBlackKeyStr(username);
            redisUtils.delete(key);

            String timesKey = buildUserNameLoginTimesKeyStr(username);
            redisUtils.delete(timesKey);

            delCurLoginBlackItemById(id);

            result.setSuccess(true);
            result.setMessage("删除成功");
        } catch (Exception e) {
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }

    @Override
    public int delCurLoginBlackItemById(long id) throws Exception {
        return blackItemService.delBlackItem(id);
    }


    @Override
    public List<BlackItem> getCurrentLoginBlackNameByName(String name) throws Exception {
        Date curDate = new Date();
        DateTime dateTime = new DateTime();
        Date startDate = dateTime.minus(TIME_ONEHOUR).toDate();
        List<BlackItem> list = blackItemService.getBlackItemList(startDate, curDate, BlackItem.BLACK_USERNAME, name, null, null, null, BlackItem.SCOPE_LOGIN, null, null);
        return list;
    }

    @Override
    public List<BlackItem> getBlackList(Date startDate, Date endDate, Integer sort,
                                        String name, Integer flag_success_limit,
                                        Double minTime, Double maxTime,
                                        Integer scope, Integer start, Integer end) throws Exception {
        return blackItemService.getBlackItemList(startDate, endDate, sort, name, flag_success_limit, minTime, maxTime, BlackItem.SCOPE_LOGIN, start, end);
    }

    @Override
    public int getBlackItemCount(Date startDate, Date endDate, Integer sort,
                                        String name, Integer flag_success_limit,
                                        Double minTime, Double maxTime,
                                        Integer scope, Integer start, Integer end) throws Exception {
        return blackItemService.getBlackItemCount(startDate, endDate, sort, name, flag_success_limit, minTime, maxTime, BlackItem.SCOPE_LOGIN, start, end);
    }



    private static String buildLoginUserNameBlackKeyStr(String username) {
        return CacheConstant.CACHE_PREFIX_LOGIN_USERNAME_BLACK_ + username;
    }

    private static String buildLoginIPBlackKeyStr(String ip) {
        return CacheConstant.CACHE_PREFIX_LOGIN_IP_BLACK_ + ip;
    }

    private static String buildUserNameLoginTimesKeyStr(String username) {
        return CacheConstant.CACHE_PREFIX_USERNAME_LOGINNUM + username;
    }

    private static String buildIPLoginTimesKeyStr(String ip) {
        return CacheConstant.CACHE_PREFIX_IP_LOGINNUM + ip;
    }

}
