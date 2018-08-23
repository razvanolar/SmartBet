package repository.writers;

import java.sql.Connection;

public class Writer {

  protected Connection connection;

  public Writer(Connection connection) {
    this.connection = connection;
  }
}
