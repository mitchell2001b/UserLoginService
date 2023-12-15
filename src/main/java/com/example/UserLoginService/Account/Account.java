package com.example.UserLoginService.Account;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "account")
@NamedQuery(name = "Account.findByEmailAndPassword", query = "SELECT a FROM Account a WHERE a.email = :email AND a.password = :password")
public class Account
{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Column(unique=true)
    private String email;

    private Long roleId;

    private String roleName;

    public Account(Long idGiven, String passWord, String emailGiven, Long roleId, String roleName)
    {
        id = idGiven;
        password = passWord;
        email = emailGiven;
        this.roleId = roleId;
        this.roleName = roleName;
    }
    public Account(String passWord, String emailGiven, Long roleId, String roleName)
    {
        password = passWord;
        email = emailGiven;
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
