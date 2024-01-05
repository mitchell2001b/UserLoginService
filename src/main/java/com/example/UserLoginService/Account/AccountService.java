package com.example.UserLoginService.Account;

import com.example.UserLoginService.HashFunctions.PasswordVerifier;
import com.example.UserLoginService.dtos.AccountLoginDto;
import com.example.UserLoginService.dtos.AccountRoleDto;
import com.example.UserLoginService.kafka.RegistrationConsumer;
import com.example.UserLoginService.repositories.AccountCustomRepository;
import com.example.UserLoginService.repositories.IAccountRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService
{
    private IAccountRepository repoAccount;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationConsumer.class);

    private AccountCustomRepository customRepo;
    @Autowired
    public AccountService(IAccountRepository repo, AccountCustomRepository repoCustom)
    {
        this.repoAccount = repo;
        this.customRepo = repoCustom;
    }

    public void AddAccount(Account newAccount)
    {
        if(newAccount != null)
        {
            repoAccount.save((newAccount));
        }
    }


    @Transactional
    public void DeleteAccount(Account accountToDelete)
    {
        if(accountToDelete != null)
        {
            repoAccount.delete(accountToDelete);
        }
    }

    public Account SelectAccount(long id, String email)
    {
        return repoAccount.FindAccount(id, email);
    }


    public AccountLoginDto Login(String password, String email)
    {
        LOGGER.info(password + " : " + email);

        Account account =  repoAccount.FindAccount(email);

        //repoAccount.loginAccount(email, password);
        if(account == null)
        {
            return null;
        }
        else
        {
            String hashedPassword = account.getPassword();
            PasswordVerifier verifier = new PasswordVerifier();
            if(verifier.VerifyPassword(password, hashedPassword))
            {
                return new AccountLoginDto(account.getId(), account.getEmail(), new AccountRoleDto(account.getRoleId(), account.getRoleName()));
            }
            else
            {
                return null;
            }

        }
    }
}
