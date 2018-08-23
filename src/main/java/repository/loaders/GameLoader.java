package repository.loaders;

import model.Game;
import repository.JDBCUtil;

import java.sql.*;

public class GameLoader extends Loader {

  public GameLoader(Connection connection) {
    super(connection);
  }

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
}
