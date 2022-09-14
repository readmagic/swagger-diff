package oop.fun.render;


public class BaseResult<T> {
    private Integer code;
    private String info;
    private T response;
    
    public BaseResult() {
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    
    public T getResponse() {
        return response;
    }
    
    public void setResponse(T response) {
        this.response = response;
    }
}
