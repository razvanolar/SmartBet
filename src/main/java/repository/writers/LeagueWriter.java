package repository.writers;

import model.League;
import repository.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeagueWriter extends Writer {

  public LeagueWriter(Connection connection) {
    super(connection);
  }

  public void saveLeague(League league) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "insert into leagues (name, country_id) values (?, ?);";
      statement = connection.prepareStatement(sql);
      statement.setString(1, league.getName());
      statement.setInt(2, league.getCountry().getId());
      statement.executeUpdate();
    } finally {
      JDBCUtil.getInstance().closePrepareStatement(statement);
    }
  }
}
