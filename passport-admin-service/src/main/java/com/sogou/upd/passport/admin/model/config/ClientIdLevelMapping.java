package com.sogou.upd.passport.admin.model.config;

/**
 * 应用与等级映射类
 * Created with IntelliJ IDEA.
 * User: liuling
 * Date: 13-11-7
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public class ClientIdLevelMapping {

    private long id;                  //主键id
    private int clientId;          //应用id
    private int levelInfo;         //等级信息
    private String interfaceName;     //接口名称，todo 为以后单独指定某一应用下某一接口等级所做的扩展，暂时没用到此参数

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLevelInfo() {
        return levelInfo;
    }

    public void setLevelInfo(int levelInfo) {
        this.levelInfo = levelInfo;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
