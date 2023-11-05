package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.ReviewerDecisionDto;
import np.com.prahladpanthi.seminaronebackend.entity.ReviewerDecisionEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewerDecisionMapper extends GenericBaseMapperImpl<ReviewerDecisionEntity, ReviewerDecisionDto> {

    public ReviewerDecisionMapper(ModelMapper modelMapper) {
        super(modelMapper, ReviewerDecisionEntity.class, ReviewerDecisionDto.class);
    }
}
