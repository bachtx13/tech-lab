package com.sem4project.sem4.dto.request;

import com.sem4project.sem4.annotation.validation.PasswordMatchConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@PasswordMatchConstraint(field = "password",
        fieldMatch = "reEnterPassword",
        message = "Passwords do not match!")
public class RegisterRequest {
    @NotNull(message = "Email is required")
    @Email
    private String email;
    @NotNull(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$", message = "Password must be contain a UPPERCASE, a lowercase, a number, a special character and at least 8 letter")
    private String password;
    @NotNull(message = "Password is required")
    private String reEnterPassword;
}
