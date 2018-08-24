package repository.utils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ConditionUtil {

  public static void updateStatement(List<ConditionTuple> conditions, PreparedStatement statement) throws SQLException {
    for (ConditionTuple condition : conditions) {
      updateStatement(condition, statement);
    }
  }

  private static void updateStatement(ConditionTuple condition, PreparedStatement statement) throws SQLException {
    for (Condition cond : condition.getConditions()) {
      updateStatement(cond, statement);
    }
  }

  private static void updateStatement(Condition condition, PreparedStatement statement) throws SQLException {
    int index = condition.getIndex();
    ConditionType type = condition.getType();
    if (type == ConditionType.DATE) {
      java.util.Date date = (java.util.Date) condition.getValue();
      statement.setDate(index, new Date(date.getTime()));
    } else if (type == ConditionType.INT) {
      statement.setInt(index, (Integer) condition.getValue());
    }
  }

  public static String computeConditions(List<ConditionTuple> conditionTuples) {
    StringBuilder builder = new StringBuilder();
    for (int i=0; i<conditionTuples.size(); i++) {
      builder.append(conditionTuples.get(i).getConditionString());
      if (i < conditionTuples.size() - 1)
        builder.append(" and ");
    }
    return builder.toString();
  }
}
