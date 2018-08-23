package model;

public class League extends IDNameModel {

  private Country country;

  public League(int id, String name, Country country) {
    super(id, name);
    this.country = country;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "League{" +
            "country=" + country +
            ", id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
