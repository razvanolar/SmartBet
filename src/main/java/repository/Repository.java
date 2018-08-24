package repository;

import model.*;
import utils.SmartOutUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Repository {

  private DataLoader loader;
  private DataWriter writer;

  public Repository() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    JDBCUtil.init();
    this.loader = new DataLoader();
    this.writer = new DataWriter(loader);
  }

  public List<LightGame> getLightGamesForLeagueId(int league_id) throws Exception {
    return loader.getLightGamesForLeagueId(league_id);
  }

  public void saveGames(Country country, League league, List<Game> games) throws Exception {
    if (games == null || games.isEmpty()) {
      return;
    }
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getNewConnection();
      SmartOutUtil.SaveProgress saveProgress = SmartOutUtil.getSaveProgressObject(country, league, games.size());
      saveProgress.begin();
      for (Game game : games) {
        saveGame(game, connection);
        saveProgress.gameSaved();
      }
      SmartOutUtil.printLine();
    } catch (SQLException e) {
      throw e;
    } finally {
      JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  public void saveGame(Game game, Connection connection) throws Exception {
    League league = game.getLeague();
    Country country = league.getCountry();
    Team homeTeam = game.getHomeTeam();
    Team awayTeam = game.getAwayTeam();

    Country c = loader.getCountryByName(country.getName(), connection);
    country = c == null ? writer.saveCountry(country) : c;
    league.setCountry(country);
    homeTeam.setCountry(country);
    awayTeam.setCountry(country);

    League l = loader.getLeagueByNameAndCountryId(league, connection);
    league = l == null ? writer.saveLeague(league, connection) : l;
    game.setLeague(league);

    Team hT = loader.getTeamByNameAndCountryId(homeTeam, connection);
    homeTeam = hT == null ? writer.saveTeam(homeTeam, connection) : hT;
    Team aT = loader.getTeamByNameAndCountryId(awayTeam, connection);
    awayTeam = aT == null ? writer.saveTeam(awayTeam, connection) : aT;
    game.setHomeTeam(homeTeam);
    game.setAwayTeam(awayTeam);

    if (!loader.checkIfGameExists(game, connection)) {
      writer.saveGame(game, connection);
    }
  }

  public void testConnection() throws SQLException {
    JDBCUtil.getInstance().testConnection();
  }
}
