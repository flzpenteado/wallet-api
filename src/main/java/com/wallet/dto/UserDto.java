package com.wallet.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
