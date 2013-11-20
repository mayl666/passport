package com.sogou.upd.passport.admin.manager.config.impl;

import com.sogou.upd.passport.admin.config.ConfigService;
import com.sogou.upd.passport.admin.manager.config.ConfigManager;
import com.sogou.upd.passport.model.app.AppConfig;
import com.sogou.upd.passport.model.config.ClientIdLevelMapping;
import com.sogou.upd.passport.model.config.InterfaceLevelMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liuling
 * Date: 13-11-11
 * Time: 上午1:12
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ConfigManagerImpl implements ConfigManager {

    @Autowired
    private ConfigService configService;

    @Override
    public InterfaceLevelMapping findInterfaceById(String id) throws Exception {
        return configService.findInterfaceById(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<InterfaceLevelMapping> findInterfaceLevelMappingList() throws Exception {
        return configService.findInterfaceLevelMappingList();
    }

    @Override
    public boolean saveOrUpdateInterfaceLevelMapping(InterfaceLevelMapping interfaceLevelMapping) throws Exception {
        return configService.saveOrUpdateInterfaceLevelMapping(interfaceLevelMapping);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteInterfaceLevelById(String id) throws Exception {
        return configService.deleteInterfaceLevelById(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getInterfaceCount() throws Exception {
        return configService.getInterfaceCount();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ClientIdLevelMapping findLevelByClientId(String clientId) throws Exception {
        return configService.findLevelByClientId(clientId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ClientIdLevelMapping> findClientIdLevelMappingList() throws Exception {
        return configService.findClientIdLevelMappingList();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean saveOrUpdateClientAndLevel(ClientIdLevelMapping clientIdLevelMapping) throws Exception {
        return configService.saveOrUpdateClientAndLevel(clientIdLevelMapping);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<InterfaceLevelMapping> getInterfaceMapByLevel() throws Exception {
        return configService.getInterfaceMapByLevel();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ClientIdLevelMapping getLevelByClientId(String clientId) throws Exception {
        return configService.getLevelByClientId(clientId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AppConfig getAppNameByAppId(String clientId) throws Exception {
        return configService.getAppNameByAppId(clientId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<AppConfig> getAppList() throws Exception {
        return configService.getAppList();  //To change body of implemented methods use File | Settings | File Templates.
    }


}
