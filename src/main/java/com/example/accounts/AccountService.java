package com.example.accounts;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private static AccountService instance;
    private final Map<String, UserProfile> sessionToProfile;

    private String URL = "jdbc:mysql://localhost:3306/file_manager";
    private String USERNAME = "root";
    private String PASSWORD = "";

    private AccountService() {
        this.sessionToProfile = new HashMap<>();
    }

    // Метод для получения экземпляра
    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public void AddNewUser(UserProfile profile) throws Exception {
        String query = "insert into users (login, password) values (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, profile.GetLogin());
            preparedStatement.setString(2, profile.GetPassword());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw e;
        }
    }

    public void AddSession(String sessionId,UserProfile profile) {
        sessionToProfile.put(sessionId,profile);
    }

    public UserProfile GetBySessionId(String sessionId) {
        return sessionToProfile.get(sessionId);
    }

    public UserProfile GetUserByLogin(String login) throws SQLException {
        String query = "select * from users where login = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, login); // Устанавливаем логин

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){ // Нужно так как изначально указывает перед 1 строкой
                return new UserProfile(resultSet.getString(2),  resultSet.getString(3));
            }
            else return null;
        } catch (SQLException e){
            throw e;
        }
    }

    public void DeleteSession(String sessionId) {
        sessionToProfile.remove(sessionId);
    }
}
