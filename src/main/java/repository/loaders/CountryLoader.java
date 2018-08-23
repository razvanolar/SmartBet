package repository.loaders;

import model.Country;
import repository.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryLoader extends Loader {

  private static String SELECT_ALL = "select id, name from countries";

  public CountryLoader(Connection connection) {
    super(connection);
  }

  public List<Country> getAllCountries() throws SQLException {
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = SELECT_ALL;
      statement = connection.prepareStatement(query);
      rs = statement.executeQuery();
      List<Country> result = new ArrayList<>();
      while (rs.next()) {
        result.add(extractCountry(rs));
      }
      return result;
    } finally {
      JDBCUtil.getInstance().close(statement, rs);
    }
  }

  public Country getCountryByName(String name) throws SQLException {
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = SELECT_ALL + " where name = ?";
      statement = connection.prepareStatement(query);
      statement.setString(1, name);
      rs = statement.executeQuery();
      return rs.next() ? extractCountry(rs) : null;
    } finally {
      JDBCUtil.getInstance().close(statement, rs);
    }
  }
}
