package com.example.fcb.play.wrapper;

public interface RequestBody<T extends RequestBody<T>> {

    @SuppressWarnings("unchecked")
    default RequestWrapper<T> wrap() {
        return new RequestWrapper<T>((T) this);
    }
}
