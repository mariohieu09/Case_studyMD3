package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/quat_manage?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String INSERT_USER = "INSERT INTO user (name, age, username, password) values (?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE user SET name = ?, age = ? where id = ?";
    private static final String CHANGE_PASSWORD = "UPDATE user SET password = ? where id = ?";

    private static final String DELETE_USER = "DELETE FROM user where id = ?";
    private static final String SELECT_ALL_USER = "SELECT * FROM user";
    private static final String SELECT_USER_BY_ID = "SELECT FROM user where id = ?";
    private static final String SELECT_USER_BY_NAME = "SELECT password from user where username = ?";

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return conn;
    }
    @Override
    public void insertUser(User user) throws SQLException {
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            String name =  user.getName();
            int age = user.getAge();
            String username = user.getUsername();
            String password = user.getPassword();
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> selectAll() {
        List<User> list = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String username = rs.getString("username");
                String password = rs.getString("password");
                list.add(new User(name, age, username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public User selectUser(int id) {
        User user = new User();
        try(Connection connection =getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User(name, age, username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public String selectUser(String username){
        String password = null;
        try(Connection connection =getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                password = rs.getString("password");
            }
            if(password == null){
                password = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return password;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdate = false;
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            String name = user.getName();
            int age = user.getAge();

            int id = user.getId();
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3,id);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdate;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDelete = false;
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1 , id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }
    public boolean changePassword(String newPassword, int id){
        boolean rowUpdate = false;
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, id);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdate;
    }
}
