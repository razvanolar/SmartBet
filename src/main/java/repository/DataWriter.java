package repository;

import model.Country;
import model.Game;
import model.League;
import model.Team;
import repository.writers.CountryWriter;
import repository.writers.GameWriter;
import repository.writers.LeagueWriter;
import repository.writers.TeamWriter;

import java.sql.Connection;
import java.sql.SQLException;

public class DataWriter {

  private DataLoader loader;

  public DataWriter(DataLoader loader) {
    this.loader = loader;
  }

  public Country saveCountry(Country country, Connection connection) throws SQLException {
    CountryWriter countryWriter = new CountryWriter(connection);
    countryWriter.saveCountry(country);
    return loader.getCountryByName(country.getName(), connection);
  }

  public Country saveCountry(Country country) throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      return saveCountry(country, connection);
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public League saveLeague(League league, Connection connection) throws SQLException {
    LeagueWriter leagueWriter = new LeagueWriter(connection);
    leagueWriter.saveLeague(league);
    return loader.getLeagueByNameAndCountryId(league.getName(), league.getCountry().getId(), connection);
  }

  public League saveLeague(League league) throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      return saveLeague(league, connection);
    } catch (Exception e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public Team saveTeam(Team team, Connection connection) throws SQLException {
    TeamWriter teamWriter = new TeamWriter(connection);
    teamWriter.saveTeam(team);
    return loader.getTeamByNameAndCountryId(team.getName(), team.getCountry().getId(), connection);
  }

  public Team saveTeam(Team team) throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      return saveTeam(team, connection);
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public void saveGame(Game game, Connection connection) throws SQLException {
    GameWriter gameWriter = new GameWriter(connection);
    gameWriter.saveGame(game);
  }
}
