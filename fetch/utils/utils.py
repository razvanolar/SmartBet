import os
import codecs
import requests
import utils.constants as constants


skip_games = ["-:-", "abor", "dnp", "annull", "resch", "WO", "ec"]


class Country:
    """ Part of the model """
    def __init__(self, name, url):
        self.name = name
        self.url = constants.WEBSITE + url

    def to_string(self):
        return self.name + " : " + self.url

    def __str__(self):
        return self.to_string()

    def __repr__(self):
        return self.to_string()

    def __unicode__(self):
        return self.to_string()


class League:
    """ Part of the model """
    def __init__(self, name, url):
        self.name = name
        self.url = constants.WEBSITE + url

    def to_string(self):
        return self.name + " : " + self.url

    def __str__(self):
        return self.to_string()

    def __repr__(self):
        return self.to_string()

    def __unicode__(self):
        return self.to_string()


class Game:

    def __init__(self, date, home_team, away_team, final_home_score, final_away_score, half_home_score, half_away_score):
        self.date = date
        self.home_team = home_team.replace("\xef", "i").encode("utf-8")
        self.away_team = away_team.replace("\xef", "i").encode("utf-8")
        self.final_home_score = final_home_score
        self.final_away_score = final_away_score
        self.half_home_score = half_home_score
        self.half_away_score = half_away_score

    def to_string(self):
        return self.date + " : " + self.home_team + " - " + self.away_team + " " + str(self.final_home_score) + ":" + str(self.final_away_score) + " (" + str(self.half_home_score) + ":" + str(self.half_away_score) + ")"

    def __str__(self):
        return self.to_string()

    def __repr__(self):
        return self.to_string()

    def __unicode__(self):
        return self.to_string()


def scrap(url):
    response = requests.get(url)
    if response.status_code == 200:
        return response.content
    return None


def log(message, caller=None):
    if caller is None:
        print(">>> " + str(message))
    else:
        print(">>> " + str(caller) + ": " + str(message))


def parse_goals(score):
    score = score.strip()
    final = score
    half = None
    hh = None
    ha = None
    if " " in score:
        s = score.split(" ")
        final = s[0]
        half = s[1]
    f = final.split(":")
    if half is not None and "(" in half and ")" in half:
        h = half[1:-1].split(":")
        if h[0] and h[1]:
            hh = int(h[0])
            ha = int(h[1])
    return int(f[0]), int(f[1]), hh, ha


def is_skipped(score_string):
    for s in skip_games:
        if s in score_string:
            return True
    return False


def normalize_path(data_path):
    if data_path.endswith("\\"):
        return data_path
    return data_path + "\\"


def read_scrapped_countries(data_path):
    if data_path is None:
        return None
    file = open(normalize_path(data_path) + constants.COMPLETE_COUNTRIES_FILE, "r+")
    countries = []
    for line in file:
        countries.append(line.strip())
    file.close()
    return countries


def write_scrapped_country(data_path, country):
    if data_path is None:
        return
    if country is not None and country is Country:
        return
    file = open(normalize_path(data_path) + constants.COMPLETE_COUNTRIES_FILE, "a+")
    file.write(country.name + "\n")
    file.close()


def create_dir_structure_for_country_and_league(data_path, country, league):
    if data_path is None:
        return None
    path = normalize_path(data_path) + country.name
    if not os.path.exists(path):
        os.makedirs(path)
    path = normalize_path(path) + league.name
    file = open(path, "w+")
    file.close()
    return path


def write_league_games(league_path, games):
    if league_path is None:
        return False
    file = codecs.open(league_path, "w+", "utf-8")
    file.write("<games>\n")
    for g in games:
        file.write("\t<game>\n")
        file.write("\t\t<date>" + g.date + "</date>\n")
        file.write("\t\t<home_team>")
        file.write(str(g.home_team, "utf-8"))
        file.write("</home_team>\n")
        file.write("\t\t<away_team>")
        file.write(str(g.away_team, "utf-8"))
        file.write("</away_team>\n")
        file.write("\t\t<final_home_score>" + str(g.final_home_score) + "</final_home_score>\n")
        file.write("\t\t<final_away_score>" + str(g.final_away_score) + "</final_away_score>\n")
        file.write("\t\t<half_home_score>" + str(g.half_home_score) + "</half_home_score>\n")
        file.write("\t\t<half_away_score>" + str(g.half_away_score) + "</half_away_score>\n")
        file.write("\t</game>\n")
    file.write("</games>")
    file.close()
    return True
