package model;

import java.util.Date;

public class LightGame {

  private Date disputed;
  private int leagueId;
  private String leagueName;
  private int homeTeamId;
  private String homeTeamName;
  private int awayTeamId;
  private String awayTeamName;
  private int finalHomeScore;
  private int finalAwayScore;
  private int halfHomeScore;
  private int halfAwayScore;

  public LightGame(Date disputed, int leagueId, String leagueName, int homeTeamId, String homeTeamName, int awayTeamId,
                   String awayTeamName, int finalHomeScore, int finalAwayScore, int halfHomeScore, int halfAwayScore) {
    this.disputed = disputed;
    this.leagueId = leagueId;
    this.leagueName = leagueName;
    this.homeTeamId = homeTeamId;
    this.homeTeamName = homeTeamName;
    this.awayTeamId = awayTeamId;
    this.awayTeamName = awayTeamName;
    this.finalHomeScore = finalHomeScore;
    this.finalAwayScore = finalAwayScore;
    this.halfHomeScore = halfHomeScore;
    this.halfAwayScore = halfAwayScore;
  }

  public Date getDisputed() {
    return disputed;
  }

  public void setDisputed(Date disputed) {
    this.disputed = disputed;
  }

  public int getLeagueId() {
    return leagueId;
  }

  public void setLeagueId(int leagueId) {
    this.leagueId = leagueId;
  }

  public String getLeagueName() {
    return leagueName;
  }

  public void setLeagueName(String leagueName) {
    this.leagueName = leagueName;
  }

  public int getHomeTeamId() {
    return homeTeamId;
  }

  public void setHomeTeamId(int homeTeamId) {
    this.homeTeamId = homeTeamId;
  }

  public String getHomeTeamName() {
    return homeTeamName;
  }

  public void setHomeTeamName(String homeTeamName) {
    this.homeTeamName = homeTeamName;
  }

  public int getAwayTeamId() {
    return awayTeamId;
  }

  public void setAwayTeamId(int awayTeamId) {
    this.awayTeamId = awayTeamId;
  }

  public String getAwayTeamName() {
    return awayTeamName;
  }

  public void setAwayTeamName(String awayTeamName) {
    this.awayTeamName = awayTeamName;
  }

  public int getFinalHomeScore() {
    return finalHomeScore;
  }

  public void setFinalHomeScore(int finalHomeScore) {
    this.finalHomeScore = finalHomeScore;
  }

  public int getFinalAwayScore() {
    return finalAwayScore;
  }

  public void setFinalAwayScore(int finalAwayScore) {
    this.finalAwayScore = finalAwayScore;
  }

  public int getHalfHomeScore() {
    return halfHomeScore;
  }

  public void setHalfHomeScore(int halfHomeScore) {
    this.halfHomeScore = halfHomeScore;
  }

  public int getHalfAwayScore() {
    return halfAwayScore;
  }

  public void setHalfAwayScore(int halfAwayScore) {
    this.halfAwayScore = halfAwayScore;
  }

  @Override
  public String toString() {
    return "LightGame{" +
            "disputed=" + disputed +
            ", leagueId=" + leagueId +
            ", leagueName='" + leagueName + '\'' +
            ", homeTeamId=" + homeTeamId +
            ", homeTeamName='" + homeTeamName + '\'' +
            ", awayTeamId=" + awayTeamId +
            ", awayTeamName='" + awayTeamName + '\'' +
            ", finalHomeScore=" + finalHomeScore +
            ", finalAwayScore=" + finalAwayScore +
            ", halfHomeScore=" + halfHomeScore +
            ", halfAwayScore=" + halfAwayScore +
            '}';
  }
}
