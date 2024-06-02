package com.haole.logistics.r2dbc.enums;

/**
 * ClassName: ResultType
 *
 * @description:
 * @author: shengjunzhao
 * @date: 2024/4/8 0008 15:15
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public enum ResultType implements ErrorType {

    SUCCESS("0", "成功"),
    FAILURE("-1", "失败"),
    SYS_ERROR("2001001000",  "服务端发生异常"),
    MISSING_REQUEST_PARAM_ERROR("2001001001", "参数缺失");

    private final String code;
    private final String message;

    private ResultType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
