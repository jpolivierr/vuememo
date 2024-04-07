package com.appvenir.vuememo.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLogin{

    private final String email;
    private final String password;   

}
