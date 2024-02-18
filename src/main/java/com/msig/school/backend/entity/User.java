package com.msig.school.backend.entity;

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
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "hashedPassword", nullable = false)
    private String hashedPassword;
}
