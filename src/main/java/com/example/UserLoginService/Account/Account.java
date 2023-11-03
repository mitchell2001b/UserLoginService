package com.example.UserLoginService.Account;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "account")
public class Account
{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String Password;

    @Column(unique=true)
    private String Email;

    private int roleId;

    private String roleName;

    public Account(int id, String password, String email, int roleId, String roleName)
    {
        Id = id;
        Password = password;
        Email = email;
        this.roleId = roleId;
        this.roleName = roleName;
    }
    public Account(String password, String email, int roleId, String roleName)
    {
        Password = password;
        Email = email;
        this.roleId = roleId;
        this.roleName = roleName;
    }
    public Account()
    {

    }

    public int getId()
    {
        return Id;
    }

    public String getPassword()
    {
        return Password;
    }

    public String getEmail()
    {
        return Email;
    }

    public int getRoleId()
    {
        return roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
