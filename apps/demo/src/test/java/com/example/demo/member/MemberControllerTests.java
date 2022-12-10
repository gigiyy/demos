package com.example.demo.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerTests {

    private MockMvc mockMvc;

    private MemberController memberController;

    @BeforeEach
    public void setup() {
        memberController = new MemberController();
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void getMember_should_return200Ok() throws Exception {
        mockMvc.perform(get("/members/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void createMember_should_return201Created() throws Exception {
        mockMvc.perform(post("/members")).andExpect(status().isCreated());
    }
}
