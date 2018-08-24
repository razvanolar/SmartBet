import sys
import utils.scrap_countries as sc
import utils.scrap_leagues as sl
import utils.scrap_seasons as ss
import utils.scrap_games as sg
from utils.utils import log, read_scrapped_countries, write_scrapped_country, create_dir_structure_for_country_and_league, write_league_games


SCRIPT_NAME = sys.argv[0]


def main(data_path):
    countries = sc.scrap()
    if countries is None:
        log("No country was identified", SCRIPT_NAME)
    print(str(len(countries)))
    if data_path:
        scrapped_countries = read_scrapped_countries(data_path)
        countries = [c for c in countries if not is_scrapped_country(scrapped_countries, c)]
    sum = 0
    for c in countries:
        log("Retrieve leagues for " + c.name)
        leagues = sl.scrap_first_leagues(c)
        saved_league = False
        if leagues is not None:
            league = leagues[0]
            # print(league)
            log("Retrieve seasons for " + c.name)
            seasons = ss.scrap(league)
            games = []
            for s in seasons:
                log("Retrieve games for " + s)
                scrapped_games = sg.scrap(s)
                sum += len(scrapped_games)
                print(sum)
                games.extend(scrapped_games)
                # break
            league_path = create_dir_structure_for_country_and_league(data_path, c, league)
            if league_path is not None:
                saved_league = write_league_games(league_path, games)
        if saved_league:
            write_scrapped_country(data_path, c)
        # break
    print(sum)


def is_scrapped_country(scrapped_countries, country):
    if scrapped_countries is None or len(scrapped_countries) == 0:
        return False
    for c in scrapped_countries:
        if c == country.name:
            return True
    return False


if __name__ == "__main__":
    data_path = None
    if len(sys.argv) > 1:
        data_path = sys.argv[1]
    main(data_path)
