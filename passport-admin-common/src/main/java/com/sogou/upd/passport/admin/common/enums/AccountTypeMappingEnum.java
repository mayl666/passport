package com.sogou.upd.passport.admin.common.enums;

import java.util.List;

/**
 * 用户账号类型映射
 * User: chengang
 * Date: 14-6-26
 * Time: 下午3:10
 */
public enum AccountTypeMappingEnum implements IndexedEnum {

    UNKNOWN(0, "unknow"), //未知

    SOGOU(1, "sogou"), // 搜狗

    SOHU(2, "sohu"), // 搜狐域

    PHONE(3, "phone"), // 手机

    OTHER(4, "other"), // 外域

    THIRD(5, "third"), // 第三方

    INDIVID(6, "individ"); // 个性化


    private static final List<AccountTypeMappingEnum> INDEXS = IndexedEnum.IndexedEnumUtil.toIndexes(AccountTypeMappingEnum.values());

    /**
     * 索引
     */
    private final int index;

    /**
     * 账号类型名称
     */
    private final String accountTypeName;

    AccountTypeMappingEnum(int index, String accountTypeName) {
        this.index = index;
        this.accountTypeName = accountTypeName;
    }

    public int getIndex() {
        return index;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    /**
     * 根据index取得相应的账号类型
     *
     * @param index
     * @return
     */
    public static AccountTypeMappingEnum indexOf(final int index) {
        return IndexedEnumUtil.valueOf(INDEXS, index);
    }

}
