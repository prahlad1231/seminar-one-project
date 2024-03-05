package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.RolesDto;
import np.com.prahladpanthi.seminaronebackend.entity.RolesEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesMapper extends GenericBaseMapperImpl<RolesEntity, RolesDto> {

    public RolesMapper(ModelMapper modelMapper) {
        super(modelMapper, RolesEntity.class, RolesDto.class);
    }
}
