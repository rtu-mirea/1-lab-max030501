package com.company;

public class User {
    protected String name;
    protected String login;
    protected String password;
    User(String name, String login, String password){
        this.name = name;
        this.login = login;
        this.password = password;

    }
    User(){
        name = "Null";
        login = "Null";
        password = "Null";
    }
    public boolean enter(String login,String password){
        if (this.login.equals(login) & this.password.equals(password))
            return true;
        else return false;
    }
}
