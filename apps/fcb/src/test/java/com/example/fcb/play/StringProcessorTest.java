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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@Import({TestChannelBinderConfiguration.class})
class StringProcessorTest {

    @Autowired
    InputDestination uppercase_in_0;

    @Autowired
    OutputDestination uppercase_out_0;

    @Autowired
    OutputDestination send_out_0;

    @Autowired
    StreamBridge streamBridge;

    @Test
    void testSendMessage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new StringController(streamBridge)).build();
        mockMvc.perform(post("/messages").content("message sent"))
            .andExpect(status().isAccepted());

        assertThat(send_out_0.receive().getPayload()).isEqualTo("message sent".getBytes());
    }

    @Test
    void testStringProcessing() {
        uppercase_in_0.send(new GenericMessage<>("hello".getBytes()));
        assertThat(uppercase_out_0.receive().getPayload()).isEqualTo("HELLO".getBytes());
    }
}