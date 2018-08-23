package model;

public class Country extends IDNameModel {

  public Country(int id, String name) {
    super(id, name);
  }

  @Override
  public String toString() {
    return "Country{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
