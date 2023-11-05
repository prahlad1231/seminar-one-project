package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.LocationDto;
import np.com.prahladpanthi.seminaronebackend.entity.LocationEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationMapper extends GenericBaseMapperImpl<LocationEntity, LocationDto> {

    public LocationMapper(ModelMapper modelMapper) {
        super(modelMapper, LocationEntity.class, LocationDto.class);
    }
}
