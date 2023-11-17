package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.entity.LocationEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

public interface ILocationService extends IBaseService<LocationEntity, Long> {

    boolean existsByVenueName(String venueName);
}
