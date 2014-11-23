package ir.fanfoot.biz.dao;

import ir.telefa.domain.NewsAgency;

public interface NewsAgencyDAO extends GenericDAO<NewsAgency> {

    NewsAgency getByEnglishQualifiedName(String englishQualifiedName);
}
