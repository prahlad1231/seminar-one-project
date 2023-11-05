package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.ProductDto;
import np.com.prahladpanthi.seminaronebackend.entity.ProductEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapper extends GenericBaseMapperImpl<ProductEntity, ProductDto> {

    public ProductMapper(ModelMapper modelMapper) {
        super(modelMapper, ProductEntity.class, ProductDto.class);
    }
}
