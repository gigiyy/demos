package com.example.fcb.play.state;

import java.util.HashMap;
import java.util.Map;

class BaseState {
    private static final ThreadLocal<Map<String, String>> state = ThreadLocal.withInitial(HashMap::new);

    public void setUuid(String uuid) {
        // thread pool will keep the data from previous thread
        // assert Strings.isEmpty(getUuid());
        BaseState.state.get().put("uuid", uuid);
    }

    public String getUuid() {
        String uuid = BaseState.state.get().get("uuid");
        return uuid == null ? "" : uuid;
    }
}
