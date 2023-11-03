package com.example.UserLoginService.Account;

import com.example.UserLoginService.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService
{
    private IAccountRepository repoAccount;
    @Autowired
    public AccountService(IAccountRepository repo)
    {
        this.repoAccount = repo;
    }

    public void AddAccount(Account newAccount)
    {
        if(newAccount != null)
        {
            repoAccount.save((newAccount));
        }
    }
}
