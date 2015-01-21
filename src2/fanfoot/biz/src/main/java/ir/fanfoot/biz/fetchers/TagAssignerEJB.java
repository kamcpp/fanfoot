package ir.fanfoot.biz.fetchers;

import ir.fanfoot.biz.dao.NewsDAO;
import ir.fanfoot.biz.dao.TagDAO;
import ir.fanfoot.domain.News;
import ir.fanfoot.domain.Tag;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
@LocalBean
public class TagAssignerEJB {

    @EJB
    private TagDAO tagDAO;

    @EJB
    private NewsDAO newsDAO;

    public void assignTags(News news) {
        String title = news.getTitle() != null ? news.getTitle().toLowerCase() : null;
        String shortDescription = news.getShortDescription() != null ? news.getShortDescription().toLowerCase() : null;
        String fullDescription = news.getFullDescription() != null ? news.getFullDescription().toLowerCase() : null;
        String newsLink = news.getSourceLink() != null ? news.getSourceLink().toLowerCase() : null;
        String imageLink = news.getImageLink() != null ? news.getImageLink().toLowerCase() : null;
        String videoLink = news.getVideoLink() != null ? news.getVideoLink().toLowerCase() : null;
        List<Tag> tags = tagDAO.getAll();
        Set<String> tagNames = new HashSet<>();
        for (Tag tag : tags) {
            if (tag.getKeywords() != null) {
                String[] keywordTokens = tag.getKeywords().split(",");
                for (String keyword : keywordTokens) {
                    keyword = keyword.trim().toLowerCase();
                    if (keyword.length() > 0) {
                        if ((title != null && title.contains(keyword)) ||
                                (newsLink != null && newsLink.contains(keyword)) ||
                                (imageLink != null && imageLink.contains(keyword)) ||
                                (videoLink != null && videoLink.contains(keyword)) ||
                                (shortDescription != null && shortDescription.contains(keyword)) ||
                                (fullDescription != null && fullDescription.contains(keyword))) {
                            tagNames.add(tag.getName());
                        }
                    }
                }
            }
        }
        if (tagNames.size() > 0) {
            newsDAO.addTags(news, tagNames.toArray(new String[]{}));
        }
    }
}
