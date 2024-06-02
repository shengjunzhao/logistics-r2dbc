package com.haole.logistics.r2dbc.common;

import com.haole.logistics.r2dbc.enums.ResultType;

import java.io.Serial;

/**
 * ClassName: BusinessException
 *
 * @description:
 * @author: shengjunzhao
 * @date: 2024/4/8 0008 15:59
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 387319997414909724L;
    /**
     * 错误码
     */
    private final String code;

    public BusinessException(ResultType resultType) {
        super((resultType.message()));
        this.code = resultType.code();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
