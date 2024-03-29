package com.msig.school.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msig.school.backend.enums.RoleType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto extends BaseDto implements Serializable {
    private String username;
    private String email;
    @JsonIgnore
    private String hashedPassword;
    private RoleType roleType;
}
