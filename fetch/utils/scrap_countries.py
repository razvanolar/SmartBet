from utils import constants
from utils.utils import scrap as get_content, Country, log
from bs4 import BeautifulSoup


def scrap():
    content = get_content(constants.WEBSITE)
    if content is None:
        return None
    return parse(content)


def parse(content):
    soup = BeautifulSoup(content, "html.parser")
    combo_div = soup.find("div", {"id": "special_navi_body"})
    if combo_div is None:
        return None
    a_tags = combo_div.find_all("a", "special")
    result = []
    for a in a_tags:
        result.append(Country(a.string, a["href"]))
    print(result)
    return result


if __name__ == "__main__":
    scrap()
