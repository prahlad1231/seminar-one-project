package np.com.prahladpanthi.seminaronebackend.mapper;

import np.com.prahladpanthi.seminaronebackend.dto.BranchDto;
import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.base.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BranchMapper extends GenericBaseMapperImpl<BranchEntity, BranchDto> {

    public BranchMapper(ModelMapper modelMapper) {
        super(modelMapper, BranchEntity.class, BranchDto.class);
    }
}
