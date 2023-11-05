package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.StaffDto;
import np.com.prahladpanthi.seminaronebackend.entity.StaffEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaffMapper extends GenericBaseMapperImpl<StaffEntity, StaffDto> {

    public StaffMapper(ModelMapper modelMapper) {
        super(modelMapper, StaffEntity.class, StaffDto.class);
    }
}
