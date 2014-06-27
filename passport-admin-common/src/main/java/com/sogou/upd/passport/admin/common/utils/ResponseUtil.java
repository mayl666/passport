package com.sogou.upd.passport.admin.common.utils;

import com.google.common.base.Strings;
import com.sogou.upd.passport.admin.common.model.FlexigridData;
import com.sogou.upd.passport.admin.common.model.Page;
import com.sogou.upd.passport.admin.common.utils.escapehtml.StdEscapeHTMLSerializerProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.BasicClassIntrospector;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

/**
 * 提供处理controller时返回结果的工具方法
 * User: chengang
 * Date: 14-6-26
 * Time: 下午7:06
 */
public final class ResponseUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * Mapping: 自助平台登录
     */
    public static final String MAPPING_CP_LOGIN = "/login/login";

    /**
     * Mapping:异常
     */
    public static final String MAPPING_ERROR = "/err/systemErr";

    /**
     * Mapping:无权
     */
    public static final String MAPPING_NO_PERMISSION = "/err/purviewErr";

    /**
     * 标准http响应请求协议的key ： 结果标识
     */
    public static final String JSON_KEY_RESPONSE_RESULT = "result";
    /**
     * 标准http响应请求协议的key ： 错误消息
     */
    public static final String JSON_KEY_RESPONSE_MSG = "msg";
    /**
     * 标准http响应请求协议的key ： 数据
     */
    public static final String JSON_KEY_RESPONSE_DATA = "data";

    private ResponseUtil() {
    }

    /**
     * JSON序列化工具
     */
    private final static ObjectMapper JSON_SERIALIZER = new ObjectMapper();

    static {
        JSON_SERIALIZER.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        JSON_SERIALIZER.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * JSON序列化工具(html转码)
     */
    private static final ObjectMapper ESCAPEHTML_SERIALIZER = createObjectMap();

    static {
        ESCAPEHTML_SERIALIZER.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        ESCAPEHTML_SERIALIZER.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 构建html转码用Json序列化器
     *
     * @return
     */
    private static ObjectMapper createObjectMap() {
        try {
            SerializationConfig serializationConfig = new SerializationConfig(BasicClassIntrospector.instance,
                    new JacksonAnnotationIntrospector(),
                    VisibilityChecker.Std.defaultInstance(),
                    null, null, TypeFactory.defaultInstance(), null);
            StdEscapeHTMLSerializerProvider stdEscapeHTMLSerializerProvider = new StdEscapeHTMLSerializerProvider(serializationConfig);
            return new ObjectMapper(null, stdEscapeHTMLSerializerProvider, null);
        } catch (Exception e) {
            LOGGER.error("init StdEscapeHTMLSerializerProvider failed!");
        }
        return null;
    }

    /**
     * 未登录处理：
     *
     * @param modelAndView
     */
    public static void handleCpLongin(ModelAndView modelAndView) {
        modelAndView.setViewName(MAPPING_CP_LOGIN);
    }

    /**
     * 失败处理：
     *
     * @param modelAndView
     */
    public static void handleExeception(ModelAndView modelAndView) {
        modelAndView.setViewName(MAPPING_ERROR);
    }


    /**
     * 无权限处理：
     *
     * @param modelAndView
     */
    public static void handleNoPermission(ModelAndView modelAndView) {
        modelAndView.setViewName(MAPPING_NO_PERMISSION);
    }

    /**
     * 构造分页Ajax响应数据
     *
     * @param page
     * @param <T>
     * @return JSON串
     * @throws java.io.IOException
     */
    public static final <T> String ajaxGridResponse(Page<T> page) throws Exception {
        if (page == null) {
            throw new NullPointerException();
        }
        FlexigridData<T> data = new FlexigridData<T>();
        data.setPage(page.getPageIndex());
        data.setTotal(page.getTotal());
        data.setRows(page.getItems());
        return ESCAPEHTML_SERIALIZER.writeValueAsString(data);
    }

    /**
     * 生成标准响应json数据
     *
     * @param modelAndView
     * @param resultFlag
     * @param message
     * @param jsonData
     */
    public static final void genJsonResult(ModelAndView modelAndView, boolean resultFlag, String message, String jsonData) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_RESPONSE_RESULT, resultFlag);
            if (!Strings.isNullOrEmpty(message)) {
                jsonObject.put(JSON_KEY_RESPONSE_MSG, message);
            }
            if (!Strings.isNullOrEmpty(jsonData)) {
                JSONObject dataJsonObject = new JSONObject(jsonData);
                jsonObject.put(JSON_KEY_RESPONSE_DATA, dataJsonObject);
            }
            modelAndView.addObject("resultData", jsonObject);
            modelAndView.setViewName("/commons/resultdata");
        } catch (JSONException e) {
            LOGGER.error("genJsonResult json format error : {}", jsonData, e);
        }
    }

    /**
     * 生成标准错误json返回数据
     *
     * @param modelAndView
     * @param message
     */
    public static void genErrorJsonResult(ModelAndView modelAndView, String message) {
        genJsonResult(modelAndView, false, message, null);
    }

    /**
     * 生成标准错误json返回数据
     *
     * @param modelAndView
     * @param jsonData
     */
    public static void genSuccJsonResult(ModelAndView modelAndView, String jsonData) {
        genJsonResult(modelAndView, true, "", jsonData);
    }

    /**
     * 生成标准错误json返回数据
     *
     * @param modelAndView
     * @param msg
     * @param jsonData
     */
    public static void genSuccJsonResult(ModelAndView modelAndView, String msg, String jsonData) {
        genJsonResult(modelAndView, true, msg, jsonData);
    }


}
