package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.News;
import ir.fanfoot.domain.NewsAgency;

import java.util.List;
import java.util.UUID;

public interface NewsDAO extends GenericDAO<News> {

    long count();

    long countShown();

    long countByTitle(String searchText);

    List<News> getAllShownPaged(int first, int pageSize);

    List<News> getAllPagedByTitle(int first, int pageSize, String searchText);

    List<News> getAllPagedShownByTitle(int first, int pageSize, String searchText);

    boolean sourceIdExists(NewsAgency newsAgency, String sourceId);

    void hide(UUID id);

    void show(UUID id);
}
