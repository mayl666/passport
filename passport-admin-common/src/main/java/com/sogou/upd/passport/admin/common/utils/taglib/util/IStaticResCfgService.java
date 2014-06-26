package com.sogou.upd.passport.admin.common.utils.taglib.util;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chengang
 * Date: 14-6-26
 * Time: 下午7:30
 */
public interface IStaticResCfgService {

    /**
     * 获取js资源配置map
     *
     * @return
     */
    Map<String, String> getJsResCfgMap();

    /**
     * 获取css资源配置map
     *
     * @return
     */
    Map<String, String> getCssResCfgMap();

    /**
     * 获取公共css资源配置map
     *
     * @return
     */
    Map<String, String> getCssResCommMap();

    /**
     * 获取公共js资源配置map
     *
     * @return
     */
    Map<String, String> getJsResCommMap();

    /**
     * 获取公共图片资源配置Map
     *
     * @return
     */
    Map<String, String> getImgResCfgMap();

    /**
     * 获取公共图片资源配置Map
     *
     * @return
     */
    Map<String, String> getImgResCommMap();
}
