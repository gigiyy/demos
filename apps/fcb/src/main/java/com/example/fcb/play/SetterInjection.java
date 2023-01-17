package com.example.fcb.play;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SetterInjection {

    private String strValue;
    private String strValueMethod;
    private int intValue;

    @Autowired
    public void setStrValue(@Value("${value.string}") String input) {
        strValue = input;
    }

    @Value("${value.string-method}")
    public void setStrValueMethod(String input) {
        strValueMethod = input;
    }

    @Autowired
    public void setIntValue(@Value("${value.int-value}") int input) {
        intValue = input;
    }
}
