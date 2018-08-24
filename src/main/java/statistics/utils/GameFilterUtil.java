package statistics.utils;

import model.Game;
import model.LightGame;

import java.util.ArrayList;
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
}
