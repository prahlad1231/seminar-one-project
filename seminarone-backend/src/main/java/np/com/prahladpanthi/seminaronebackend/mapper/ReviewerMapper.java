package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.ReviewerDto;
import np.com.prahladpanthi.seminaronebackend.entity.ReviewerEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewerMapper extends GenericBaseMapperImpl<ReviewerEntity, ReviewerDto> {

    public ReviewerMapper(ModelMapper modelMapper) {
        super(modelMapper, ReviewerEntity.class, ReviewerDto.class);
    }
}
