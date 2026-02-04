package com.example.accounts;

public class UserProfile {
    private final String login;
    private final String password;

    public UserProfile(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String GetLogin() {return login;}
    public String GetPassword() {return password;}
}
