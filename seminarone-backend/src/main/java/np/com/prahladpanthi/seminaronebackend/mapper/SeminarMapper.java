package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.SeminarDto;
import np.com.prahladpanthi.seminaronebackend.entity.SeminarEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeminarMapper extends GenericBaseMapperImpl<SeminarEntity, SeminarDto> {

    public SeminarMapper(ModelMapper modelMapper) {
        super(modelMapper, SeminarEntity.class, SeminarDto.class);
    }
}
