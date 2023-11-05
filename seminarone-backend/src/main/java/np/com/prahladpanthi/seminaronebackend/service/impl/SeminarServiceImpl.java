package np.com.prahladpanthi.seminaronebackend.service.impl;

import jakarta.transaction.Transactional;
import np.com.prahladpanthi.seminaronebackend.entity.SeminarEntity;
import np.com.prahladpanthi.seminaronebackend.repository.SeminarRepository;
import np.com.prahladpanthi.seminaronebackend.service.ISeminarService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SeminarServiceImpl extends BaseServiceImpl<SeminarEntity, Long> implements ISeminarService {

    private SeminarRepository seminarRepository;

    public SeminarServiceImpl(SeminarRepository seminarRepository) {
        super(seminarRepository);
        this.seminarRepository = seminarRepository;
    }
}
