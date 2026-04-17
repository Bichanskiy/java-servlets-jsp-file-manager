package com.example.accounts;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")

public class UserProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Обозначаем автоинкремент
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    protected UserProfile() {} // Пустой конструктор обязательно для Hibernate
    public UserProfile(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String GetLogin() {return login;}
    public String GetPassword() {return password;}
}
