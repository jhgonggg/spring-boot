package com.funtl.hello.spring.boot.config;

import com.alibaba.fastjson.JSONException;
import com.funtl.hello.spring.boot.enums.ErrorEnum;
import com.funtl.hello.spring.boot.response.MsgCode;
import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = UncategorizedSQLException.class)
    public Response uncategorizedSQLException(HttpServletRequest req, UncategorizedSQLException e) {
        log.error("---UncategorizedSQLException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), "请输入符合规则的内容");
    }

    @ExceptionHandler(value = SQLException.class)
    public Response sqlExceptionHandler(HttpServletRequest req, SQLException e) {
        log.error("---SQLException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), "请输入符合规则的内容");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Response baseErrorHandler(HttpServletRequest req, Exception e) {
        log.error("---RuntimeException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), MsgCode.FAIL.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public Response defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error("---DefaultException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), MsgCode.FAIL.getMsg());
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public Response duplicateKeyExceptionHandler(HttpServletRequest req, DuplicateKeyException e) {
        log.error("---DuplicateKeyException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), "已存在重复的记录");
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public Response arithmeticExceptionHandler(HttpServletRequest req, ArithmeticException e) {
        log.error("---arithmeticExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = SouthcnException.class)
    public Response southcnExceptionHandler(HttpServletRequest req, SouthcnException e) {
        log.error("---southcnExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage(), e);
        return ResponseBuilder.buildFail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response illegalArgumentExceptionHandler(HttpServletRequest req, IllegalArgumentException e) {
        log.error("---southcnExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public Response bindExceptionHandler(HttpServletRequest req, BindException e) {
        log.error("---bindExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), ErrorEnum.ARGS_ERROR.getMsg());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("---methodArgumentNotValidException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), ErrorEnum.ARGS_ERROR.getMsg());
    }



    @ExceptionHandler(value = ConstraintDefinitionException.class)
    public Response constraintDefinitionExceptionHandler(HttpServletRequest req, ConstraintDefinitionException e) {
        log.error("---constraintDefinitionExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException e) {
        log.error("---constraintViolationExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), ErrorEnum.ARGS_ERROR.getMsg());
    }

    @ExceptionHandler(value = JSONException.class)
    public Response jSONExceptionHandler(HttpServletRequest req, JSONException e) {
        log.error("---jSONExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e);
        return ResponseBuilder.buildFail(MsgCode.FAIL.getCode(), "json转换异常");
    }

}