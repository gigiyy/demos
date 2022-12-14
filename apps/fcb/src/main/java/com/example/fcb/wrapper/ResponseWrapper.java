package com.example.fcb.wrapper;

public class ResponseWrapper<T extends ResponseBody> {
    T body;

    public ResponseWrapper() {
    }

    public ResponseWrapper(T clientResponse) {
        body = clientResponse;
    }

    public T unwrap() {
        return body;
    }
}
