package repository;

import model.*;
import repository.loaders.CountryLoader;
import repository.loaders.GameLoader;
import repository.loaders.LeagueLoader;
import repository.loaders.TeamLoader;
import repository.utils.GameDatabaseFilter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DataLoader {

  public List<Country> getAllCountries() throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      CountryLoader countryLoader = new CountryLoader(connection);
      return countryLoader.getAllCountries();
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public Country getCountryByName(String name, Connection connection) throws SQLException {
    CountryLoader countryLoader = new CountryLoader(connection);
    return countryLoader.getCountryByName(name);
  }

  public Country getCountryByName(String name) throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      CountryLoader countryLoader = new CountryLoader(connection);
      return countryLoader.getCountryByName(name);
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public List<League> getAllLeagues() throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      LeagueLoader leagueLoader = new LeagueLoader(connection);
      return leagueLoader.getAllLeagues();
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public League getLeagueByNameAndCountryId(League league, Connection connection) throws SQLException {
    return getLeagueByNameAndCountryId(league.getName(), league.getCountry().getId(), connection);
  }

  public League getLeagueByNameAndCountryId(String leagueName, int countryId, Connection connection) throws SQLException {
    LeagueLoader leagueLoader = new LeagueLoader(connection);
    return leagueLoader.getLeagueByNameAndCountryId(leagueName, countryId);
  }

  public League getLeagueByNameAndCountryId(String leagueName, int countryId) throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      return getLeagueByNameAndCountryId(leagueName, countryId, connection);
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public List<Team> getAllTeams() throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      TeamLoader teamLoader = new TeamLoader(connection);
      return teamLoader.getAllTeams();
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public Team getTeamByNameAndCountryId(Team team, Connection connection) throws SQLException {
    return getTeamByNameAndCountryId(team.getName(), team.getCountry().getId(), connection);
  }

  public Team getTeamByNameAndCountryId(String teamName, int countryId, Connection connection) throws SQLException {
    TeamLoader teamLoader = new TeamLoader(connection);
    return teamLoader.getTeamByNameAndCountryId(teamName, countryId);
  }

  public Team getTeamByNameAndCountryId(String teamName, int countryId) throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      return getTeamByNameAndCountryId(teamName, countryId, connection);
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public boolean checkIfGameExists(Game game, Connection connection) throws SQLException {
    GameLoader gameLoader = new GameLoader(connection);
    return gameLoader.checkIfGameExists(game);
  }

  public List<LightGame> getLightGames(GameDatabaseFilter filter) throws SQLException {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      GameLoader gameLoader = new GameLoader(connection);
      return gameLoader.getLightGames(filter);
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }
}
