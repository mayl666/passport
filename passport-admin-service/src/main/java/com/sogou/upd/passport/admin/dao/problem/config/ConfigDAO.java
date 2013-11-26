package com.sogou.upd.passport.admin.dao.problem.config;

import com.sogou.upd.passport.admin.model.config.ClientIdLevelMapping;
import com.sogou.upd.passport.admin.model.config.InterfaceLevelMapping;
import com.sogou.upd.passport.model.app.AppConfig;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liuling
 * Date: 13-11-6
 * Time: 下午11:52
 * To change this template use File | Settings | File Templates.
 */
@DAO
public interface ConfigDAO {

    /**
     * 对应接口与等级数据库表名称
     */
    String INTERFACE_LEVEL_TABLE_NAME = " interface_level_mapping ";

    /**
     * 应用与等级数据库表名称
     */
    String CLIENTID_LEVEL_TABLE_NAME = " clientid_level_mapping ";
    /**
     * 接口与次数表所有字段
     */
    String INTERFACE_ALL_FIELDS = " id,interface_name,primary_level,primary_level_count,middle_level,middle_level_count,high_level,high_level_count,create_time ";
    /**
     * 接口与次数表所有字段值
     */
    String INTERFACE_ALL_VALUES = ":inter.id,:inter.interfaceName,:inter.primaryLevel,:inter.primaryLevelCount,:inter.middleLevel,:inter.middleLevelCount,:inter.highLevel,:inter.highLevelCount,:inter.createTime";
    /**
     * 应用与等级表所有字段
     */
    String CLIENTID_ALL_FIELDS = " id,client_id,level_info,interface_name ";
    /**
     * 应用与等级表所有字段值
     */
    String CLIENTID_ALL_VALUES = ":clm.id,:clm.clientId,:clm.levelInfo,:clm.interfaceName";
    /**
     * 查询应用表所有字段
     */
    String APP_CONFIG_ALL_FIELDS = " id,client_id,sms_text,access_token_expiresin,refresh_token_expiresin,server_secret,client_secret,create_time,client_name ";


    /**
     * 应用表
     */
    String APP_CONFIG = " app_config ";

    /**
     * 根据id查询接口信息
     *
     * @param id
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL(" select " + INTERFACE_ALL_FIELDS + " from " +
            INTERFACE_LEVEL_TABLE_NAME +
            " where id=:id")
    public InterfaceLevelMapping findInterfaceById(@SQLParam("id") long id) throws DataAccessException;

    /**
     * 获取所有接口配置信息列表
     */
    @SQL("select " + INTERFACE_ALL_FIELDS + " from " +
            INTERFACE_LEVEL_TABLE_NAME
    )
    public List<InterfaceLevelMapping> findInterfaceLevelMappingList() throws DataAccessException;

    /**
     * 查询接口列表的总行数
     *
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL("select count(*) from " +
            INTERFACE_LEVEL_TABLE_NAME)
    public int getInterfaceCount() throws DataAccessException;

    /**
     * 新增配置信息接口,三个等级新增时会有默认数值
     */
    @SQL("insert into" +
            INTERFACE_LEVEL_TABLE_NAME +
            "(" + INTERFACE_ALL_FIELDS + ") " +
            "values (" + INTERFACE_ALL_VALUES + ")"
    )
    public int insertInterfaceLevelMapping(@SQLParam("inter") InterfaceLevelMapping inter) throws DataAccessException;

    /**
     * 根据id删除对应接口配置信息
     */
    @SQL("delete from" +
            INTERFACE_LEVEL_TABLE_NAME +
            "where id=:id"
    )
    public int deleteInterfaceLevelMappingById(@SQLParam("id") long id) throws DataAccessException;

