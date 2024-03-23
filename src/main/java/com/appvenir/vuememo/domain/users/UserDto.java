package com.appvenir.vuememo.domain.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, message = "First name must be at least 2 characters long")
    @Size(max = 20, message = "First name must be at most 20 characters long")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, message = "Last name must be at least 2 characters long")
    @Size(max = 20, message = "Last name must be at most 20 characters long")
    private String lastName;

    @Email(message = "Not a valid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    public UserDto(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}
