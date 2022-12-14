package com.example.fcb.play.wrapper;

public interface ResponseBody<T extends ResponseBody<T>> {

    default ResponseWrapper<T> wrap() {
        return new ResponseWrapper<>((T) this);
    }
}
