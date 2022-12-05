package com.example.demo.state;

import java.util.HashMap;
import java.util.Map;

public class BaseState {
    private static final ThreadLocal<Map<String, String>> state = ThreadLocal.withInitial(HashMap::new);

    public void setUuid(String uuid) {
        BaseState.state.get().put("uuid", uuid);
    }

    public String getUuid() {
        String uuid = BaseState.state.get().get("uuid");
        return uuid == null ? "" : uuid;
    }
}
