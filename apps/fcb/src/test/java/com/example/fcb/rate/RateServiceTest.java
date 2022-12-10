package com.example.fcb.rate;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RateServiceTest {
    @Test
    public void testMap() {

        List<Message> messages = List.of(new Message(1, "AA"), new Message(2, "BB"));
        List<Message> updated = new RateService().updateMessage(messages);

        assertThat(updated).isEqualTo(List.of(new Message(2, "AA")));
    }

}