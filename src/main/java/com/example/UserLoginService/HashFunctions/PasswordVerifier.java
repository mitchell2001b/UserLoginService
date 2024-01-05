package com.example.UserLoginService.HashFunctions;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordVerifier
{
    public boolean VerifyPassword(String enteredPassword, String storedHashedPassword)
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(enteredPassword, storedHashedPassword);
    }

}
