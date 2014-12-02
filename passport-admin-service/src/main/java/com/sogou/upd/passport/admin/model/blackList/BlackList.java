package com.sogou.upd.passport.admin.model.blackList;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lzy_clement
 * Date: 14-11-26
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class BlackList {

    private int id;

    @Override
    public String toString() {
        return "BlackList{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", account_type=" + account_type +
                ", expire_time=" + expire_time +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", status=" + status +
                '}';
    }

    private String userid;
    private int account_type;
    private Long expire_time;
    private Date create_time;
    private Date update_time;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getAccount_type() {
        return account_type;
    }

    public void setAccount_type(int account_type) {
        this.account_type = account_type;
    }

    public Long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(Long expire_time) {
        this.expire_time = expire_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
