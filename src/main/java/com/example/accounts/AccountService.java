package com.example.accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private static AccountService instance;
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionToProfile;

    private AccountService() {
        this.loginToProfile = new HashMap<>();
        this.sessionToProfile = new HashMap<>();

        loginToProfile.put("admin", new UserProfile("admin", "1234"));
        loginToProfile.put("Alex",  new UserProfile("Alex", "Alex"));
    }

    // Метод для получения экземпляра
    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public void AddNewUser(UserProfile profile) {
        loginToProfile.put(profile.GetLogin(), profile);
    }

    public void AddSession(String sessionId,UserProfile profile) {
        sessionToProfile.put(sessionId,profile);
    }

    public UserProfile GetBySessionId(String sessionId) {
        return sessionToProfile.get(sessionId);
    }

    public UserProfile GetUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public void DeleteSession(String sessionId) {
        sessionToProfile.remove(sessionId);
    }
}
