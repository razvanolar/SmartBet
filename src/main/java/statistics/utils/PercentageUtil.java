package statistics.utils;

import utils.SmartOutUtil;

public class PercentageUtil {

  public static String getLoadingBarString(int percentage) {
    int consoleWidth = SmartOutUtil.getConsoleWidth();
    String rez = "\r ";
    if (percentage < 10)
      rez += "  ";
    else if (percentage < 100)
      rez += " ";

    rez += String.valueOf(percentage);
    rez += "% [";

    int spaceLeft = consoleWidth - rez.length() - 1;
    int fill = (spaceLeft * percentage) / 100;

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
