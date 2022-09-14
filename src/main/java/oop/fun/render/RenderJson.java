package oop.fun.render;


import cn.hutool.json.JSONUtil;
import oop.fun.constant.ResultCodeConst;

public class RenderJson {


    public static String success() {
        SuccessResult response = new SuccessResult("成功", ResultCodeConst.OK, "success");
        return JSONUtil.toJsonStr(response);
    }

    public static <T> String success(T any) {
        SuccessResult response = new SuccessResult<T>(any, ResultCodeConst.OK, "success");
        return JSONUtil.toJsonStr(response);
    }

    public static <T> String fail(String errorMsg) {
        FailResult response = new FailResult(ResultCodeConst.FAIL, errorMsg);
        return JSONUtil.toJsonStr(response);
    }


    public static <T> String fail(String errorMsg, Integer code) {
        FailResult response = new FailResult(code, errorMsg);
        return JSONUtil.toJsonStr(response);
    }

    public static <T> String error(String errorMsg) {
        FailResult response = new FailResult(ResultCodeConst.ERROR, errorMsg);
        return JSONUtil.toJsonStr(response);
    }
}
