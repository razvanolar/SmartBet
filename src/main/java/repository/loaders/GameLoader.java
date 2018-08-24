package repository.loaders;

import model.Game;
import model.LightGame;
import repository.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameLoader extends Loader {

  public GameLoader(Connection connection) {
    super(connection);
  }

  public static String SELECT_ALL_LIGHT_GAMES_SQL =
          "select g.disputed, l.id, l.name, t1.id, t1.name, t2.id, t2.name, g.final_home_score, g.final_away_score, " +
                  "g.half_home_score, g.half_away_score " +
                  "from games g inner join leagues l on g.league_id = l.id inner join teams t1 " +
                  "on g.home_team_id = t1.id inner join teams t2 on g.away_team_id = t2.id";

  public static String ORDER_BY_DATE_ASC = " order by g.disputed asc";
  public static String ORDER_BY_DATE_DESC = " order by g.disputed desc";

  public boolean checkIfGameExists(Game game) throws SQLException {
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = "select * from games where disputed = ? and home_team_id = ? and away_team_id = ? and " +
          "final_home_score = ? and final_away_score = ? and league_id = ?";
      statement = connection.prepareStatement(query);
      statement.setDate(1, new Date(game.getDate().getTime()));
      statement.setInt(2, game.getHomeTeam().getId());
      statement.setInt(3, game.getAwayTeam().getId());
      statement.setInt(4, game.getFinalTimeHomeScore());
      statement.setInt(5, game.getFinalTimeAwayScore());
      statement.setInt(6, game.getLeague().getId());
      rs = statement.executeQuery();
      return rs.next();
    } finally {
      JDBCUtil.getInstance().close(statement, rs);
    }
  }

  public List<LightGame> getLightGamesForLeagueId(int leagueId) throws SQLException {
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = SELECT_ALL_LIGHT_GAMES_SQL + " where l.id = ?" + ORDER_BY_DATE_ASC;
      statement = connection.prepareStatement(query);
      statement.setInt(1, leagueId);
      rs = statement.executeQuery();
      List<LightGame> result = new ArrayList<>();
      while (rs.next()) {
        result.add(extractLightGame(rs));
      }
      return result;
    } finally {
      JDBCUtil.getInstance().close(statement, rs);
    }
  }
}
