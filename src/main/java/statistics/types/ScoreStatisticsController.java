package statistics.types;

import model.LightGame;
import model.ScoreStatistics;
import statistics.utils.PercentageUtil;
import utils.SmartOutUtil;

import java.util.List;

public class ScoreStatisticsController {

  public ScoreStatistics getStatistics(List<LightGame> games) {
    int totalGames = games.size();
    if (totalGames == 0) {
      SmartOutUtil.printLine("Games list is empty");
      return null;
    }
    int totalFinalGoals = 0;
    int totalFinalHomeGoals = 0;
    int totalFinalAwayGoals = 0;

    int noFinalGoalsGames = 0;
    int noFinalHomeGoalsGames = 0;
    int noFinalAwayGoalsGames = 0;

    int atleastOneFinalGoalGames = 0;
    int atleastOneFinalHomeGoalGames = 0;
    int atleastOneFinalAwayGoalGames = 0;

    int atleastTwoFinalGoalGames = 0;
    int atleastTwoFinalHomeGoalGames = 0;
    int atleastTwoFinalAwayGoalGames = 0;

    int homeVictoryGames = 0;
    int awayVictoryGames = 0;
    int drawGames = 0;

    for (LightGame game : games) {
      int finalHomeScore = game.getFinalHomeScore();
      totalFinalHomeGoals += finalHomeScore;
      int finalAwayScore = game.getFinalAwayScore();
      totalFinalAwayGoals += finalAwayScore;
      if (finalHomeScore == 0) noFinalHomeGoalsGames++;
      if (finalAwayScore == 0) noFinalAwayGoalsGames++;
      if (finalHomeScore == 0 && finalAwayScore == 0) noFinalGoalsGames++;

      if (finalHomeScore >= 1) atleastOneFinalHomeGoalGames++;
      if (finalAwayScore >= 1) atleastOneFinalAwayGoalGames++;
      if (finalHomeScore + finalAwayScore >= 1) atleastOneFinalGoalGames++;

      if (finalHomeScore >= 2) atleastTwoFinalHomeGoalGames++;
      if (finalAwayScore >= 2) atleastTwoFinalAwayGoalGames++;
      if (finalHomeScore + finalAwayScore >= 2) atleastTwoFinalGoalGames++;

      if (finalHomeScore > finalAwayScore) homeVictoryGames++;
      if (finalAwayScore > finalHomeScore) awayVictoryGames++;
      if (finalHomeScore == finalAwayScore) drawGames++;
    }
    totalFinalGoals = totalFinalHomeGoals + totalFinalAwayGoals;

    return new ScoreStatistics(totalGames,
            noFinalGoalsGames,
            noFinalHomeGoalsGames,
            noFinalAwayGoalsGames,
            atleastOneFinalGoalGames,
            atleastOneFinalHomeGoalGames,
            atleastOneFinalAwayGoalGames,
            atleastTwoFinalGoalGames,
            atleastTwoFinalHomeGoalGames,
            atleastTwoFinalAwayGoalGames,
            homeVictoryGames,
            awayVictoryGames,
            drawGames);

//    List<LightGame> filteredGames = GameFilterUtil.filterGamesWithHalfScoreInfo(games);
//    if (filteredGames.isEmpty())
//      return;
//    int totalHalfGoals = 0;
//    int totalHalfHomeGoals = 0;
//    int totalHalfAwayGoals = 0;
//    for (LightGame game : filteredGames) {
//      totalHalfHomeGoals += game.getHalfHomeScore();
//      totalHalfAwayGoals += game.getHalfAwayScore();
//    }
//    totalHalfGoals = totalHalfHomeGoals + totalHalfAwayGoals;
//    showStatistics();
  }

  public void showStatistics(ScoreStatistics statistics) {
    if (statistics == null)
      return;
    try {
      SmartOutUtil.printLine("No goals statistics");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getNoFinalHomeGoalsGamesPercentage(), "H") + "\n");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getNoFinalAwayGoalsGamesPercentage(), "A") + "\n");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getNoFinalGoalsPercentage(), "G") + "\n");
      SmartOutUtil.printLine("At least one goal | > 0.5");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getAtleastOneFinalHomeGoalGamesPercentage(), "H") + "\n");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getAtleastOneFinalAwayGoalGamesPercentage(), "A") + "\n");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getAtleastOneFinalGoalGamesPercentage(), "G") + "\n");
      SmartOutUtil.printLine("At least two goals | > 1.5");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getAtleastTwoFinalHomeGoalGamesPercentage(), "H") + "\n");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getAtleastTwoFinalAwayGoalGamesPercentage(), "A") + "\n");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getAtleastTwoFinalGoalGamesPercentage(), "G") + "\n");
      SmartOutUtil.printLine("Victories/Draws");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getHomeVictoryGamesPercentage(), "HV") + "\n");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getAwayVictoryGamesPercentage(), "AV") + "\n");
      SmartOutUtil.print(PercentageUtil.getLoadingBarString(statistics.getDrawGamesPercentage(), "D ") + "\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
