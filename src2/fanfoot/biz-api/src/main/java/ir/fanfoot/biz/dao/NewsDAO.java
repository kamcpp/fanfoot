package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.News;
import ir.fanfoot.domain.NewsAgency;

import java.util.List;
import java.util.UUID;

public interface NewsDAO extends GenericDAO<News> {

    long count();

    boolean sourceIdExists(NewsAgency newsAgency, String sourceId);

    void hide(UUID id);

    void show(UUID id);

    void addTags(News news, String[] tagStrings);

    void addTags(News news, List<String> tagStrings);
}
