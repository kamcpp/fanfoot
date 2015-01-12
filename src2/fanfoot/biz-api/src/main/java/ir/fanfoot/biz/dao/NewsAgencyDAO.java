package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.NewsAgency;

public interface NewsAgencyDAO extends GenericDAO<NewsAgency> {

    NewsAgency getByEnglishQualifiedName(String englishQualifiedName);
}
