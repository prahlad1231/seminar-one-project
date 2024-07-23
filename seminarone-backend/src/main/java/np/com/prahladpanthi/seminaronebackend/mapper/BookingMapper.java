package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.BookingDto;
import np.com.prahladpanthi.seminaronebackend.entity.BookingEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingMapper extends GenericBaseMapperImpl<BookingEntity, BookingDto> {


    public BookingMapper(ModelMapper modelMapper) {
        super(modelMapper, BookingEntity.class, BookingDto.class);
    }
}
