package com.example.fcb.play;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

@SpringBootTest
@Import({TestChannelBinderConfiguration.class})
class StringProcessorTest {

    @Autowired
    InputDestination uppercase_in_0;

    @Autowired
    OutputDestination uppercase_out_0;

    @Test
    void testStringProcessing() {
        uppercase_in_0.send(new GenericMessage<>("hello".getBytes()));
        assertThat(uppercase_out_0.receive().getPayload()).isEqualTo("HELLO".getBytes());
    }
}