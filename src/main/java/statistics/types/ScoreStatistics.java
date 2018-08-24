package statistics.types;

import model.LightGame;
import statistics.utils.GameFilterUtil;
import statistics.utils.PercentageUtil;
import utils.SmartOutUtil;

import java.util.List;

public class ScoreStatistics {

  public void showStatistics(List<LightGame> games) {
    int totalGames = games.size();
    int totalFinalGoals = 0;
    int totalFinalHomeGoals = 0;
    int totalFinalAwayGoals = 0;
    int noFinalHomeGoalsGames = 0;
    int noFinalAwayGoalsGames = 0;
    for (LightGame game : games) {
      totalFinalHomeGoals += game.getFinalHomeScore();
      totalFinalAwayGoals += game.getFinalAwayScore();
      if (game.getFinalHomeScore() == 0)
        noFinalHomeGoalsGames++;
      if (game.getFinalAwayScore() == 0)
        noFinalAwayGoalsGames++;
    }
    totalFinalGoals = totalFinalHomeGoals + totalFinalAwayGoals;
    showPercentage((noFinalHomeGoalsGames * 100) / totalGames, "No final goals for home teams");
    showPercentage((noFinalAwayGoalsGames * 100) / totalGames, "No final goals for away teams");

    List<LightGame> filteredGames = GameFilterUtil.filterGamesWithHalfScoreInfo(games);
    if (filteredGames.isEmpty())
      return;
    int totalHalfGoals = 0;
    int totalHalfHomeGoals = 0;
    int totalHalfAwayGoals = 0;
    for (LightGame game : filteredGames) {
      totalHalfHomeGoals += game.getHalfHomeScore();
      totalHalfAwayGoals += game.getHalfAwayScore();
    }
    totalHalfGoals = totalHalfHomeGoals + totalHalfAwayGoals;
//    showPercentage();
  }

  private void showPercentage(int percentage, String message) {
    try {
      SmartOutUtil.printLine(message);
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(percentage) + "\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
