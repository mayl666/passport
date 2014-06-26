package com.sogou.upd.passport.admin.account;

import com.sogou.upd.passport.admin.BaseTest;
import com.sogou.upd.passport.admin.manager.accountAdmin.AccountAdminManager;
import com.sogou.upd.passport.common.result.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户账号信息单元测试
 * User: chengang
 * Date: 14-6-26
 * Time: 下午5:26
 */
public class AccountAdminManagerImplTest extends BaseTest {


    @Autowired
    private AccountAdminManager accountAdminManager;


    @Test
    public void testResetPassword() {
        String passportId = "gang.chen050dddd5@gmail.com";

//        String newPp = UuidUtil.generatePassword();

        Result result = accountAdminManager.resetPassword(passportId, true);
//        Assert.assertNotNull(result);
//        Assert.assertEquals(true, result.isSuccess());

        System.out.println(" ===== result " + result.toString());

    }
}
