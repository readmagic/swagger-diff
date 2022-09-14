package oop.fun.render;

public class SuccessResult<T> extends BaseResult<T> {
    public SuccessResult(T response, Integer code, String info) {
        this.setResponse(response);
        this.setCode(code);
        this.setInfo(info);
    }

    public SuccessResult() {
    }
}
