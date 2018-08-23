package save_data.parser;

import model.Country;
import model.Game;
import model.League;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlParser {

  private SAXParser parser;

  public XmlParser() throws ParserConfigurationException, SAXException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    parser = factory.newSAXParser();
  }

  public List<Game> parse(File xmlFile, Country country, League league) throws IOException, SAXException {
    ParserHandler handler = new ParserHandler(country, league);
    parser.parse(xmlFile, handler);
    return handler.getGames();
  }
}
