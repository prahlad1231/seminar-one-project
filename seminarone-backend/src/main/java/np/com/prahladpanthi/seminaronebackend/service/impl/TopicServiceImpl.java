package np.com.prahladpanthi.seminaronebackend.service.impl;

import jakarta.transaction.Transactional;
import np.com.prahladpanthi.seminaronebackend.entity.TopicEntity;
import np.com.prahladpanthi.seminaronebackend.repository.TopicRepository;
import np.com.prahladpanthi.seminaronebackend.service.ITopicService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TopicServiceImpl extends BaseServiceImpl<TopicEntity, Long> implements ITopicService {

    private TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        super(topicRepository);
        this.topicRepository = topicRepository;
    }
}
