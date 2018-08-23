import sys
from utils.utils import scrap as get_content, log, parse_goals, is_skipped, Game
from bs4 import BeautifulSoup


SCRIPT_NAME = sys.argv[0]


def scrap(season_url):
    content = get_content(season_url)
    if content is None:
        return None
    return parse(content)


def parse(content):
    soup = BeautifulSoup(content, "html.parser")
    site_div = soup.find("div", {"id": "site"})
    if site_div is None:
        log("Unable to find site div.", SCRIPT_NAME)
        return None
    content_div = site_div.find("div", {"class": "content"})
    if content_div is None:
        log("Unable to find content div.", SCRIPT_NAME)
        return None
    data_div = content_div.find("div", {"class": "box"})
    if data_div is None:
        log("Unable to find data div.", SCRIPT_NAME)
        return None
    table = data_div.find("table")
    if table is None:
        log("Unable to find table.", SCRIPT_NAME)
        return None

    date = ""
    games = []
    for tr in table.find_all("tr"):
        # if colspan attribute is present it means that this row is a header
        if tr.has_attr("colspan"):
            continue

        # every correct row should have 8 columns
        tds = tr.find_all("td")
        if len(tds) != 8:
            continue

        # change current date when a new one is available
        a = tds[0].find("a")
        if a is not None:
            if a.string:
                date = a.string
            else:
                continue

        # print(date)

        # if the match was not yet disputed
        score = None
        sc = tds[5].find("a")
        if sc is None:
            # if not disputed yet or it was aborted
            score_string = tds[5].string
            if is_skipped(score_string):
                continue
            else:
                score = score_string
        else:
            span = sc.find("span")
            if span:
                continue
            score = sc.string
            # check if <span> in <a> !!!!
            if is_skipped(score):
                continue

        home_team = tds[2].find("a").string
        away_team = tds[4].find("a").string
        if home_team.endswith(";"):
            home_team = home_team[:-1]
        if away_team.endswith(";"):
            away_team = away_team[:-1]
        home_team = home_team.replace("&", "&amp;")
        away_team = away_team.replace("&", "&amp;")

        home_team_goals, away_team_goals, home_team_half_goals, away_team_half_goals = parse_goals(score)
        game = Game(date, home_team, away_team, home_team_goals, away_team_goals, home_team_half_goals,
                    away_team_half_goals)
        # print(game)
        games.append(game)
    return games
