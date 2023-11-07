package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.entity.ApplicationFormEntity;
import np.com.prahladpanthi.seminaronebackend.repository.ApplicationFormRepository;
import np.com.prahladpanthi.seminaronebackend.service.IApplicationFormService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ApplicationFormServiceImpl extends BaseServiceImpl<ApplicationFormEntity, Long> implements IApplicationFormService {

    private ApplicationFormRepository applicationFormRepository;

    public ApplicationFormServiceImpl(ApplicationFormRepository applicationFormRepository) {
        super(applicationFormRepository);
        this.applicationFormRepository = applicationFormRepository;
    }
}
