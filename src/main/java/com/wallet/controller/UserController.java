package com.wallet.controller;

import javax.validation.Valid;

import com.wallet.dto.UserDto;
import com.wallet.entity.User;
import com.wallet.service.UserService;
import com.wallet.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Response<UserDto>> create(@Valid @RequestBody UserDto dto, BindingResult result) {
        Response<UserDto> response = new Response<UserDto>();

        User user = service.save(convertDtoToEntity(dto));

        response.setData(convertEntityToDto(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private User convertDtoToEntity(UserDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    private UserDto convertEntityToDto(User user){
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());

        return dto;
    }

}
