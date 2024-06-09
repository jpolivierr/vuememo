package com.appvenir.vuememo.domain.users.model;

import com.appvenir.vuememo.domain.baseEntity.BaseEntity;
import com.appvenir.vuememo.domain.users.dto.UserRegistrationDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(name = "name")
    private String name;


    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "password")
    private String password;
    

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }   

    public User(UserRegistrationDto userRegistration){
        this.name = userRegistration.getFullName();
        this.email = userRegistration.getEmail();
        this.password = userRegistration.getPassword();
    }  
    
}
