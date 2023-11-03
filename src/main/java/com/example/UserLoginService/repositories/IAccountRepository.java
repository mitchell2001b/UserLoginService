package com.example.UserLoginService.repositories;

import com.example.UserLoginService.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAccountRepository extends JpaRepository<Account, Integer>
{
    @Query(value = "SELECT * FROM `account` ORDER BY `id` DESC LIMIT 1", nativeQuery = true)
    public Account findLastCreatedAccount();

    @Query(value = "SELECT * FROM `account` WHERE `email` = ?1 AND `password` = ?2", nativeQuery = true)
    public Account login(String email, String password);
}