package model;

import java.util.Date;

public class Game {

  public static int DEFAULT_MISSING_SCORE = -1;

  private Date date;
  private League league;
  private Team homeTeam;
  private Team awayTeam;
  private int finalTimeHomeScore;
  private int finalTimeAwayScore;
  private int halfTimeHomeScore;
  private int halfTimeAwayScore;

  public Game() {
  }

  public Game(Date date, Country country, League league, Team homeTeam, Team awayTeam, int finalTimeHomeScore,
              int finalTimeAwayScore, int halfTimeHomeScore, int halfTimeAwayScore) {
    this.date = date;
    this.league = league;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.finalTimeHomeScore = finalTimeHomeScore;
    this.finalTimeAwayScore = finalTimeAwayScore;
    this.halfTimeHomeScore = halfTimeHomeScore;
    this.halfTimeAwayScore = halfTimeAwayScore;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public League getLeague() {
    return league;
  }

  public void setLeague(League league) {
    this.league = league;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(Team homeTeam) {
    this.homeTeam = homeTeam;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(Team awayTeam) {
    this.awayTeam = awayTeam;
  }

  public int getFinalTimeHomeScore() {
    return finalTimeHomeScore;
  }

  public void setFinalTimeHomeScore(int finalTimeHomeScore) {
    this.finalTimeHomeScore = finalTimeHomeScore;
  }

  public int getFinalTimeAwayScore() {
    return finalTimeAwayScore;
  }

  public void setFinalTimeAwayScore(int finalTimeAwayScore) {
    this.finalTimeAwayScore = finalTimeAwayScore;
  }

  public int getHalfTimeHomeScore() {
    return halfTimeHomeScore;
  }

  public void setHalfTimeHomeScore(int halfTimeHomeScore) {
    this.halfTimeHomeScore = halfTimeHomeScore;
  }

  public int getHalfTimeAwayScore() {
    return halfTimeAwayScore;
  }

  public void setHalfTimeAwayScore(int halfTimeAwayScore) {
    this.halfTimeAwayScore = halfTimeAwayScore;
  }

  @Override
  public String toString() {
    return "Game{" +
            "date=" + date +
            ", league=" + league +
            ", homeTeam=" + homeTeam +
            ", awayTeam=" + awayTeam +
            ", finalTimeHomeScore=" + finalTimeHomeScore +
            ", finalTimeAwayScore=" + finalTimeAwayScore +
            ", halfTimeHomeScore=" + halfTimeHomeScore +
            ", halfTimeAwayScore=" + halfTimeAwayScore +
            '}';
  }
}
