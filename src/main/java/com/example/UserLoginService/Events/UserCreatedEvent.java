package com.example.UserLoginService.Events;

import java.io.Serializable;
import java.time.LocalDate;

public class UserCreatedEvent implements Serializable
{
    private Long Id;

    private String PassWord;

    private String Email;

    private Long RoleId;
    private String RoleName;

    private LocalDate CreatedAt;

    public UserCreatedEvent(Long id, String passWord, String email, Long roleId, String roleName, LocalDate createdAt) {
        Id = id;
        PassWord = passWord;
        Email = email;
        RoleId = roleId;
        RoleName = roleName;
        CreatedAt = createdAt;
    }
    public UserCreatedEvent()
    {

    }

    public Long getId() {
        return Id;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getEmail() {
        return Email;
    }

    public Long getRoleId() {
        return RoleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public LocalDate getCreatedAt() {
        return CreatedAt;
    }

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "Id=" + Id +
                ", PassWord='" + PassWord + '\'' +
                ", Email='" + Email + '\'' +
                ", RoleId=" + RoleId +
                ", RoleName='" + RoleName + '\'' +
                ", CreatedAt=" + CreatedAt +
                '}';
    }
}
