package com.haole.logistics.r2dbc.common;

import com.haole.logistics.r2dbc.enums.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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
     * 处理 ServerWebInputException 异常
     * <p>
     * WebFlux 参数不正确
     */
    @ExceptionHandler(value = ServerWebInputException.class)
    public ActionResult<String> handleServerWebInputException(ServerWebInputException ex) {
        logger.warn("[handleServerWebInputException]", ex);
        // 包装 ActionResult 结果
        return ActionResult.fail(ResultType.MISSING_REQUEST_PARAM_ERROR.code(),
                ResultType.MISSING_REQUEST_PARAM_ERROR.message());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ActionResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.warn("参数错误 {}", e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        return ActionResult.fail(ResultType.METHOD_ARGUMENT_NOT_VALID.code(), error.getDefaultMessage());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ActionResult<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.warn("参数类型错误 {}", e.getMessage());
        return ActionResult.fail(ResultType.METHOD_ARGUMENT_TYPE_MISMATCH.code(),
                ResultType.METHOD_ARGUMENT_TYPE_MISMATCH.message());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ActionResult<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.warn("参数解析错误 {}", e.getMessage());
        return ActionResult.fail(ResultType.MESSAGE_NOT_READABLE.code(),
                ResultType.MESSAGE_NOT_READABLE.message());
    }


    @ExceptionHandler({BindException.class})
    public ActionResult<String> handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        ActionResult<String> result = ActionResult.fail(ResultType.FAILURE.code(), e.getMessage());
        if (fieldError != null) {
            logger.warn("参数绑定失败 {}", fieldError.getDefaultMessage());
            result.setMessage(fieldError.getDefaultMessage());
        }
        return result;
    }

    @ExceptionHandler({HandlerMethodValidationException.class})
    public ActionResult<String> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        logger.warn("方法错误 {}", e.getMessage());
        return ActionResult.fail(ResultType.METHOD_ARGUMENT_TYPE_MISMATCH.code(), e.getMessage());
    }

    /**
     * 处理 BusinessException 异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public <T> ActionResult<T> handlerBusinessException(BusinessException ex) {
        logger.warn("[handlerBusinessException]", ex);
        // 包装 ActionResult 结果
        return ActionResult.fail(ex.getCode(), ex.getMessage());
    }


    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public <T> ActionResult<T> handlerException(Exception e) {
        // 记录异常日志
        logger.warn("[handlerException]", e);
        // 返回 ERROR ActionResult
        return ActionResult.fail(ResultType.SYS_ERROR.code(),
                ResultType.SYS_ERROR.message());
    }
}
