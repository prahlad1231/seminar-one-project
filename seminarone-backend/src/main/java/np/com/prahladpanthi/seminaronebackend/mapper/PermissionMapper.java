package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.PermissionDto;
import np.com.prahladpanthi.seminaronebackend.entity.PermissionEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissionMapper extends GenericBaseMapperImpl<PermissionEntity, PermissionDto> {

    public PermissionMapper(ModelMapper modelMapper) {
        super(modelMapper, PermissionEntity.class, PermissionDto.class);
    }
}
