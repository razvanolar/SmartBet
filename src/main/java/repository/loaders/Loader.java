package repository.loaders;

import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Loader {

  protected Connection connection;

  public Loader() {
  }

  public Loader(Connection connection) {
    this.connection = connection;
  }

  protected Country extractCountry(ResultSet resultSet) throws SQLException {
    return new Country(resultSet.getInt(1), resultSet.getString(2));
  }

  protected League extractLeague(ResultSet resultSet) throws SQLException {
    return new League(resultSet.getInt(1), resultSet.getString(2),
            new Country(resultSet.getInt(3), resultSet.getString(4)));
  }

  protected Team extractTeam(ResultSet resultSet) throws SQLException {
    return new Team(resultSet.getInt(1), resultSet.getString(2),
            new Country(resultSet.getInt(3), resultSet.getString(4)));
  }

  protected LightGame extractLightGame(ResultSet resultSet) throws SQLException {
    int halfHomeScore = resultSet.getInt(10);
    halfHomeScore = resultSet.wasNull() ? Game.DEFAULT_MISSING_SCORE : halfHomeScore;
    int halfAwayScore = resultSet.getInt(11);
    halfAwayScore = resultSet.wasNull() ? Game.DEFAULT_MISSING_SCORE : halfAwayScore;
    return new LightGame(resultSet.getDate(1),
            resultSet.getInt(2), resultSet.getString(3),
            resultSet.getInt(4), resultSet.getString(5),
            resultSet.getInt(6), resultSet.getString(7),
            resultSet.getInt(8), resultSet.getInt(9),
            halfHomeScore, halfAwayScore);
  }
}
