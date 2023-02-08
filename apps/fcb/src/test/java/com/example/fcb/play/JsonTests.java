package com.example.fcb.play;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonTests {

    private final Logger logger = LoggerFactory.getLogger(JsonTests.class);

    @Test
    public void retrieveNestedObject() throws JsonProcessingException {
        // language=json
        String json = """
                {
                    "tx-id": "txid",
                    "user": "username",
                    "body": {
                        "ok": "good",
                        "number": 100
                    }
                }
                """;

        Data data = convertPartialOf(json, Data.class);
        logger.info("ok is {}, number is {}", data.ok, data.number);

    }

    private <T> T convertPartialOf(String json, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<>() {
        });

        logger.debug("{}", map.get("body"));

        String bodyStr = objectMapper.writeValueAsString(map.get("body"));

        return objectMapper.readValue(bodyStr, clazz);
    }

    static class Data {

        private String ok;
        private int number;

        public String getOk() {
            return ok;
        }

        public void setOk(String ok) {
            this.ok = ok;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
