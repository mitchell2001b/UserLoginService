package com.example.UserLoginService.Account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/accounts")
public class AccountController
{
    @GetMapping(value = "/login")
    public String Login()
    {
        return "user logging in";
    }
}
