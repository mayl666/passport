package com.sogou.upd.passport.admin.common.enums;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-26
 * Time: 上午10:57
 * To change this template use File | Settings | File Templates.
 */
public enum ProblemStateEnum {
    NOTANSWER(0), //未回答
    ANSWERED(1), // 已回答
    CLOSED(2);// 关闭

    private int value;

    ProblemStateEnum(int value) {
        this.value = value;
    }

    public static ProblemStateEnum getProblemState(int state) {
        if (state == 1) {
            return ProblemStateEnum.ANSWERED;
        } else if (state == 2) {
            return ProblemStateEnum.CLOSED;
        } else {
            return ProblemStateEnum.NOTANSWER;
        }
    }
}
