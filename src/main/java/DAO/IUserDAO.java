package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public void insertUser(User user) throws SQLException;
    public List<User> selectAll();
    public User selectUser(int id);
    public boolean updateUser(User user) throws SQLException;
    public boolean deleteUser(int id) throws SQLException;
}
