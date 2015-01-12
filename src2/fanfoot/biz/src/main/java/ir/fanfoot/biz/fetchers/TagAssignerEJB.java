package ir.fanfoot.biz.fetchers;

import ir.fanfoot.biz.dao.TagDAO;
import ir.fanfoot.domain.News;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class TagAssignerEJB {

    @EJB
    private TagDAO tagDAO;

    public void assignTags(News news) {
        // TODO
    }
}
