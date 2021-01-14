package com.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDto;
import com.wallet.entity.User;
import com.wallet.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
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


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    private static final Long ID = 1L;
    private static final String NAME = "John Doe";
    private static final String EMAIL = "johndoe@mail.com";
    private static final String PASSWORD = "mysecret";
    private static final String URL = "/user";
    @Test
    public void testSave() throws Exception {

        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

        mvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(getJsonPayload(ID, NAME, EMAIL, PASSWORD))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
         .andExpect(jsonPath("$.data.id").value(ID))
         .andExpect(jsonPath("$.data.name").value(NAME))
         .andExpect(jsonPath("$.data.email").value(EMAIL))
         .andExpect(jsonPath("$.data.password").doesNotExist());

    }

    @Test
    public void testSaveInvalidEmail() throws Exception {

        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

        mvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(getJsonPayload(ID, NAME, "invalid", PASSWORD))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Email inválido"));

    }

    public User getMockUser() {
        User user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        return user;
    }

    public String getJsonPayload(Long id, String name, String email, String password) throws JsonProcessingException {
        UserDto dto = new UserDto();
        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setPassword(password);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dto);
    }
}
