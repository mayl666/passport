package com.sogou.upd.passport.admin.common.utils;

import com.google.common.base.Strings;
import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: chengang
 * Date: 14-7-22
 * Time: 下午4:57
 */
public class IPUtil {


    /**
     * ip规则校验
     */
    private static final String IP_PATTERN = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";

    /**
     * ip段规则校验
     */
    private static final String IP_RANGE = IP_PATTERN + "\\-" + IP_PATTERN;


    /**
     * vpn 链接后 电信 分配网段
     */
    private static final String VPN_IP_RANAGE_CTCC = "10.129.212.10-10.129.215.240";

    /**
     * vpn 链接后 联通 分配网段
     */
    private static final String VPN_IP_RANAGE_CMCC = "10.129.200.10-10.129.203.240";


    private IPUtil() {
    }

    /**
     * 取得当前用户IP
     *
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {
        // 根据优先级规则，如果取得一个有效ip，立即返回
        String ip = request.getHeader("X-Real-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        ip = request.getHeader("x-forwarded-for");
        if (isValidIp(ip)) {
            return ip;
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        ip = request.getRemoteAddr();
        return ip;
    }

    /**
     * 是否是有效的ip地址
     *
     * @param ip
     * @return
     */
    private static boolean isValidIp(String ip) {
        return (!Strings.isNullOrEmpty(ip)) && (!"unknown".equalsIgnoreCase(ip));
    }


    /**
     * 判断一个ip是否在某一网段内
     *
     * @param ipSection
     * @param ip
     * @return
     */
    public static boolean ipIsValid(String ipSection, String ip) {
        if (Strings.isNullOrEmpty(ipSection)) {
            return false;
        }

        if (Strings.isNullOrEmpty(ip)) {
            return false;
        }
        ipSection = ipSection.trim();
        ip = ip.trim();

        Pattern ipPattern = Pattern.compile(IP_PATTERN);
        Matcher ipMatcher = ipPattern.matcher(ip);

        Pattern ipRangePattern = Pattern.compile(IP_RANGE);
        Matcher ipRangeMatcher = ipRangePattern.matcher(ipSection);

        if (!ipMatcher.matches() || !ipRangeMatcher.matches()) {
            return false;
        }

        int idx = ipSection.indexOf('-');
        String[] sips = ipSection.substring(0, idx).split("\\.");
        String[] sipe = ipSection.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }


    /**
     * 查看用户vpn 连接后，ip是否合法
     *
     * @param userIp
     * @return
     */
    public static boolean checkIpForOperator(String userIp) {
        return ipIsValid(VPN_IP_RANAGE_CTCC, userIp) || ipIsValid(VPN_IP_RANAGE_CMCC, userIp);
    }

}
