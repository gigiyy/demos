package com.example.fcb.play;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@Import({TestChannelBinderConfiguration.class})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class StringProcessorTest {

    @Autowired
    InputDestination input;

    @Autowired
    OutputDestination output;

    @Autowired
    StreamBridge streamBridge;

    @Test
    void testSendMessage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new StringController(streamBridge))
            .build();
        mockMvc.perform(post("/messages").content("message sent"))
            .andExpect(status().isAccepted());

        assertThat(output.receive(100, "string-process-input").getPayload())
            .isEqualTo("message sent".getBytes());
    }

    @Test
    void testStringProcessing() {
        input.send(new GenericMessage<>("hello"), "string-process-input");
        assertThat(output.receive(100, "string-process-output").getPayload())
            .isEqualTo("HELLO".getBytes());
    }
}