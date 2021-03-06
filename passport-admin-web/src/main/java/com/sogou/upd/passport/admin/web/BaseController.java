package com.sogou.upd.passport.admin.web;

import com.google.common.base.Strings;
import com.sogou.upd.passport.admin.common.utils.IPUtil;
import com.sogou.upd.passport.admin.common.utils.RequestUtils;
import com.sogou.upd.passport.admin.manager.operate.OperateManager;
import com.sogou.upd.passport.common.parameter.AccountDomainEnum;
import com.sogou.upd.passport.model.operatelog.OperateHistoryLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public static final String INTERNAL_HOST = "api.id.sogou.com.z.sogou-op.org;dev01.id.sogou.com;test01.id.sogou.com";

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private OperateManager operateManager;

    //TODO IP白名单 暂写死、之后改到后台进行添加
    private static final String WHITE_IP_LIST = "10.129.192.118,10.129.192.58,10.129.192.145,10.129.192.201,10.129.192.194," +
            "10.129.192.160,10.129.192.114,10.129.192.45,10.129.192.211,10.129.192.186,10.129.192.220,10.129.40.203,10.129.40.198," +
            "10.129.41.14,10.129.41.21,10.129.40.166,10.129.40.210,10.129.41.51,10.129.40.64,10.129.192.103";


    //操作人员：韩旭
    private static final String OPERATOR_HANXU = "hanxu200946@sogou-inc.com";

    // 操作人 林琳 特殊处理
    private static final String OPERATOR_LINLIN = "linlin201945@sogou-inc.com";

    //魏云龙
    private static final String OPERATOR_WEIYUNLONG = "weiyunlong@sogou-inc.com";

    /**
     * 判断是否是服务端签名
     */
    protected boolean isServerSig(String client_signature, String signature) {
        if (StringUtils.isEmpty(signature)) {
            return false;
        }
        return true;
    }

    /**
     * 验证参数是否有空参数
     */
    protected boolean hasEmpty(String... args) {

        if (args == null) {
            return false;
        }

        Object[] argArray = getArguments(args);
        for (Object obj : argArray) {
            if (obj instanceof String && StringUtils.isEmpty((String) obj)) {
                return true;
            }
        }
        return false;
    }

    private Object[] getArguments(Object[] varArgs) {
        if (varArgs.length == 1 && varArgs[0] instanceof Object[]) {
            return (Object[]) varArgs[0];
        } else {
            return varArgs;
        }
    }

    protected static String getIp(HttpServletRequest request) {
        String sff = request.getHeader("X-Forwarded-For");// 根据nginx的配置，获取相应的ip
        if (sff == null) {
            return "";
        }
        String[] ips = sff.split(",");
        String realip = ips[0];
        return realip;
    }

    protected boolean isInternalRequest(HttpServletRequest request) {

        String host = request.getServerName();
        String[] hosts = INTERNAL_HOST.split(";");
        int i = Arrays.binarySearch(hosts, host);
        if (i >= 0) {
            return true;
        }
        return false;
    }


    /**
     * 构建记录后台操作日志
     *
     * @param request
     * @param passportId
     * @return
     */
    protected OperateHistoryLog buildOperateHistoryLog(HttpServletRequest request, String passportId) {
        OperateHistoryLog operateHistoryLog = new OperateHistoryLog();
        operateHistoryLog.setOperate_userid(RequestUtils.getPassportEmail(request));
        operateHistoryLog.setOperate_userip(IPUtil.getIP(request));
        operateHistoryLog.setOperate_user(passportId);
        operateHistoryLog.setAccount_type(AccountDomainEnum.getAccountDomain(passportId).getValue());
        return operateHistoryLog;
    }


    /**
     * 判断是否有操作权限
     *
     * @param userIp
     * @return
     */
    protected boolean checkHasOperatePower(String userIp) {
        return StringUtils.contains(WHITE_IP_LIST, userIp);
    }

    /**
     * 检查操作者、ip是否在白名单中
     *
     * @param userName
     * @param userIp
     * @return
     */
    protected boolean checkUserOrIpInWhiteList(String userName, String userIp) {
        //林琳 特殊处理
      /*  if (!Strings.isNullOrEmpty(userName) && userName.equals(OPERATOR_LINLIN)) {
            return true;
        } else {
            if (!Strings.isNullOrEmpty(userName) && userName.equals(OPERATOR_HANXU)) {
                boolean checkIp = IPUtil.checkIpForOperator(userIp);
                if (checkIp) {
                    return true;
                }
            }
            return operateManager.checkUserOrIpInWhiteList(userName, userIp);
        }*/

        if (!Strings.isNullOrEmpty(userName)) {
            if (userName.equals(OPERATOR_HANXU) || userName.equals(OPERATOR_LINLIN) || userName.equals(OPERATOR_WEIYUNLONG)) {
                boolean checkIp = IPUtil.checkIpForOperator(userIp);
                if (checkIp) {
                    return true;
                }
            }
        }
        return operateManager.checkUserOrIpInWhiteList(userName, userIp);

    }

}
