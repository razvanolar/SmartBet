package save_data.parser;

import model.Country;
import model.Game;
import model.League;
import model.Team;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ParserHandler extends DefaultHandler {

  private Country country;
  private League league;

  private List<Game> games = new ArrayList<>();
  private Game game;

  private boolean isDate;
  private boolean isHomeTeam;
  private boolean isAwayTeam;
  private boolean isFinalHomeScore;
  private boolean isFinalAwayScore;
  private boolean isHalfHomeScore;
  private boolean isHalfAwayScore;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  public ParserHandler(Country country, League league) {
    this.country = country;
    this.league = league;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    clearFlags();
    if (qName.equalsIgnoreCase("game")) {
      game = new Game();
      game.setLeague(league);
    } else if (qName.equalsIgnoreCase("date")) {
      isDate = true;
    } else if (qName.equalsIgnoreCase("home_team")) {
      isHomeTeam = true;
    } else if (qName.equalsIgnoreCase("away_team")) {
      isAwayTeam = true;
    } else if (qName.equalsIgnoreCase("final_home_score")) {
      isFinalHomeScore = true;
    } else if (qName.equalsIgnoreCase("final_away_score")) {
      isFinalAwayScore = true;
    } else if (qName.equalsIgnoreCase("half_home_score")) {
      isHalfHomeScore = true;
    } else if (qName.equalsIgnoreCase("half_away_score")) {
      isHalfAwayScore = true;
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    String value = new String(ch, start, length).trim();
    if (!value.isEmpty()) {
      if (isDate) {
        try {
          game.setDate(dateFormat.parse(value));
        } catch (ParseException e) {
          throw new SAXException(e);
        }
      } else if (isHomeTeam) {
        game.setHomeTeam(new Team(0, value, country));
      } else if (isAwayTeam) {
        game.setAwayTeam(new Team(0, value, country));
      } else if (isFinalHomeScore) {
        game.setFinalTimeHomeScore(Integer.parseInt(value));
      } else if (isFinalAwayScore) {
        game.setFinalTimeAwayScore(Integer.parseInt(value));
      } else if (isHalfHomeScore) {
        int score = value.equalsIgnoreCase("None") ? Game.DEFAULT_MISSING_SCORE : Integer.parseInt(value);
        game.setHalfTimeHomeScore(score);
      } else if (isHalfAwayScore) {
        int score = value.equalsIgnoreCase("None") ? Game.DEFAULT_MISSING_SCORE : Integer.parseInt(value);
        game.setHalfTimeAwayScore(score);
      }
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equalsIgnoreCase("game")) {
      games.add(game);
    }
  }

  private void clearFlags() {
    isDate = false;
    isHomeTeam = false;
    isAwayTeam = false;
    isFinalHomeScore = false;
    isFinalAwayScore = false;
    isHalfHomeScore = false;
    isHalfAwayScore = false;
  }

  public List<Game> getGames() {
    return games;
  }
}
