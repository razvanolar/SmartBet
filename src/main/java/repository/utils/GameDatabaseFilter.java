package repository.utils;

import java.util.Date;

public class GameDatabaseFilter {

  public static int EMPTY_FIELD = -1;

  private Date minDisputedDate;

  /** if teamId is set, homeTeamId and awayTeamId will be ignored */
  private int teamId = EMPTY_FIELD;
  private int homeTeamId = EMPTY_FIELD;
  private int awayTeamId = EMPTY_FIELD;

  private int leagueId = EMPTY_FIELD;

  public GameDatabaseFilter(Date minDisputedDate) {
    this.minDisputedDate = minDisputedDate;
  }

  public GameDatabaseFilter(Date minDisputedDate, int teamId, int homeTeamId, int awayTeamId, int leagueId) {
    this.minDisputedDate = minDisputedDate;
    this.teamId = teamId;
    this.homeTeamId = homeTeamId;
    this.awayTeamId = awayTeamId;
    this.leagueId = leagueId;
  }

  public Date getMinDisputedDate() {
    return minDisputedDate;
  }

  public int getTeamId() {
    return teamId;
  }

  public int getHomeTeamId() {
    return homeTeamId;
  }

  public int getAwayTeamId() {
    return awayTeamId;
  }

  public int getLeagueId() {
    return leagueId;
  }
}
