package np.com.prahladpanthi.seminaronebackend.service.impl;

import jakarta.transaction.Transactional;
import np.com.prahladpanthi.seminaronebackend.entity.ReviewerEntity;
import np.com.prahladpanthi.seminaronebackend.repository.ReviewerRepository;
import np.com.prahladpanthi.seminaronebackend.service.IReviewerService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReviewerServiceImpl extends BaseServiceImpl<ReviewerEntity, Long> implements IReviewerService {

    private ReviewerRepository reviewerRepository;

    public ReviewerServiceImpl(ReviewerRepository reviewerRepository) {
        super(reviewerRepository);
        this.reviewerRepository = reviewerRepository;
    }
}
