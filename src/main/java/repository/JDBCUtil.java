package repository;

import java.sql.*;
import java.util.Collection;

public class JDBCUtil {

  private String host = "jdbc:mysql://localhost:3306/smartbet?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=utf8&useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";

  private String user = "root";
  private String password = "root";

  private int retry = 5;

  private static JDBCUtil INSTANCE;

  private JDBCUtil() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
  }

  /**
   * Testing DB connection.
   * Called when the application starts
   */
  public void testConnection() throws SQLException {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      connection = getNewConnection();
      String query = "SHOW TABLES";
      statement = connection.prepareStatement(query);
      resultSet = statement.executeQuery();
    } catch (SQLException e) {
      //TODO Handle test connection error
      e.printStackTrace();
      throw e;
    } finally {
      close(connection, statement, resultSet);
    }
  }

  public Connection getNewConnection() throws SQLException {
    return DriverManager.getConnection(host, user, password);
  }

  public void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
    closeResultSet(resultSet);
    closePrepareStatement(statement);
    closeConnection(connection);
  }

  public void close(Connection connection, Statement statement, ResultSet resultSet) {
    closeResultSet(resultSet);
    closeStatement(statement);
    closeConnection(connection);
  }

  public void close(PreparedStatement statement, ResultSet resultSet) {
    closePrepareStatement(statement);
    closeResultSet(resultSet);
  }

  public void closeConnection(Connection connection) {
    closeConnection(connection, retry);
  }

  private void closeConnection(Connection connection, int attemptsLeft) {
    if (connection == null)
      return;
    if (attemptsLeft <= 0) {
      //TODO Handle exception when unable to close connection
      return;
    }

    try {
      if (!connection.isClosed())
        connection.close();
    } catch (SQLException e) {
      //TODO Handle exception when trying to close connection
      attemptsLeft--;
      System.out.println("Unable to close connection. Attempts left: " + attemptsLeft);
      closeConnection(connection, attemptsLeft);
    }
  }

  public void closeStatement(Statement statement) {
    closeStatement(statement, retry);
  }

  public void closeStatement(Statement statement, int attemptsLeft) {
    if (statement == null)
      return;
    if (attemptsLeft <= 0) {
      // TODO Handle exception when unable to close statement
      return;
    }

    try {
      if (!statement.isClosed())
        statement.close();
    } catch (SQLException e) {
      //TODO Handle exception when trying to close connection
      attemptsLeft--;
      System.out.println("Unable to close statement. Attempts left: " + attemptsLeft);
      closeStatement(statement, attemptsLeft);
    }
  }

  public void closePrepareStatement(PreparedStatement statement) {
    closePrepareStatement(statement, retry);
  }

  private void closePrepareStatement(PreparedStatement statement, int attemptsLeft) {
    if (statement == null)
      return;
    if (attemptsLeft <= 0) {
      //TODO Handle exception when unable to close the prepare statement
      return;
    }
    try {
      if (!statement.isClosed())
        statement.close();
    } catch (SQLException e) {
      //TODO Handle exception when trying to close the prepare statement
      attemptsLeft--;
      System.out.println("Unable to close prepare statement. Attempts left: " + attemptsLeft);
      closePrepareStatement(statement, attemptsLeft);
    }
  }

  public void closeResultSet(ResultSet resultSet) {
    closeResultSet(resultSet, retry);
  }

  private void closeResultSet(ResultSet resultSet, int attemptsLeft) {
    if (resultSet == null)
      return;
    if (attemptsLeft <= 0) {
      //TODO Handle exception when unable to close the result set
      return;
    }
    try {
      if (!resultSet.isClosed())
        resultSet.close();
    } catch (SQLException e) {
      //TODO Handle exception when trying to close the result set
      attemptsLeft--;
      System.out.println("Unable to close result set. Attempts left: " + attemptsLeft);
      closeResultSet(resultSet, attemptsLeft);
    }
  }

  public String getInnerClause(Collection<Integer> ids) {
    if (ids == null || ids.isEmpty())
      return "";
    StringBuilder rez = new StringBuilder();
    for (Integer i : ids) {
      rez.append(i).append(", ");
    }
    String s = rez.toString();
    return s.substring(0, s.length() - 2);
  }

  public static void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    INSTANCE = new JDBCUtil();
  }

  public static JDBCUtil getInstance() {
    return INSTANCE;
  }
}
