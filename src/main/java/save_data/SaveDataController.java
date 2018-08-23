package save_data;

import model.Country;
import model.Game;
import model.League;
import repository.Repository;
import save_data.parser.XmlParser;
import utils.SmartOutUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveDataController {

  private Repository repo;
  private String data_path;

  public SaveDataController(Repository repo, String data_path) {
    this.repo = repo;
    this.data_path = data_path;
  }

  public void saveDataToDB() throws Exception {
    File dataDir = getDataPathDirFile();
    if (dataDir == null) {
      throw new Exception("Data path does not exist or it's not a directory.");
    }

    List<File> countries = readCountryDirs(dataDir);
    if (countries == null || countries.isEmpty()) {
      throw new Exception("No country directory found.");
    }

    XmlParser parser = new XmlParser();

    for (File c : countries) {
//      SmartOutUtil.printLine(c.getName());
      File[] leagues = c.listFiles();
      if (leagues == null || leagues.length == 0) {
        continue;
      }
      Country country = new Country(0, c.getName());
      for (File l : leagues) {
        League league = new League(0, l.getName(), country);
        List<Game> games = parser.parse(l, country, league);
//        System.out.println(games);
        repo.saveGames(country, league, games);
      }
//      break;
    }
  }

  private File getDataPathDirFile() {
    File file = new File(data_path);
    return !file.exists() || !file.isDirectory() ? null : file;
  }

  private List<File> readCountryDirs(File dataDir) {
    File[] files = dataDir.listFiles();
    if (files == null) {
      return null;
    }
    List<File> result = new ArrayList<>();
    for (File file : files) {
      if (file.isDirectory()) {
        result.add(file);
      }
    }
    return result;
  }
}
