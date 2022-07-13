package com.example.demo.claim;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClaimControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private ClaimController claimController;

    @Mock
    private ClaimRepository claimRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(claimController).build();
    }

    @Test
    public void submitClaimShouldCallRepositorySaveAndReturnStatusOk() throws Exception {
        ClaimData data = new ClaimData("XXXXXX", "YYYYYY", "the claim amount is 2000USD!");
        String json = new ObjectMapper().writeValueAsString(data);
        when(claimRepository.save(any())).thenReturn(new Claim(1L, "XXXXXX", "YYYYYY", "message"));

        mockMvc.perform(post("/claims")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));

        ArgumentCaptor<Claim> entity = ArgumentCaptor.forClass(Claim.class);
        verify(claimRepository).save(entity.capture());
        assertEquals("XXXXXX", entity.getValue().getSender());
    }
}
