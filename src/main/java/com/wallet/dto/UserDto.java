package com.wallet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    private Long id;

    @NotNull
    @Length(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    private String name;

    @Email(message = "Email inválido")
    @NotNull
    private String email;

    @NotNull
    @Length(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    private String password;
}
