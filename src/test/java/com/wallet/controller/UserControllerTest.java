package com.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDto;
import com.wallet.entity.User;
import com.wallet.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    @MockBean
    UserService service;

    @Autowired
    MockMvc mvc;

    private static final String NAME = "John Doe";
    private static final String EMAIL = "johndoe@mail.com";
    private static final String PASSWORD = "mysecret";
    private static final String URL = "/user";
    @Test
    public void testSave() throws Exception {

        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

        mvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    public User getMockUser() {
        User user = new User();
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        return user;
    }

    public String getJsonPayload() throws JsonProcessingException {
        UserDto dto = new UserDto();
        dto.setName(NAME);
        dto.setEmail(EMAIL);
        dto.setPassword(PASSWORD);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dto);
    }
}
