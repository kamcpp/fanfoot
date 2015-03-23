package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Option;

import java.util.List;
import java.util.UUID;

public interface OptionDAO extends GenericDAO<Option> {

    List<Option> getByQuestionId(UUID questionId);
}
