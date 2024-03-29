package com.example.fcb.claim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.fcb.claim.error.ClaimExceptionHandler;
import com.example.fcb.claim.error.ClaimNotFoundException;
import com.example.fcb.claim.service.ClaimEntity;
import com.example.fcb.claim.service.ClaimData;
import com.example.fcb.claim.service.ClaimRepository;
import com.example.fcb.claim.service.ClaimService;
import com.example.fcb.request.ClaimRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
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

@ExtendWith(MockitoExtension.class)
public class ClaimEntityControllerTests {

    @InjectMocks
    ClaimService service;
    private MockMvc mockMvc;
    @Mock
    private ClaimRepository claimRepository;
    @Mock
    private ClaimRequest claimRequest;

    @BeforeEach
    public void setUp() {
        // RestControllerAdvice classes should also be included otherwise it would take effect when testing
        ClaimExceptionHandler claimExceptionHandler = new ClaimExceptionHandler();
        ClaimController claimController = new ClaimController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(claimController, claimExceptionHandler).build();
    }

    @Test
    public void submitClaimShouldCallRepositorySaveAndReturnStatusOk() throws Exception {
        ClaimData data = new ClaimData("123456", "XXXXXX", "YYYYYY", "the claim amount is 2000USD!");
        String json = new ObjectMapper().writeValueAsString(data);
        when(claimRepository.save(any())).thenReturn(new ClaimEntity(1L, "123456","XXXXXX", "YYYYYY", "message"));

        mockMvc.perform(post("/claims")
                .contentType(MediaType.APPLICATION_JSON).content(json))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").isNumber());

        ArgumentCaptor<ClaimEntity> entity = ArgumentCaptor.forClass(ClaimEntity.class);
        verify(claimRepository).save(entity.capture());
        assertEquals("XXXXXX", entity.getValue().getSender());
    }

    @Test
    public void sendClaimMessageShouldFindClaimAndInitiateClaimRequestThenReturnStatusOk()
        throws Exception {
        ClaimEntity claim = new ClaimEntity(100L, "123456", "FCBFCB", "SYSTEX", "claim message content");
        when(claimRepository.findById(100L)).thenReturn(Optional.of(claim));

        mockMvc.perform(put("/claims/100/send"))
            .andExpect(status().isOk());

        ArgumentCaptor<ClaimEntity> entity = ArgumentCaptor.forClass(ClaimEntity.class);
        verify(claimRequest).send(entity.capture());
        assertEquals(100L, entity.getValue().getId());
    }

    @Test
    public void sendClaimMessageNotExistsThrowException() throws Exception {
        when(claimRepository.findById(404L)).thenReturn(Optional.empty());
        mockMvc.perform(put("/claims/404/send"))
            .andExpect(result -> assertTrue(
                result.getResolvedException() instanceof ClaimNotFoundException))
            .andExpect(status().isNotFound());
    }
}
