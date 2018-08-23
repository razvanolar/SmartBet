package repository.loaders;

import model.Team;
import repository.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamLoader extends Loader {

  private static String SELECT_ALL = "SELECT t.id, t.name, c.id, c.name FROM teams t INNER JOIN countries c ON t.country_id = c.id";

  public TeamLoader(Connection connection) {
    super(connection);
  }

  public List<Team> getAllTeams() throws SQLException {
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = SELECT_ALL;
      statement = connection.prepareStatement(query);
      rs = statement.executeQuery();
      List<Team> result = new ArrayList<>();
      while (rs.next()) {
        result.add(extractTeam(rs));
      }
      return result;
    } finally {
      JDBCUtil.getInstance().close(statement, rs);
    }
  }

  public Team getTeamByNameAndCountryId(String teamName, int countryId) throws SQLException {
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = SELECT_ALL + " where t.name = ? and c.id = ?";
      statement = connection.prepareStatement(query);
      statement.setString(1, teamName);
      statement.setInt(2, countryId);
      rs = statement.executeQuery();
      return rs.next() ? extractTeam(rs) : null;
    } finally {
      JDBCUtil.getInstance().close(statement, rs);
    }
  }
}
