package com.haole.logistics.r2dbc.common;

import com.haole.logistics.r2dbc.enums.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

/**
 * ClassName: GlobalExceptionHandler
 *
 * @description:
 * @author: shengjunzhao
 * @date: 2024/4/8 0008 16:05
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RestControllerAdvice(basePackages = "com.haole.logistics.controller")
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理 BusinessException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public <T> ActionResult<T> businessExceptionHandler(BusinessException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return ActionResult.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理 ServerWebInputException 异常
     * <p>
     * WebFlux 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = ServerWebInputException.class)
    public <T> ActionResult<T> serverWebInputExceptionHandler(ServerWebInputException ex) {
        logger.debug("[ServerWebInputExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return ActionResult.fail(ResultType.MISSING_REQUEST_PARAM_ERROR.code(),
                ResultType.MISSING_REQUEST_PARAM_ERROR.message());
    }

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public <T> ActionResult<T> exceptionHandler(Exception e) {
        // 记录异常日志
        logger.error("[exceptionHandler]", e);
        // 返回 ERROR CommonResult
        return ActionResult.fail(ResultType.SYS_ERROR.code(),
                ResultType.SYS_ERROR.message());
    }
}
