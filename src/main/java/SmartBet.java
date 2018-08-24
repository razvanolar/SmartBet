import repository.Repository;
import save_data.SaveDataController;
import statistics.StatisticsController;
import utils.SmartOutUtil;

public class SmartBet {

  public static void main(String[] args) {
    try {
      Repository repo = new Repository();
      repo.testConnection();
      SmartOutUtil.printLine("Test connection passed");

      if (args.length == 0) {
        StatisticsController statisticsController = new StatisticsController(repo);
        statisticsController.showStatisticsForLeague(50);
      } else {

        SaveDataController saveController = new SaveDataController(repo, args[0]);
        saveController.saveDataToDB();

        SmartOutUtil.printLine("All tests passed");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
