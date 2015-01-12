package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Team;

public interface TeamDAO extends GenericDAO<Team> {

    Team getByEnglishName(String englishName);
}
