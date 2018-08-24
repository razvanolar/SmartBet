package statistics;

import model.LightGame;
import repository.Repository;
import statistics.types.ScoreStatistics;

import java.util.List;

public class StatisticsController {

  private Repository repo;
  private ScoreStatistics scoreStatistics;

  public StatisticsController(Repository repository) {
    this.repo = repository;
    this.scoreStatistics = new ScoreStatistics();
  }

  public void showStatisticsForLeague(int leagueId) {
    try {
      List<LightGame> games = repo.getLightGamesForLeagueId(leagueId);
      scoreStatistics.showStatistics(games);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
