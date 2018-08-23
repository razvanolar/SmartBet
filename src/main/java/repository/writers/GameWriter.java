package repository.writers;

import model.Game;
import repository.JDBCUtil;

import java.sql.*;

public class GameWriter extends Writer {

  public GameWriter(Connection connection) {
    super(connection);
  }

  public void saveGame(Game game) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "insert into games " +
              "(disputed, home_team_id, away_team_id, final_home_score, final_away_score, half_home_score, half_away_score, league_id) " +
              "values (?, ?, ?, ?, ?, ?, ?, ?)";
      statement = connection.prepareStatement(sql);
      statement.setDate(1, new Date(game.getDate().getTime()));
      statement.setInt(2, game.getHomeTeam().getId());
      statement.setInt(3, game.getAwayTeam().getId());
      statement.setInt(4, game.getFinalTimeHomeScore());
      statement.setInt(5, game.getFinalTimeAwayScore());
      if (game.getHalfTimeHomeScore() == Game.DEFAULT_MISSING_SCORE)
        statement.setNull(6, Types.INTEGER);
      else
        statement.setInt(6, game.getHalfTimeHomeScore());
      if (game.getHalfTimeAwayScore() == Game.DEFAULT_MISSING_SCORE)
        statement.setNull(7, Types.INTEGER);
      else
        statement.setInt(7, game.getHalfTimeAwayScore());
      statement.setInt(8, game.getLeague().getId());

      statement.executeUpdate();
    } finally {
      JDBCUtil.getInstance().closeStatement(statement);
    }
  }
}
