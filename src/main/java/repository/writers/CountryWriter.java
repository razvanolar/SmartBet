package repository.writers;

import model.Country;
import repository.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CountryWriter extends Writer {

  public CountryWriter(Connection connection) {
    super(connection);
  }

  public void saveCountry(Country country) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "insert into countries (name) values (?)";
      statement = connection.prepareStatement(sql);
      statement.setString(1, country.getName());
      statement.executeUpdate();
    } finally {
      JDBCUtil.getInstance().closePrepareStatement(statement);
    }
  }
}
