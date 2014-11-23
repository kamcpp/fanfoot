package ir.fanfoot.biz.dao;

import ir.telefa.domain.News;
import ir.telefa.domain.NewsAgency;

import java.util.List;

public interface NewsDAO extends GenericDAO<News> {

    long count();

    long countShown();

    List<News> getAllPaged(int first, int pageSize);

    List<News> getAllShownPaged(int first, int pageSize);

    boolean sourceIdExists(NewsAgency newsAgency, String sourceId);
}
