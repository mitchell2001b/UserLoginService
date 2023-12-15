package com.example.UserLoginService.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class AccountLoginDto implements Serializable
{
    @JsonProperty("id")
    private Long Id;

    @JsonProperty("password")
    private String Password;
    @JsonProperty("email")
    private String Email;

    @JsonProperty("role")
    private AccountRoleDto RoleDto;

    public AccountLoginDto(Long id, String passWord, String email, AccountRoleDto roleDto) {
        Id = id;
        Password = passWord;
        Email = email;
        RoleDto = roleDto;
    }
    public AccountLoginDto(Long id, String email, AccountRoleDto roleDto) {
        Id = id;
        Email = email;
        RoleDto = roleDto;
    }

    public AccountLoginDto(String email, String passWord)
    {
        Email = email;
        Password = passWord;
    }

    public AccountLoginDto()
    {

    }

    public Long getId() {
        return Id;
    }

    public String getPassWord() {
        return Password;
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
                ", PassWord='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", RoleDto=" + RoleDto +
                '}';
    }
}
