package com.sogou.upd.passport.admin.common.utils.taglib;

public enum ResourceType {

    /**
     * java script
     */
    JAVA_SCRIPT("javascript"),
    /**
     * css
     */
    CSS("css");

    /**
     * 资源类型的名称
     */
    private final String name;

    ResourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
