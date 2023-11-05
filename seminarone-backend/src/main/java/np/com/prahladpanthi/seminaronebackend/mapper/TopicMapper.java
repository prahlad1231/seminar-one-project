package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.TopicDto;
import np.com.prahladpanthi.seminaronebackend.entity.TopicEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicMapper extends GenericBaseMapperImpl<TopicEntity, TopicDto> {

    public TopicMapper(ModelMapper modelMapper) {
        super(modelMapper, TopicEntity.class, TopicDto.class);
    }
}
