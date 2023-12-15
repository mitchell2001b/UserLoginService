package com.example.UserLoginService.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class AccountRoleDto implements Serializable
{
    @JsonProperty("id")
    private Long Id;
    @JsonProperty("name")
    private String Name;

    public AccountRoleDto(Long id, String name)
    {
        Id = id;
        Name = name;
    }
    public AccountRoleDto(String name)
    {
        Name = name;
    }

    public AccountRoleDto()
    {

    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountRoleDto entity = (AccountRoleDto) o;
        return Objects.equals(this.Id, entity.Id) &&
                Objects.equals(this.Name, entity.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "Id = " + Id + ", " +
                "Name = " + Name + ")";
    }
}
