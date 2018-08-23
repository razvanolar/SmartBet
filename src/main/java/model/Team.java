package model;

public class Team extends IDNameModel {

  private Country country;

  public Team(int id, String name, Country country) {
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
    return "Team{" +
            "cousntry=" + country +
            ", id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
