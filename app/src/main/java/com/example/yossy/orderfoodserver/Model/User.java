package com.example.yossy.orderfoodserver.Model;

public class User {
    private String Name, Password, NoAdmin, IsAdmin;

    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNoAdmin() {
        return NoAdmin;
    }

    public void setNoAdmin(String noAdmin) {
        NoAdmin = noAdmin;
    }

    public String getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        IsAdmin = isAdmin;
    }
}
