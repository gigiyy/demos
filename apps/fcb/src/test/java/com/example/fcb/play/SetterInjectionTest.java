package com.example.fcb.play;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SetterInjectionTest {

    @Autowired
    SetterInjection injection;
    @Test
    public void testSetterInjection() {
        assertThat(injection.getStrValueMethod()).isEqualTo("world");
        assertThat(injection.getStrValue()).isEqualTo("hello");
        assertThat(injection.getIntValue()).isEqualTo(10);
    }
}