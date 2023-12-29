package com.sem4project.sem4.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class LoginRequest {
    @Email
    @NotNull
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    private String password;
}
