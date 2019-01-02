package com.qiniu.csvc.base;

import com.google.gson.annotations.SerializedName;


public class BaseResponseResult<T> {

    @SerializedName("result") private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseResponseResult{" +
                "result=" + result +
                '}';
    }
}
