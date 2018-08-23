package repository.loaders;

import model.Country;
import model.League;
import model.Team;

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
}
