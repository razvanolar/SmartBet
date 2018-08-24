package statistics.utils;

import model.Game;
import model.LightGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameFilterUtil {

  public static List<LightGame> filterGamesWithHalfScoreInfo(List<LightGame> games) {
    List<LightGame> result = new ArrayList<>();
    for (LightGame game : games) {
      if (game.getHalfHomeScore() != Game.DEFAULT_MISSING_SCORE && game.getHalfAwayScore() != Game.DEFAULT_MISSING_SCORE) {
        result.add(game);
      }
    }
    return result;
  }

  public static List<LightGame> filterGamesAfterDate(List<LightGame> games, Date date) {
    List<LightGame> result = new ArrayList<>();
    long time = date.getTime();
    for (LightGame game : games) {
      if (game.getDisputed().getTime() >= time) {
        result.add(game);
      }
    }
    return result;
  }
}
