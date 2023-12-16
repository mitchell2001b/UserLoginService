package com.example.UserLoginService.Account;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "account")
@NamedQuery(name = "Account.findByEmailAndPassword", query = "SELECT a FROM Account a WHERE a.email = :email AND a.password = :password")
public class Account
{
    @jakarta.persistence.Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 40)
    private String password;

    @Column(unique=true, nullable = false, length = 20)
    private String email;

    private Long roleId;

    private String roleName;

    public Account(Long idGiven, String passWord, String emailGiven, Long roleId, String roleName)
    {
        this.id = idGiven;
        this.password = passWord;
        this.email = emailGiven;
        this.roleId = roleId;
        this.roleName = roleName;
    }
    public Account(String passWord, String emailGiven, Long roleId, String roleName)
    {
        this.password = passWord;
        this.email = emailGiven;
        this.roleId = roleId;
        this.roleName = roleName;
    }


    public Account()
    {

    }

    public Long getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    public Long getRoleId()
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
                "Id=" + id +
                ", Password='" + password + '\'' +
                ", Email='" + email + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
