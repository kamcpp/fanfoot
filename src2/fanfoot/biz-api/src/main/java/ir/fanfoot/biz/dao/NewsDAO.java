package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.News;
import ir.fanfoot.domain.NewsAgency;

import java.util.List;

public interface NewsDAO extends GenericDAO<News> {

    long count();

    long countShown();

    List<News> getAllShownPaged(int first, int pageSize);

    boolean sourceIdExists(NewsAgency newsAgency, String sourceId);
}
