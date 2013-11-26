package com.sogou.upd.passport.admin.model.config;

import java.util.Date;

/**
 * 接口与等级映射类
 * Created with IntelliJ IDEA.
 * User: liuling
 * Date: 13-11-6
 * Time: 下午11:49
 * To change this template use File | Settings | File Templates.
 */
public class InterfaceLevelMapping {

    private long id;                         //主键id
    private String interfaceName;            //接口名
    private int primaryLevel;             //初级,数值表示为0
    private long primaryLevelCount;        //初级对应的接口限制次数
    private int middleLevel;              //中级，数值表示为1
    private long middleLevelCount;         //中级对应的接口限制次数
    private int highLevel;                //高级，数值表示为2
    private long highLevelCount;           //高级对应的接口限制次数
    private Date createTime;                 //日期，新增记录是创建日期；修改记录是修改日期

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public int getPrimaryLevel() {
        return primaryLevel;
    }

    public void setPrimaryLevel(int primaryLevel) {
        this.primaryLevel = primaryLevel;
    }

    public long getPrimaryLevelCount() {
        return primaryLevelCount;
    }

    public void setPrimaryLevelCount(long primaryLevelCount) {
        this.primaryLevelCount = primaryLevelCount;
    }

    public int getMiddleLevel() {
        return middleLevel;
    }

    public void setMiddleLevel(int middleLevel) {
        this.middleLevel = middleLevel;
    }

    public long getMiddleLevelCount() {
        return middleLevelCount;
    }

    public void setMiddleLevelCount(long middleLevelCount) {
        this.middleLevelCount = middleLevelCount;
    }

    public int getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(int highLevel) {
        this.highLevel = highLevel;
    }

    public long getHighLevelCount() {
        return highLevelCount;
    }

    public void setHighLevelCount(long highLevelCount) {
        this.highLevelCount = highLevelCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
