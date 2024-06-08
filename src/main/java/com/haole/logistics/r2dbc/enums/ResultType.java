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
    METHOD_ARGUMENT_NOT_VALID("-99001", "参数错误"),
    METHOD_ARGUMENT_TYPE_MISMATCH("-99002", "参数类型错误"),
    MESSAGE_NOT_READABLE("-99003", "参数解析错误"),
    MISSING_REQUEST_HEADER("-99004", "缺少请求头"),
    REQUEST_METHOD_NOT_SUPPORTED("-99005", "请求方法不支持"),
    MEDIA_TYPE_NOT_SUPPORTED("-99006", "不支持的媒体类型"),
    SYS_ERROR("-99007",  "服务端发生异常"),
    MISSING_REQUEST_PARAM_ERROR("-99008", "参数缺失");

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
