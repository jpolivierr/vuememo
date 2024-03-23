package com.appvenir.vuememo.domain.users;

import com.appvenir.vuememo.entity.baseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User extends BaseEntity{

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, message = "First name must be at least 2 characters long")
    @Size(max = 20, message = "First name must be at most 20 characters long")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, message = "Last name must be at least 2 characters long")
    @Size(max = 20, message = "Last name must be at most 20 characters long")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Not a valid email")
    @NotBlank(message = "Email cannot be empty")
    @Column(nullable = false, name = "email", unique = true)
    private String email;
    
}
