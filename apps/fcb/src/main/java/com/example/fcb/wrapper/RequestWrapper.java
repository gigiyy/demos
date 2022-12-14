package com.example.fcb.wrapper;

public class RequestWrapper<T extends RequestBody> {
    T body;

    public RequestWrapper() {

    }

    public RequestWrapper(T clientRequest) {
        body = clientRequest;
    }

    public T unwrap() {
        return body;
    }
}
