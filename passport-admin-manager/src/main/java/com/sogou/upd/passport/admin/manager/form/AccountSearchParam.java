package com.sogou.upd.passport.admin.manager.form;

/**
 * 账号信息查询参数类
 * User: chengang
 * Date: 14-8-7
 * Time: 下午3:12
 */
public class AccountSearchParam {


    /**
     * 用户账号
     */
    private String userName;


    /**
     * 用户昵称
     */
    private String nickName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
