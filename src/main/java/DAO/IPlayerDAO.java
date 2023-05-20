package DAO;

import model.Player;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IPlayerDAO {
    public void insertPlayer(Player player) throws SQLException;
    public List<Player> selectAllPlayer();
    public Player selectPlayer(int id);

    public boolean updatePlayer(Player player) throws SQLException;
    public boolean deletePlayer(int id) throws SQLException;
}
