package model;

public class ScoreStatistics {

  private int totalGames;

  private int noFinalGoalsGames;
  private int noFinalHomeGoalsGames;
  private int noFinalAwayGoalsGames;

  private int atleastOneFinalGoalGames;
  private int atleastOneFinalHomeGoalGames;
  private int atleastOneFinalAwayGoalGames;

  private int atleastTwoFinalGoalGames;
  private int atleastTwoFinalHomeGoalGames;
  private int atleastTwoFinalAwayGoalGames;

  private int homeVictoryGames;
  private int awayVictoryGames;
  private int drawGames;

  public ScoreStatistics(int totalGames,
                         int noFinalGoalsGames,
                         int noFinalHomeGoalsGames,
                         int noFinalAwayGoalsGames,
                         int atleastOneFinalGoalGames,
                         int atleastOneFinalHomeGoalGames,
                         int atleastOneFinalAwayGoalGames,
                         int atleastTwoFinalGoalGames,
                         int atleastTwoFinalHomeGoalGames,
                         int atleastTwoFinalAwayGoalGames,
                         int homeVictoryGames,
                         int awayVictoryGames,
                         int drawGames) {
    this.totalGames = totalGames;
    this.noFinalGoalsGames = noFinalGoalsGames;
    this.noFinalHomeGoalsGames = noFinalHomeGoalsGames;
    this.noFinalAwayGoalsGames = noFinalAwayGoalsGames;
    this.atleastOneFinalGoalGames = atleastOneFinalGoalGames;
    this.atleastOneFinalHomeGoalGames = atleastOneFinalHomeGoalGames;
    this.atleastOneFinalAwayGoalGames = atleastOneFinalAwayGoalGames;
    this.atleastTwoFinalGoalGames = atleastTwoFinalGoalGames;
    this.atleastTwoFinalHomeGoalGames = atleastTwoFinalHomeGoalGames;
    this.atleastTwoFinalAwayGoalGames = atleastTwoFinalAwayGoalGames;
    this.homeVictoryGames = homeVictoryGames;
    this.awayVictoryGames = awayVictoryGames;
    this.drawGames = drawGames;
  }

  public int getNoFinalGoalsPercentage() {
    return (noFinalGoalsGames * 100) / totalGames;
  }

  public int getNoFinalHomeGoalsGamesPercentage() {
    return (noFinalHomeGoalsGames * 100) / totalGames;
  }

  public int getNoFinalAwayGoalsGamesPercentage() {
    return (noFinalAwayGoalsGames * 100) / totalGames;
  }

  public int getAtleastOneFinalGoalGamesPercentage() {
    return (atleastOneFinalGoalGames * 100) / totalGames;
  }

  public int getAtleastOneFinalHomeGoalGamesPercentage() {
    return (atleastOneFinalHomeGoalGames * 100) / totalGames;
  }

  public int getAtleastOneFinalAwayGoalGamesPercentage() {
    return (atleastOneFinalAwayGoalGames * 100) / totalGames;
  }

  public int getAtleastTwoFinalGoalGamesPercentage() {
    return (atleastTwoFinalGoalGames * 100) / totalGames;
  }

  public int getAtleastTwoFinalHomeGoalGamesPercentage() {
    return (atleastTwoFinalHomeGoalGames * 100) / totalGames;
  }

  public int getAtleastTwoFinalAwayGoalGamesPercentage() {
    return (atleastTwoFinalAwayGoalGames * 100) / totalGames;
  }

  public int getHomeVictoryGamesPercentage() {
    return (homeVictoryGames * 100) / totalGames;
  }

  public int getAwayVictoryGamesPercentage() {
    return (awayVictoryGames * 100) / totalGames;
  }

  public int getDrawGamesPercentage() {
    return (drawGames * 100) / totalGames;
  }
}
