package statistics;

import model.LightGame;
import model.ScoreStatistics;
import repository.Repository;
import repository.utils.GameDatabaseFilter;
import statistics.types.ScoreStatisticsController;
import statistics.utils.GameFilterUtil;
import utils.SmartOutUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatisticsController {

  private Repository repo;
  private ScoreStatisticsController scoreStatistics;

  public StatisticsController(Repository repository) {
    this.repo = repository;
    this.scoreStatistics = new ScoreStatisticsController();
  }

  public void showStatisticsForLeague(int leagueId) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = dateFormat.parse("01/01/2010");

      SmartOutUtil.printLine("-------- League Statistics --------");
      List<LightGame> games = repo.getLightGames(new GameDatabaseFilter(date,
              GameDatabaseFilter.EMPTY_FIELD,
              GameDatabaseFilter.EMPTY_FIELD,
              GameDatabaseFilter.EMPTY_FIELD,
              leagueId));
      games = GameFilterUtil.filterGamesAfterDate(games, date);
      ScoreStatistics statistics = scoreStatistics.getStatistics(games);
      scoreStatistics.showStatistics(statistics);

      SmartOutUtil.printLine("-------- Team statistics ----------");
      games = repo.getLightGames(new GameDatabaseFilter(date,
              GameDatabaseFilter.EMPTY_FIELD,
              GameDatabaseFilter.EMPTY_FIELD,
              1509,
              leagueId));
      games = GameFilterUtil.filterGamesAfterDate(games, date);
      statistics = scoreStatistics.getStatistics(games);
      scoreStatistics.showStatistics(statistics);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
