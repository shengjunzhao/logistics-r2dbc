package com.haole.logistics.r2dbc.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haole.logistics.r2dbc.enums.ErrorType;
import com.haole.logistics.r2dbc.enums.ResultType;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: ActionResult
 *
 * @description:
 * @author: shengjunzhao
 * @date: 2024/4/8 0008 15:09
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SuppressWarnings("unchecked")
public class ActionResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private T data;

    public ActionResult() {
    }

    public ActionResult(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return ResultType.SUCCESS.code().equals(this.code);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }


    public static <T> ActionResult<T> success() {
        return (ActionResult<T>) builder().build();
    }

    public static <T> ActionResult<T> success(T data) {
        return (ActionResult<T>) builder().data(data).build();
    }

    public static <T> ActionResult<T> success(String msg, T data) {
        return (ActionResult<T>) builder().message(msg).data(data).build();
    }

    public static <T> ActionResult<T> fail(String message) {
        return (ActionResult<T>) builder().code(ResultType.FAILURE.code()).message(message).build();
    }

    public static <T> ActionResult<T> fail(ErrorType errorType) {
        return (ActionResult<T>) builder().code(errorType.code()).message(errorType.message()).build();
    }

    public static <T> ActionResult<T> fail(ErrorType errorType, String message) {
        return (ActionResult<T>) builder().code(errorType.code()).message(message).build();
    }

    public static <T> ActionResult<T> fail(String errorCode, String message) {
        return (ActionResult<T>) builder().code(errorCode).message(message).build();
    }

    public static <T> ActionResult<T> fail(String errorCode, String message, T data) {
        return (ActionResult<T>) builder().code(errorCode).message(message).data(data).build();
    }

    public static class Builder<T> {
        private String code;
        private String message;
        private T data;

        public Builder() {
            // this constructor is empty
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> resultType(ErrorType errorType) {
            this.code = errorType.code();
            this.message = errorType.message();
            return this;
        }

        public ActionResult<T> build() {
            this.initDefaultValue(this);
            return new ActionResult<>(this);
        }

        private void initDefaultValue(Builder<T> builder) {
            if (builder.code == null) {
                builder.code = ResultType.SUCCESS.code();
                builder.message = "ok";
            }

        }
    }
}
