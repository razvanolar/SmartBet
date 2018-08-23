package utils;

import model.Country;
import model.League;

import java.io.IOException;

public class SmartOutUtil {

  public static class SaveProgress {

    private String county;
    private String league;
    private int totalGames;
    private int savedGames;

    public SaveProgress(String county, String league, int totalGames) {
      this.county = county;
      this.league = league;
      this.totalGames = totalGames;
      this.savedGames = 0;
    }

    public void begin() throws Exception {
      printLine(" - - - - - - - - -");
      printLine(county);
      printLine(league);
      print(getProgressString());
    }

    public void gameSaved() throws Exception {
      savedGames++;
      print(getProgressString());
    }

    private String getProgressString() {
      int consoleWidth = getConsoleWidth();
      int percent = (savedGames * 100) / totalGames;

      String rez = "\r ";
      if (percent < 10)
        rez += "  ";
      else if (percent < 100)
        rez += " ";

      rez += String.valueOf(percent);
      rez += "% [";

      int spaceLeft = consoleWidth - rez.length() - 1;
      int fill = (spaceLeft * percent) / 100;

      for (int i=0; i<spaceLeft; i++) {
        if (i < fill) {
          rez += "#";
        } else {
          rez += "-";
        }
      }

      rez += "]";

      return rez;
    }
  }

  public static int getConsoleWidth() {
    return jline.TerminalFactory.get().getWidth();
  }

  public static void printLine() {
    System.out.println("\nSM >>>");
  }

  public static void printLine(int i) {
    printLine(String.valueOf(i));
  }

  public static void printLine(String message) {
    System.out.println("SM >>> " + message);
  }

  public static void print(String message) throws IOException {
//    System.out.write(message.getBytes());
    System.out.print(message);
  }

  public static SaveProgress getSaveProgressObject(Country country, League league, int gamesNo) {
    return new SaveProgress(country.getName(), league.getName(), gamesNo);
  }
}
