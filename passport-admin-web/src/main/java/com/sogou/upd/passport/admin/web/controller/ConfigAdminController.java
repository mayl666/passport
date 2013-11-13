package com.sogou.upd.passport.admin.web.controller;

import com.google.common.collect.Maps;
import com.sogou.upd.passport.admin.manager.config.ConfigManager;
import com.sogou.upd.passport.admin.web.BaseController;
import com.sogou.upd.passport.common.result.APIResultSupport;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.model.config.ClientIdLevelMapping;
import com.sogou.upd.passport.model.config.InterfaceLevelMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liuling
 * Date: 13-11-11
 * Time: 上午1:28
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
public class ConfigAdminController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigAdminController.class);

    @Autowired
    private ConfigManager configManager;

    /**
     * 接口列表查询页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/interface/queryinterfacelist", method = RequestMethod.GET)
    public String queryInterfaceList(Model model) throws Exception {
        List<InterfaceLevelMapping> interfaceVOList = null;
        try {
            interfaceVOList = configManager.findInterfaceLevelMappingList();
        } catch (Exception e) {
            logger.error("queryInterfaceList error:", e);
        }
        model.addAttribute("interfaceVOList", interfaceVOList);
        return "/pages/admin/config/interface.jsp";
    }

    /**
     * 保存接口,新增或修改的
     *
     * @param interfaceName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interface/saveinterface", method = RequestMethod.POST)
    public String saveInterface(@RequestParam("interfaceName") String interfaceName, @RequestParam("id") String id) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            InterfaceLevelMapping ilm = new InterfaceLevelMapping();
            if (!"".equals(id) || id != null) {
                ilm.setId(Long.parseLong(id));
            }
            ilm.setInterfaceName(interfaceName);
            boolean isSuccess = configManager.saveOrUpdateInterfaceLevelMapping(ilm);
            if (isSuccess) {
                result.setSuccess(true);
                result.setMessage("保存接口成功!");
                return result.toString();
            } else {
                result.setMessage("保存接口失败!");
            }
        } catch (Exception e) {
            logger.error("saveInterfcae error:", e);
        }
        return result.toString();
    }

    /**
     * 修改接口信息之前先读取接口信息,显示在新增页面上,新增页面与修改页面相同
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interface/getinterface", method = RequestMethod.GET)
    public String getInterfaceById(@RequestParam("id") String id, Model model) throws Exception {
        InterfaceLevelMapping ilm = null;
        try {
            ilm = configManager.findInterfaceById(id);
        } catch (Exception e) {
            logger.error("getInterfaceById Error:id is " + id, e);
        }
        model.addAttribute("interfaceVO", ilm);
        return "/pages/admin/config/addinterface.jsp";
    }

    /**
     * 删除指定接口
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interface/delInterface", method = RequestMethod.POST)
    public String delInterface(@RequestParam("id") String id) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            boolean isSuccess = configManager.deleteInterfaceLevelById(id);
            if (isSuccess) {
                result.setSuccess(true);
                result.setMessage("删除接口成功!");
            } else {
                result.setMessage("删除接口失败!");
            }
        } catch (Exception e) {
            logger.error("delInterface error:id is " + id, e);
        }
        return result.toString();
    }

    /**
     * 获取应用和等级列表,初始化应用和等级的下拉列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interface/getclientandlevel", method = RequestMethod.GET)
    public String getClientIdAndLevelList(Model model) throws Exception {
        List<ClientIdLevelMapping> listVOResult = null;
        try {
            listVOResult = configManager.findClientIdLevelMappingList();
        } catch (Exception e) {
            logger.error("getClientIdAndLevelList error:", e);
        }
        model.addAttribute("listVO", listVOResult);
        return "/pages/admin/config/clientAndLevel.jsp";
    }

    /**
     * 根据应用查询该应用对应的等级,应用和等级做联动处理
     *
     * @param clientId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interface/getlevelbyclientid", method = RequestMethod.GET)
    public String getLevelByClientId(@RequestParam("clientId") String clientId, Model model) throws Exception {
        ClientIdLevelMapping clm = null;
        try {
            clm = configManager.getLevelByClientId(clientId);
        } catch (Exception e) {
            logger.error("getLevelByClientId error:clientId is " + clientId, e);
        }
        model.addAttribute("clientLevelVO", clm);
        return "/pages/admin/config/clientAndLevel.jsp";
    }

    /**
     * 保存应用和等级信息
     *
     * @param clientId
     * @param level
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interfce/saveclientlevel", method = RequestMethod.POST)
    public String saveClientIdAndLevel(@RequestParam("clientId") String clientId, @RequestParam("level") String level) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            ClientIdLevelMapping clm = new ClientIdLevelMapping();
            clm.setClientId(clientId);
            clm.setLevelInfo(level);
            boolean isSuccess = configManager.saveOrUpdateClientAndLevel(clm);
            if (isSuccess) {
                result.setSuccess(true);
                result.setMessage("保存应用与等级成功！");
            } else {
                result.setMessage("保存应用与等级失败！");
            }
        } catch (Exception e) {
            logger.error("saveClientIdAndLevel error:clientid is " + clientId, e);
        }
        return result.toString();
    }

    /**
     * 显示接口，等级，及对应的次数列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interface/getinterfaceandlevellist", method = RequestMethod.GET)
    public String getInterfaceAndLevelList(Model model) throws Exception {
        Map<String, List<InterfaceLevelMapping>> maps;
        try {
            maps = configManager.getInterfaceMapByLevel();
            Set<String> keySet = maps.keySet();
            if (keySet != null && keySet.size() > 0) {
                Iterator ite = keySet.iterator();
                if (ite != null && ite.hasNext()) {
                    String levelKey = (String) ite.next();
                    model.addAttribute(levelKey, maps.get(levelKey));
                }
            }
        } catch (Exception e) {
            logger.error("getInterfaceAndLevelList error:", e);
        }
        return "/pages/admin/config/interfaceMain.jsp";
    }

    /**
     * 修改之前，先根据id查询该接口，等级及次数的对应信息
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interface/getinterfacelevelbyid", method = RequestMethod.GET)
    public String getInterfaceLevelById(@RequestParam("id") String id, Model model) throws Exception {
        InterfaceLevelMapping ilm = null;
        try {
            ilm = configManager.findInterfaceById(id);
        } catch (Exception e) {
            logger.error("getInterfaceLevelById", e);
        }
        model.addAttribute("interfacelevelVO", ilm);
        return "/pages/admin/config/interfaceMain.jsp";
    }

    /**
     * 保存接口与等级，及对应的次数
     *
     * @param interfaceName
     * @param level
     * @param levelCount
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/interfce/saveinterfaceandlevel", method = RequestMethod.POST)
    public String saveInterfaceAndLevel(@RequestParam("id") String id, @RequestParam("interfaceName") String interfaceName, @RequestParam("level") String level, @RequestParam("levelCount") String levelCount) throws Exception {
        Result result = new APIResultSupport(false);
        try {
            InterfaceLevelMapping ilm = new InterfaceLevelMapping();
            setValue(ilm, level, levelCount);
            ilm.setId(Long.parseLong(id));
            ilm.setInterfaceName(interfaceName);
            boolean isSuccess = configManager.saveOrUpdateInterfaceLevelMapping(ilm);
            if (isSuccess) {
                result.setSuccess(true);
                result.setMessage("保存接口与等级信息成功！");
            } else {
                result.setMessage("保存接口与等级信息失败！");
            }
        } catch (Exception e) {
            logger.error("saveInterfaceAndLevel error: id is " + id, e);
        }
        return result.toString();
    }

    private InterfaceLevelMapping setValue(InterfaceLevelMapping ilm, String level, String levelCount) {
        switch (level) {
            case "0":
                ilm.setPrimaryLevel(level);
                ilm.setPrimaryLevelCount(levelCount);
                break;
            case "1":
                ilm.setMiddleLevel(level);
                ilm.setMiddleLevelCount(levelCount);
                break;
            case "2":
                ilm.setHighLevel(level);
                ilm.setHighLevelCount(levelCount);
                break;
        }
        return ilm;
    }

}
