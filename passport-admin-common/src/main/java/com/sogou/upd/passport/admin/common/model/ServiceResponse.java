package com.sogou.upd.passport.admin.common.model;

import java.io.Serializable;

/**
 * 服务响应类
 * User: chengang
 * Date: 14-6-25
 * Time: 下午6:03
 */
public class ServiceResponse<T> implements Serializable {

    private static final long serialVersionUID = -7499998444420182552L;

    /**
     * 调用结果成功还是失败
     */
    private final boolean success;

    /**
     * 如果失败，失败的Key值
     */
    private final int errKey;

    /**
     * 封装的返回数据
     */
    private T responseData;

    public ServiceResponse(boolean success, int errKey, T responseData) {
        this.success = success;
        this.errKey = errKey;
        this.responseData = responseData;
    }

    public ServiceResponse(T responseData) {
        this.success = true;
        this.errKey = 0;
        this.responseData = responseData;
    }

    public T getResponseData() {
        return responseData;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getErrKey() {
        return errKey;
    }

    /**
     * 构建错误的响应
     *
     * @param errKey
     * @return
     */
    public static <T> ServiceResponse<T> genFailResponse(int errKey) {
        return new ServiceResponse<T>(false, errKey, null);
    }

    /**
     * 构建正确的响应，用于没有响应数据
     *
     * @return
     */
    public static <T> ServiceResponse<T> genSuccResponseWithoutData() {
        return new ServiceResponse<T>(true, 0, null);
    }


}
