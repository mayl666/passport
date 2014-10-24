package com.sogou.upd.passport.admin.util;

import com.sogou.upd.passport.admin.common.utils.IPUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: chengang
 * Date: 14-10-24
 * Time: 下午3:03
 */
public class IPUtilTest {


    /**
     * 电信 10.129.212.10-10.129.215.240
     * 联通 10.129.200.10-10.129.203.240
     */


    @Test
    public void testIpRangeTest() {

        final String vpn_ipRange_CTCC = "10.129.212.10-10.129.215.240";
        final String vpn_ipRange1_CUCC = "10.129.212.10-10.129.215.240";

        String ipTest = "10.192.129.45";
        String ipTest2 = "10.129.212.119";

        boolean isTrue = IPUtil.ipIsValid(vpn_ipRange_CTCC, ipTest);
        boolean isTrue2 = IPUtil.ipIsValid(vpn_ipRange_CTCC, ipTest2);

        System.out.println("isTrue  " + isTrue);
        System.out.println("isTrue2  " + isTrue2);
    }
}
