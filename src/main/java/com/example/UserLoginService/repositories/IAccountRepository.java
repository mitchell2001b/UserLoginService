package com.example.UserLoginService.repositories;

import com.example.UserLoginService.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IAccountRepository extends JpaRepository<Account, Integer>
{
    @Query(value = "SELECT * FROM `account` ORDER BY `id` DESC LIMIT 1", nativeQuery = true)
    public Account findLastCreatedAccount();

    @Query(value = "SELECT * FROM `account` WHERE `email` = ?1 AND `password` = ?2", nativeQuery = true)
    public Account loginAccount(String email, String password);

    @Query(value = "SELECT * FROM `account` WHERE id = ?1 AND email = ?2", nativeQuery = true)
    public Account FindAccount(long id, String email);
    @Query(value = "SELECT * FROM `account` WHERE email = ?1", nativeQuery = true)
    public Account FindAccount(String email);
}