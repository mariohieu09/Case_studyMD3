package DAO;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements IPlayerDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/quat_manage?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_PLAYER = "INSERT INTO player (name, age,username, password , games, image ) values(?, ?, ?, ?, ?, DEFAULT)";
    private static final String LIST_PLAYER = "SELECT name, age, games, image from player";

    private static final String SELECT_PLAYER = "SELECT * from player where id = ?";
    private static final String UPDATE_PLAYER = "UPDATE player SET name = ?, age = ?, games = ?, image = ?";
    private static final String DELETE_PLAYER = "DELETE FROM player where id = ?";
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        return connection;
    }
    @Override
    public void insertPlayer(Player player) throws SQLException {
//        Player player1 = new Player();
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYER);
            String name = player.getName();
            int age = player.getAge();
            String username = player.getUsername();
            String password = player.getPassword();
            String games = player.getGames();
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age );
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, games);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> selectAllPlayer() {
        List<Player> list = new ArrayList<>();
            try(Connection connection = getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_PLAYER);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String games = rs.getString("games");
                    String path = rs.getString("image");
                    list.add(new Player(name, age, games, path));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return list;
    }

    @Override
    public Player selectPlayer(int id) {
        Player player = new Player();
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLAYER);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String games = rs.getString("games");
                String path = rs.getString("image");
                player = new Player(name, age, games, path);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return player;
    }

    @Override
    public boolean updatePlayer(Player player) throws SQLException {
        boolean rowUpdate = false;
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAYER);
            String name = player.getName();
            int age = player.getAge();
            String games = player.getGames();
            String path = player.getImage();
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, games);
            preparedStatement.setString(4, path);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdate;
    }

    @Override
    public boolean deletePlayer(int id) throws SQLException {
        boolean rowDelete = false;
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLAYER);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }
}
