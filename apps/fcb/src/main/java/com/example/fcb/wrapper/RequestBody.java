package com.example.fcb.wrapper;

public interface RequestBody<T extends RequestBody<T>> {

    default RequestWrapper<T> wrap() {
        return new RequestWrapper<T>((T) this);
    }
}
