package repository;

import model.Country;
import model.Game;
import model.League;
import model.Team;
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

  public void runTests() throws Exception {
    testCountries();
    SmartOutUtil.printLine("Tests passed for countries");
    testLeagues();
    SmartOutUtil.printLine("Tests passed for leagues");
  }

  private void testCountries() throws SQLException, AssertionError {
    Country c1 = loader.getCountryByName("Romania");
    assert c1 != null;
    assert c1.getId() == 1;
    assert c1.getName().equalsIgnoreCase("Romania");

    Country c2 = loader.getCountryByName("R");
    assert c2 == null;

    Country c3 = loader.getCountryByName("England");
    assert c3 != null;
    assert c3.getId() == 2;
    assert c3.getName().equalsIgnoreCase("England");

    List<Country> all = loader.getAllCountries();
    assert all.size() == 2;
    assert all.get(0).getId() == 1;
    assert all.get(0).getName().equalsIgnoreCase("Romania");
    assert all.get(1).getId() == 2;
    assert all.get(1).getName().equalsIgnoreCase("England");
  }

  private void testLeagues() throws Exception {
    League l1 = loader.getLeagueByNameAndCountryId("Liga 1", 1);
    assert l1.getId() == 1;
    assert l1.getName().equalsIgnoreCase("Liga 1");
    assert l1.getCountry().getId() == 1;
    assert l1.getCountry().getName().equalsIgnoreCase("Romania");

    League l2 = loader.getLeagueByNameAndCountryId("Liga 2", 1);
    assert l2.getId() == 3;
    assert l2.getName().equalsIgnoreCase("Liga 2");
    assert l2.getCountry().getId() == 1;
    assert l2.getCountry().getName().equalsIgnoreCase("Romania");

    League l3 = loader.getLeagueByNameAndCountryId("Liga", 1);
    assert l3 == null;

    League l4 = loader.getLeagueByNameAndCountryId("Premier League", 2);
    assert l4.getId() == 2;
    assert l4.getName().equalsIgnoreCase("Premier League");
    assert l4.getCountry().getId() == 2;
    assert l4.getCountry().getName().equalsIgnoreCase("England");

    List<League> all = loader.getAllLeagues();
    for (League l : all) {
      if (l.getName().equalsIgnoreCase("Liga 1"))
        assert l.getId() == 1;
      if (l.getName().equalsIgnoreCase("Liga 2"))
        assert l.getId() == 3;
      if (l.getName().equalsIgnoreCase("Premier League"))
        assert l.getId() == 2;
    }
  }

  public void testConnection() throws SQLException {
    JDBCUtil.getInstance().testConnection();
  }
}
