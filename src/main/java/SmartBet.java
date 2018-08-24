import repository.Repository;
import save_data.SaveDataController;
import utils.SmartOutUtil;

public class SmartBet {

  public static void main(String[] args) {
    try {
      if (args.length == 0) {
        throw new Exception("No data path provided.");
      }

      Repository repo = new Repository();
      repo.testConnection();
      SmartOutUtil.printLine("Test connection passed");

      SaveDataController saveController = new SaveDataController(repo, args[0]);
      saveController.saveDataToDB();

      SmartOutUtil.printLine("All tests passed");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
