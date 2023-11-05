package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.UserDto;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapper extends GenericBaseMapperImpl<UserEntity, UserDto> {

    public UserMapper(ModelMapper modelMapper) {
        super(modelMapper, UserEntity.class, UserDto.class);
    }
}
