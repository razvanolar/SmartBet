package utils;

public class SmartOutUtil {

  public static void printLine(int i) {
    printLine(String.valueOf(i));
  }

  public static void printLine(String message) {
    System.out.println("SM >>> " + message);
  }
}
