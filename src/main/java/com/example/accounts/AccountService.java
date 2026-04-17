package com.example.accounts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private static AccountService instance;
    private final Map<String, UserProfile> sessionToProfile;

    private final SessionFactory sessionFactory;

    private AccountService() {
        this.sessionToProfile = new HashMap<>();

        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Метод для получения экземпляра
    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public void AddNewUser(UserProfile profile) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(profile);
            session.getTransaction().commit();
        } catch(Exception ex) {
            throw ex;
        }
    }

    public void AddSession(String sessionId,UserProfile profile) {
        sessionToProfile.put(sessionId,profile);
    }

    public UserProfile GetBySessionId(String sessionId) {
        return sessionToProfile.get(sessionId);
    }

    public UserProfile GetUserByLogin(String login) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            // В запросе писать именно имя класса, а не таблицы
            return session.createQuery("FROM UserProfile WHERE login = :login", UserProfile.class)
                    .setParameter("login", login).getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
    }

    public void DeleteSession(String sessionId) {
        sessionToProfile.remove(sessionId);
    }
}
