package np.com.prahladpanthi.seminaronebackend.service.impl;

import jakarta.transaction.Transactional;
import np.com.prahladpanthi.seminaronebackend.entity.LocationEntity;
import np.com.prahladpanthi.seminaronebackend.repository.LocationRepository;
import np.com.prahladpanthi.seminaronebackend.service.ILocationService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LocationServiceImpl extends BaseServiceImpl<LocationEntity, Long> implements ILocationService {

    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        super(locationRepository);
        this.locationRepository = locationRepository;
    }
}
