package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.ApplicationFormDto;
import np.com.prahladpanthi.seminaronebackend.entity.ApplicationFormEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationFormMapper extends GenericBaseMapperImpl<ApplicationFormEntity, ApplicationFormDto> {

    public ApplicationFormMapper(ModelMapper modelMapper) {
        super(modelMapper, ApplicationFormEntity.class, ApplicationFormDto.class);
    }
}
