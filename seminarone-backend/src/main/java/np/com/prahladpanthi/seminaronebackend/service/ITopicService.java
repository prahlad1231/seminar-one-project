package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.entity.TopicEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

public interface ITopicService extends IBaseService<TopicEntity, Long> {

    boolean existsByName(String name);
}
