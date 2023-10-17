package com.example.UserLoginService.dtos;

import java.io.Serializable;
import java.time.LocalDate;

public class AccountLoginDto implements Serializable
{
    private int Id;

    private String PassWord;

    private String Email;

    private AccountRoleDto RoleDto;

    public AccountLoginDto(int id, String passWord, String email, AccountRoleDto roleDto) {
        Id = id;
        PassWord = passWord;
        Email = email;
        RoleDto = roleDto;
    }

    public AccountLoginDto()
    {

    }

    public int getId() {
        return Id;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getEmail() {
        return Email;
    }

    public AccountRoleDto getRoleDto() {
        return RoleDto;
    }

    @Override
    public String toString() {
        return "AccountLoginDto{" +
                "Id=" + Id +
                ", PassWord='" + PassWord + '\'' +
                ", Email='" + Email + '\'' +
                ", RoleDto=" + RoleDto +
                '}';
    }
}
