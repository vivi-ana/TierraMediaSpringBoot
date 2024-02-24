package com.example.TierraMediaSpringBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_credential")
public class UserCredentialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @NotNull
    @NotBlank(message = "Please complete this field")
    @Size(max = 30, message = "Name is too long")
    @Size(min = 5, message = "Name is too short")
    private String username;

    @NotNull
    @NotBlank(message = "Please complete this field")
    @Size(max = 30, message = "Password is too long")
    @Size(min = 3, message = "Password is too short")
    private String password;

    @ManyToOne (fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @NotNull
    @JoinColumn (name = "fk_id_role")
    private RoleEntity role;
}
