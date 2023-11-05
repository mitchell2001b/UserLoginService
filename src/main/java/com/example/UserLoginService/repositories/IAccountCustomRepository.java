package com.example.UserLoginService.repositories;

import com.example.UserLoginService.Account.Account;

import java.util.List;

public interface IAccountCustomRepository
{
    Account login(String email, String passWord);
    List<Account> findByEmailAndPassword(String email, String password);
}
