package com.sogou.upd.passport.admin.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: chengang
 * Date: 14-7-22
 * Time: 下午4:58
 */
public class RequestUtils {

    private RequestUtils() {
    }

    /**
     * 从cookie 中取得当前登录后台用户
     *
     * @param httpRequest
     * @return
     */
    public static String getPassportEmail(HttpServletRequest httpRequest) {
        String cookieStr = httpRequest.getHeader("Cookie");
        // 字符串截取.
        cookieStr = StringUtils.substringBetween(cookieStr, "jpassport-sp={", "}");
        String userEmail = "";
        if (StringUtils.isNotBlank(cookieStr)) {
            String[] strArray = cookieStr.split(",");
            for (String strTemp : strArray) {
                // System.out.println(strTemp);
                String[] strName = strTemp.split(":");
                // System.out.println(strName[0] + "/" + strName[1]);
                // 循环查找cookie里面的内容.
                if (strName != null && strName.length == 2
                        && strName[0] != null && strName[1] != null
                        && strName[0].equals("username")) {
                    userEmail = strName[1];
                }
            }
        }
        return userEmail;
    }


}
