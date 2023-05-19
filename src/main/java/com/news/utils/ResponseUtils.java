package com.news.utils;

import com.news.constants.ResponseCode;

/**
 * @author Sancean
 * @Date 2023/05/03 13:37
 **/
public class ResponseUtils {
    public static <T> Response<T>  defineSuccess(Integer code, T data) {
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<T>();
        response.setCode(ResponseCode.SUCCESS);
        response.setData(data);
        response.setMsg("success.");
        return response;
    }

    public static <T> Response<T> success(String msg) {
        Response<T> response = new Response<T>();
        response.setCode(ResponseCode.SUCCESS);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> fail(String msg) {
        Response<T> response = new Response();
        response.setCode(ResponseCode.FAIL);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> defineFail(int code, String msg){
        Response<T> response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> define(int code, String msg, T data){
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }
}
