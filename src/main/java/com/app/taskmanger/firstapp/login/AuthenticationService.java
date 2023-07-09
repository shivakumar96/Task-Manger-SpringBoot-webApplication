package com.app.taskmanger.firstapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String Password){
        boolean isValidName = username.equalsIgnoreCase("shiva");
        boolean isValidPass = username.equalsIgnoreCase("shiva");

        return isValidName && isValidPass ;
    }
}
