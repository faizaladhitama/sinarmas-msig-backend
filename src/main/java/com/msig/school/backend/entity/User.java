package com.msig.school.backend.entity;

import com.msig.school.backend.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "UserEntity")
@Table(name = "user")
public class User extends Base implements Serializable {
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "hashedPassword", nullable = false)
    private String hashedPassword;
    @Column(name = "roleType", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
