package com.sogou.upd.passport.admin.manager.model;

/**
 * 用户账号信息
 * User: chengang
 * Date: 14-6-25
 * Time: 下午5:22
 */
public class AccountDetailInfo {

    /**
     * 用户账号
     */
    private String passportId;

    /**
     * 密码
     */
    private String password;

    /**
     * 绑定手机
     */
    private String mobile;

    /**
     * 注册时间
     */
    private String regTime;


    /**
     * 注册IP
     */
    private String regIp;

    /**
     * 用户状态 1-正式用户，2-未激活账号，3-锁定或封杀用户
     */
    private int flag;

    /**
     * 账号类型 1-email，2-phone，3-qq，4-sina，5-renren，6-taobao；7-baidu；
     */
    private int accountType;


    /**
     * 账号类型名称  ：搜狗域、搜狐域、第三方账号、手机账号、外域邮箱账号
     */
    private String accountTypeName;

    /**
     * 用户昵称
     */
    private String uniqname; // 昵称

    /**
     * 绑定邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String fullname;


    /**
     * 身份证号
     */
    private String personalid;

    /**
     * 用户性别
     */
    private String gender;


    /**
     * 省份
     */
    private String province;


    /**
     * 城市
     */
    private String city;

    public AccountDetailInfo() {

    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getUniqname() {
        return uniqname;
    }

    public void setUniqname(String uniqname) {
        this.uniqname = uniqname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPersonalid() {
        return personalid;
    }

    public void setPersonalid(String personalid) {
        this.personalid = personalid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
