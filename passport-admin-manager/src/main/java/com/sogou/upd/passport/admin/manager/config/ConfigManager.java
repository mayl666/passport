package com.sogou.upd.passport.admin.manager.config;

import com.sogou.upd.passport.model.app.AppConfig;
import com.sogou.upd.passport.model.config.ClientIdLevelMapping;
import com.sogou.upd.passport.model.config.InterfaceLevelMapping;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liuling
 * Date: 13-11-11
 * Time: 上午1:11
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigManager {

    /**
     * 修改前先读出该条记录
     *
     * @param id
     * @return
     * @throws com.sogou.upd.passport.exception.ServiceException
     *
     */
    public InterfaceLevelMapping findInterfaceById(String id) throws Exception;

    /**
     * 获取配置信息列表
     *
     * @return
     */
    public List<InterfaceLevelMapping> findInterfaceLevelMappingList() throws Exception;

    /**
     * 新增或修改接口
     *
     * @param interfaceLevelMapping 新增或修改接口和等级信息
     * @return
     */
    public boolean saveOrUpdateInterfaceLevelMapping(InterfaceLevelMapping interfaceLevelMapping) throws Exception;

    /**
     * 删除接口
     *
     * @param id 要删除的接口id
     * @return
     */
    public boolean deleteInterfaceLevelById(String id) throws Exception;

    /**
     * 查询接口列表的总行数
     *
     * @return
     * @throws Exception
     */
    public int getInterfaceCount() throws Exception;

    /**
     * 根据应用id查询对应的等级信息
     *
     * @param clientId
     * @return
     * @throws Exception
     */
    public ClientIdLevelMapping findLevelByClientId(String clientId) throws Exception;

    /**
     * 获取所有应用id
     *
     * @return
     */
    public List<ClientIdLevelMapping> findClientIdLevelMappingList() throws Exception;

    /**
     * 保存应用和等级信息
     *
     * @param clientIdLevelMapping
     * @return
     */
    public boolean saveOrUpdateClientAndLevel(ClientIdLevelMapping clientIdLevelMapping) throws Exception;

    /**
     * 根据hashKey获取对应的key-value键值对
     *
     * @param clientId
     * @return
     */
    public Map<String, String> getMapsFromCacheKey(String clientId) throws Exception;

    /**
     * 查询所有接口及对应等级信息，按三个不同的等级划分
     *
     * @return
     */
    public Map<String, List<InterfaceLevelMapping>> getInterfaceMapByLevel() throws Exception;

    /**
     * 根据应用查询该应用对应的等级
     *
     * @param clientId
     * @return
     * @throws Exception
     */
    public ClientIdLevelMapping getLevelByClientId(String clientId) throws Exception;

    /**
     * 根据应用id查询应用名称
     *
     * @param clientId
     * @return
     * @throws Exception
     */
    public AppConfig getAppNameByAppId(String clientId) throws Exception;

    /**
     * 获取所有应用的信息，主要是id和name
     *
     * @return
     * @throws Exception
     */
    public List<AppConfig> getAppList() throws Exception;
}
