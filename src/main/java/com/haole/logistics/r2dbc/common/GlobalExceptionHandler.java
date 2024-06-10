package com.haole.logistics.r2dbc.common;

import com.haole.logistics.r2dbc.enums.ResultType;
import com.haole.logistics.r2dbc.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: GlobalExceptionHandler
 *
 * @description:
 * ResponseEntityExceptionHandler中列出了需要处理的异常
 * @author: shengjunzhao
 * @date: 2024/4/8 0008 16:05
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(WebExchangeBindException.class)
    public ActionResult<String> handleWebExchangeBindException(WebExchangeBindException e) {
        logger.warn("[handleWebExchangeBindException]", e);
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ActionResult.fail(ResultType.METHOD_ARGUMENT_NOT_VALID.code(), JSON.toJSONString(errors));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ActionResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.warn("参数错误 {}", e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        return ActionResult.fail(ResultType.METHOD_ARGUMENT_NOT_VALID.code(), error.getDefaultMessage());
    }

    @ExceptionHandler(value =MethodArgumentTypeMismatchException.class)
    public ActionResult<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.warn("参数类型错误 {}", e.getMessage());
        return ActionResult.fail(ResultType.METHOD_ARGUMENT_TYPE_MISMATCH.code(),
                ResultType.METHOD_ARGUMENT_TYPE_MISMATCH.message());
    }

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

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ActionResult<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.warn("参数解析错误 {}", e.getMessage());
        return ActionResult.fail(ResultType.MESSAGE_NOT_READABLE.code(),
                ResultType.MESSAGE_NOT_READABLE.message());
    }


    @ExceptionHandler(value = BindException.class)
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

    @ExceptionHandler(value = HandlerMethodValidationException.class)
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public <T> ActionResult<T> handlerException(Exception e) {
        // 记录异常日志
        logger.warn("[handlerException]", e);
        // 返回 ERROR ActionResult
        return ActionResult.fail(ResultType.SYS_ERROR.code(),
                ResultType.SYS_ERROR.message());
    }
}
