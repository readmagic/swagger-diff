package oop.fun.render;

public class FailResult<T> extends BaseResult<T> {

    public FailResult(Integer code, String info) {
        this.setCode(code);
        this.setInfo(info);
    }

    public FailResult() {
    }
}
