package com.appvenir.vuememo.domain.users.dto;

import com.appvenir.vuememo.domain.users.model.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    

    private String name;

    private String email;

    public UserDto(User user){
        this.name = user.getName();
        this.email = user.getEmail();
    }
    
}
