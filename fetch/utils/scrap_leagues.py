import sys
from utils.utils import scrap as get_content, log, League
from bs4 import BeautifulSoup


SCRIPT_NAME = sys.argv[0]


def scrap_first_leagues(country):
    content = get_content(country.url)
    if content is None:
        return None
    return parse(content, False)


def parse(content, get_second_league):
    soup = BeautifulSoup(content, "html.parser")
    navi_div = soup.find("div", {"id": "navi"})
    if navi_div is None:
        log("Unable to find navi_div.", SCRIPT_NAME)
        return None
    subnavi_div = navi_div.find("div", {"class": "subnavi"})
    if subnavi_div is None:
        log("Unable to find subnavi.", SCRIPT_NAME)
        return None
    ul_tag = subnavi_div.find("ul")
    if ul_tag is None:
        log("Unable to find league list.", SCRIPT_NAME)
        return None
    list = ul_tag.find_all("li")
    if len(list) < 1:
        log("Unable to find first league.", SCRIPT_NAME)
        return None
    first_league = parse_first_league(list)
    if not get_second_league:
        if first_league is None:
            return None
        return [first_league]
    return None


def parse_first_league(li_tags):
    link = li_tags[0].find("a")
    if link is None:
        log("Unable to find league link", SCRIPT_NAME)
        return None
    return League(link.string, link["href"])
