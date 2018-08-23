package repository.loaders;

import model.League;
import repository.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeagueLoader extends Loader {

  private static String SELECT_ALL = "SELECT l.id, l.name, c.id, c.name FROM leagues l INNER JOIN countries c ON l.country_id = c.id";

  public LeagueLoader(Connection connection) {
    super(connection);
  }

  public List<League> getAllLeagues() throws SQLException {
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = SELECT_ALL;
      statement = connection.prepareStatement(query);
      rs = statement.executeQuery();
      List<League> result = new ArrayList<>();
      while (rs.next()) {
        result.add(extractLeague(rs));
      }
      return result;
    } finally {
      JDBCUtil.getInstance().close(statement, rs);
    }
  }

  public League getLeagueByNameAndCountryId(String leagueName, int countryId) throws SQLException {
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = SELECT_ALL + " WHERE l.name = ? and c.id = ?";
      statement = connection.prepareStatement(query);
      statement.setString(1, leagueName);
      statement.setInt(2, countryId);
      rs = statement.executeQuery();
      return rs.next() ? extractLeague(rs) : null;
    } finally {
      JDBCUtil.getInstance().close(statement, rs);
    }
  }
}
