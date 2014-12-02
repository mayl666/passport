package com.sogou.upd.passport.admin.blacklist;

import com.sogou.upd.passport.admin.BaseTest;
import com.sogou.upd.passport.admin.manager.blackList.BlackListManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: lzy_clement
 * Date: 14-11-28
 * Time: 下午2:19
 * To change this template use File | Settings | File Templates.
 */
public class BlackListManagerImplTest extends BaseTest {

    @Autowired
    private BlackListManager blackListManager;

    @Test
    public void testInsertBlackListAccount(){
        String passport = "1978121526119@sohu.com";
//        blackListManager.insertBlackListAccount(passport,null);
    }

    @Test
    public void testUpdateBlackListStatus(){
        blackListManager.updateBlackListStatus("aaa.sogou.com",true);
    }

    @Test
    public void testInsertBlackList(){
        System.out.print(blackListManager.insertBlackList("test4",Long.parseLong("1")));
        System.out.print(blackListManager.insertBlackList("test",null));
    }

    @Test
    public void testGetBlackList() {
        String userid = null;
        Integer pageNo = 1;
        Integer pageSize = 10;
        System.out.println(blackListManager.getBlackList(userid, pageNo, pageSize).getItems());
        System.out.println(blackListManager.getBlackList(userid, pageNo, pageSize).getPageIndex());
        System.out.println(blackListManager.getBlackList(userid, pageNo, pageSize).getMaxPage());
        System.out.println(blackListManager.getBlackList(userid, pageNo, pageSize).getTotal());
        Assert.notEmpty(blackListManager.getBlackList(userid, pageNo, pageSize).getItems());
        userid = "1";
        System.out.println(blackListManager.getBlackList(userid, pageNo, pageSize).getItems());
        Assert.notEmpty(blackListManager.getBlackList(userid, pageNo, pageSize).getItems());
        pageNo = 2;
        pageSize = 1;
        System.out.println(blackListManager.getBlackList(userid, pageNo, pageSize).getItems());
        Assert.notNull(blackListManager.getBlackList(userid, pageNo, pageSize));
    }
}
