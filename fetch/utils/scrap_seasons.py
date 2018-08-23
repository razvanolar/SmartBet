import sys
from utils import constants
from utils.utils import scrap as get_content, log
from bs4 import BeautifulSoup


SCRIPT_NAME = sys.argv[0]
START_SEASON = "2016"


def scrap(league):
    content = get_content(league.url)
    if content is None:
        return None
    schedule_url = parse_schedule(content)
    content = get_content(schedule_url)
    if content is None:
        return None
    return parse_seasons(content)


def parse_schedule(content):
    soup = BeautifulSoup(content, "html.parser")
    navi_div = soup.find("div", {"id": "navi"})
    if navi_div is None:
        log("Unable to find navi_div.", SCRIPT_NAME)
        return None
    sitenavi_div = navi_div.find("div", {"class": "sitenavi"})
    if sitenavi_div is None:
        log("Unable to find sitenavi.", SCRIPT_NAME)
        return None
    lists = sitenavi_div.find_all("ul")
    if len(lists) < 2:
        log("Unable to find schedule list. ", SCRIPT_NAME)
        return None
    list = lists[1]
    for li in list.find_all("li"):
        a = li.find("a")
        if a is not None and a.string == "Schedule":
            return constants.WEBSITE + a["href"]
    return None


def parse_seasons(content):
    soup = BeautifulSoup(content, "html.parser")
    table = soup.find("table", {"class": "auswahlbox"})
    if table is None:
        log("Unable to find drop-down table.", SCRIPT_NAME)
        return None
    select = table.find("select")
    if select is None:
        log("Unable to find drop-down.", SCRIPT_NAME)
        return None
    seasons = []
    for op in select.find_all("option"):
        seasons.append(constants.WEBSITE + op["value"])
    return seasons
