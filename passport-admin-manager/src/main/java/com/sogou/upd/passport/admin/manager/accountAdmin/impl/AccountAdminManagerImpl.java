package com.sogou.upd.passport.admin.manager.accountAdmin.impl;

import com.google.common.base.Strings;
import com.sogou.upd.passport.admin.common.enums.AccountTypeMappingEnum;
import com.sogou.upd.passport.admin.common.utils.MessageUtil;
import com.sogou.upd.passport.admin.common.utils.UuidUtil;
import com.sogou.upd.passport.admin.manager.accountAdmin.AccountAdminManager;
import com.sogou.upd.passport.admin.manager.model.AccountDetailInfo;
import com.sogou.upd.passport.common.parameter.AccountDomainEnum;
import com.sogou.upd.passport.common.result.APIResultSupport;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.ErrorUtil;
import com.sogou.upd.passport.common.utils.ProvinceAndCityUtil;
import com.sogou.upd.passport.model.account.Account;
import com.sogou.upd.passport.model.account.AccountInfo;
import com.sogou.upd.passport.service.account.AccountInfoService;
import com.sogou.upd.passport.service.account.AccountService;
import com.sogou.upd.passport.service.account.MobilePassportMappingService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-14
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AccountAdminManagerImpl implements AccountAdminManager {

    private static final Logger logger = LoggerFactory.getLogger(AccountAdminManagerImpl.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private MobilePassportMappingService mobilePassportMappingService;

    @Override
    public Account queryAccountByUserName(String username) {
        try {
            String passportId = username;
            //判断登录用户类型
            AccountDomainEnum accountDomainEnum = AccountDomainEnum.getAccountDomain(username);
            //默认是sogou.com
            if (AccountDomainEnum.PHONE.equals(accountDomainEnum)) {
                passportId = mobilePassportMappingService.queryPassportIdByUsername(username);
            } else if (AccountDomainEnum.UNKNOWN.equals(accountDomainEnum)) {
                passportId = passportId + "@sogou.com";
            }

            return accountService.queryAccountByPassportId(passportId);
        } catch (Exception e) {
            logger.error("queryAccountByUserName fail,userId:" + username, e);
            return null;
        }
    }

    @Override
    public boolean updateState(Account account, int newState) {
        try {
            return accountService.updateState(account, newState);
        } catch (Exception e) {
            logger.error("updateState fail,userId:" + account.getPassportId(), e);
            return false;
        }
    }

    @Override
    public boolean resetPassword(Account account, String password, boolean needMD5) {
        try {
            return accountService.resetPassword(account, password, needMD5);
        } catch (Exception e) {
            logger.error("resetPassword fail,userId:" + account.getPassportId(), e);
            return false;
        }
    }

    @Override
    public AccountDetailInfo getAccountDetailInfo(String username) {
        AccountDetailInfo accountDetail = new AccountDetailInfo();

        try {
            String passportId = username;
            //判断登录用户类型
            AccountDomainEnum accountDomainEnum = AccountDomainEnum.getAccountDomain(username);
            //默认是sogou.com
            if (AccountDomainEnum.PHONE.equals(accountDomainEnum)) {
                passportId = mobilePassportMappingService.queryPassportIdByUsername(username);
            } else if (AccountDomainEnum.UNKNOWN.equals(accountDomainEnum)) {
                passportId = passportId + "@sogou.com";
            }

            Account account = accountService.queryAccountByPassportId(passportId);
            if (account != null) {
                accountDetail.setPassportId(passportId);
                accountDetail.setPassword(account.getPassword());
                accountDetail.setAccountTypeName(AccountTypeMappingEnum.indexOf(accountDomainEnum.getValue()).getAccountTypeName());
                accountDetail.setAccountType(accountDomainEnum.getValue());
                accountDetail.setMobile(Strings.isNullOrEmpty(account.getMobile()) ? StringUtils.EMPTY : account.getMobile());
                accountDetail.setRegIp(Strings.isNullOrEmpty(account.getRegIp()) ? StringUtils.EMPTY : account.getRegIp());
                accountDetail.setRegTime(account.getRegTime().toString());
                accountDetail.setUniqname(Strings.isNullOrEmpty(account.getUniqname()) ? StringUtils.EMPTY : account.getUniqname());
                accountDetail.setFlag(account.getFlag());
            }

            AccountInfo accountInfo = accountInfoService.queryAccountInfoByPassportId(passportId);
            if (accountInfo != null) {
                accountDetail.setEmail(Strings.isNullOrEmpty(accountInfo.getEmail()) ? StringUtils.EMPTY : accountInfo.getEmail());
                accountDetail.setProvince(ProvinceAndCityUtil.getProvinceByPCode(accountInfo.getProvince()));
                accountDetail.setCity(ProvinceAndCityUtil.getCityByCityCode(accountInfo.getCity()));
                accountDetail.setGender(accountInfo.getGender().equalsIgnoreCase("0") ? "女" : "男");
                accountDetail.setFullname(Strings.isNullOrEmpty(accountInfo.getFullname()) ? StringUtils.EMPTY : accountInfo.getFullname());
                accountDetail.setPersonalid(Strings.isNullOrEmpty(accountInfo.getPersonalid()) ? StringUtils.EMPTY : accountInfo.getPersonalid());
            }
        } catch (Exception e) {
            logger.error("getAccountDetailInfo error,passportId:" + username, e);
        }
        return accountDetail;
    }

    @Override
    public Result resetPassword(String passportId, boolean needMD5) {
        Result result = new APIResultSupport(false);
        try {
            if (Strings.isNullOrEmpty(passportId)) {
                result.setCode(ErrorUtil.ERR_CODE_COM_REQURIE);
                result.setSuccess(false);
                result.setMessage(ErrorUtil.ERR_CODE_MSG_MAP.get(ErrorUtil.ERR_CODE_COM_REQURIE));
                return result;
            }
            Account account = accountService.queryAccountByPassportId(passportId);
            if (account != null) {
                //重置密码
                String newPassword = UuidUtil.generatePassword();
                boolean resetResult = accountService.resetPassword(account, newPassword, needMD5);
                if (resetResult) {
                    result.setSuccess(true);
                    result.setMessage(MessageUtil.RESET_PP_SUCCESS);
                    result.setDefaultModel("newPassword", newPassword);
                }
            } else {
                result.setCode(ErrorUtil.INVALID_ACCOUNT);
            }
        } catch (Exception e) {
            logger.error("resetPassword error.passportId:" + passportId);
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }

    /**
     * 解绑定手机
     *
     * @param passportId
     * @param mobile
     * @return
     */
    @Override
    public Result unbundlingMobile(String passportId, String mobile) {
        Result result = new APIResultSupport(false);

        try {
            //清除mobile_passport_id_mapping 影身

            //更新account_info，清空用户绑定的 mobile 信息




        } catch (Exception e) {
            logger.error("unbundlingMobile error.passportId:" + passportId);
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }

    @Override
    public Result unbundlingEmail(String passportId) {
        Result result = new APIResultSupport(false);

        try {

            //更新 account ,清空用户绑定的 email 信息

        } catch (Exception e) {
            logger.error("unbundlingEmail error.passportId:" + passportId);
            result.setCode(ErrorUtil.SYSTEM_UNKNOWN_EXCEPTION);
        }
        return result;
    }
}
