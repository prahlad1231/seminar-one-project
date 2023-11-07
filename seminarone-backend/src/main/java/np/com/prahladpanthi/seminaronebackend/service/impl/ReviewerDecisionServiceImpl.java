package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.entity.ReviewerDecisionEntity;
import np.com.prahladpanthi.seminaronebackend.repository.ReviewerDecisionRepository;
import np.com.prahladpanthi.seminaronebackend.service.IReviewerDecisionService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ReviewerDecisionServiceImpl extends BaseServiceImpl<ReviewerDecisionEntity, Long> implements IReviewerDecisionService {


    private ReviewerDecisionRepository reviewerDecisionRepository;

    public ReviewerDecisionServiceImpl(ReviewerDecisionRepository reviewerDecisionRepository) {
        super(reviewerDecisionRepository);
        this.reviewerDecisionRepository = reviewerDecisionRepository;
    }
}
