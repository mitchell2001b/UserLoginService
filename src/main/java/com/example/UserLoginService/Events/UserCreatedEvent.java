package com.example.UserLoginService.Events;

import java.io.Serializable;
import java.time.LocalDate;

public class UserCreatedEvent implements Serializable
{
    private int Id;

    private String PassWord;

    private String Email;

    private int RoleId;
    private String RoleName;

    private LocalDate CreatedAt;

    public UserCreatedEvent(int id, String passWord, String email, int roleId, String roleName, LocalDate createdAt) {
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

    public int getId() {
        return Id;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getEmail() {
        return Email;
    }

    public int getRoleId() {
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
