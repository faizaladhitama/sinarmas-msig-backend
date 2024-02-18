package com.msig.school.backend.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDto {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirmation;
}