    /**
     * 修改接口配置信息
     */
    @SQL("update " +
            INTERFACE_LEVEL_TABLE_NAME +
            " set " +
            "#if(:inter.interfaceName != null){interface_name=:inter.interfaceName,}  " +
            "#if(:inter.primaryLevel != 0){primary_level=:inter.primaryLevel,}  " +
            "#if(:inter.primaryLevelCount != 0){primary_level_count=:inter.primaryLevelCount,}  " +
            "#if(:inter.middleLevel != 0){middle_level=:inter.middleLevel,}  " +
            "#if(:inter.middleLevelCount != 0){middle_level_count=:inter.middleLevelCount,}  " +
            "#if(:inter.highLevel != 0){high_level=:inter.highLevel,}  " +
            "#if(:inter.highLevelCount != 0){high_level_count=:inter.highLevelCount,}  " +
            "#if(:inter.createTime != null){create_time=:inter.createTime}  " +
            "where id=:inter.id"
    )
    public int updateInterfaceLevelMapping(@SQLParam("inter") InterfaceLevelMapping inter) throws DataAccessException;


    /**
     * 查询应用与等级映射信息
     *
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL(" select " + CLIENTID_ALL_FIELDS + " from " +
            CLIENTID_LEVEL_TABLE_NAME)
    public List<ClientIdLevelMapping> findClientIdAndLevelList() throws DataAccessException;

    /**
     * 根据clientId查得该应用的等级
     *
     * @param clientId
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL(" select " + CLIENTID_ALL_FIELDS + " from " +
            CLIENTID_LEVEL_TABLE_NAME +
            "where client_id=:clientId")
    public ClientIdLevelMapping findLevelByClientId(@SQLParam("clientId") int clientId) throws DataAccessException;

    /**
     * 新增应用与等级关系
     *
     * @param clm
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL("insert into" +
            CLIENTID_LEVEL_TABLE_NAME +
            "(" + CLIENTID_ALL_FIELDS +")" +
            "values (" + CLIENTID_ALL_VALUES +")"
    )
    public int insertClientIdAndLevel(@SQLParam("clm") ClientIdLevelMapping clm) throws DataAccessException;

    /**
     * 修改应用与等级的映射信息
     *
     * @param clientIdLevelMapping
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL("update " +
            CLIENTID_LEVEL_TABLE_NAME +
            "set level_info=:clientIdLevelMapping.levelInfo where client_id=:clientIdLevelMapping.clientId")
    public int updateClientIdAndLevelMapping(@SQLParam("clientIdLevelMapping") ClientIdLevelMapping clientIdLevelMapping) throws DataAccessException;

    /**
     * 查出某一等级下的所有接口
     *
     * @param level
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL("select " + CLIENTID_ALL_FIELDS + " from " +
            CLIENTID_LEVEL_TABLE_NAME +
            "where level_info=:level")
    public List<ClientIdLevelMapping> getClientIdListByLevel(@SQLParam("level") int level) throws DataAccessException;

    /**
     * 根据应用id查该应用对应的等级
     *
     * @param clientId
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL("select " + CLIENTID_ALL_FIELDS + " from " +
            CLIENTID_LEVEL_TABLE_NAME +
            "where client_id=:clientId"
    )
    public ClientIdLevelMapping getLevelByClientId(@SQLParam("clientId") int clientId) throws DataAccessException;

    /**
     * 查询所有接口与等级的信息
     *
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL("select " + INTERFACE_ALL_FIELDS  + " from " +
            INTERFACE_LEVEL_TABLE_NAME)
    public List<InterfaceLevelMapping> getInterfaceListAll() throws DataAccessException;


    /**
     * 根据应用id获取应用名称
     *
     * @param appId
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL("select " + APP_CONFIG_ALL_FIELDS  + " from" +
            APP_CONFIG +
            "where client_id=:appId"
    )
    public AppConfig getAppNameByAppId(@SQLParam("appId") int appId) throws DataAccessException;


    /**
     * 获取所有应用的信息，主要是id和name
     *
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SQL("select " + APP_CONFIG_ALL_FIELDS + " from " + APP_CONFIG)
    public List<AppConfig> getAppList() throws DataAccessException;

    /**
     * 根据接口名称查询接口信息
     *
     * @param interfaceName
     * @return
     * @throws DataAccessException
     */
    @SQL("select " + INTERFACE_ALL_FIELDS + " from " + INTERFACE_LEVEL_TABLE_NAME + "where interface_name=:interfaceName")
    public InterfaceLevelMapping getInterfaceByName(@SQLParam("interfaceName") String interfaceName) throws DataAccessException;


}
