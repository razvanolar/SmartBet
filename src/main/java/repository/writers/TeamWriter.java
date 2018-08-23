package repository.writers;

import model.Team;
import repository.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamWriter extends Writer {

  public TeamWriter(Connection connection) {
    super(connection);
  }

  public void saveTeam(Team team) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "insert into teams (name, country_id) values (?, ?)";
      statement = connection.prepareStatement(sql);
      statement.setString(1, team.getName());
      statement.setInt(2, team.getCountry().getId());
      statement.executeUpdate();
    } finally {
      JDBCUtil.getInstance().closeStatement(statement);
    }
  }
}
