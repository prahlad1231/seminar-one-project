package np.com.prahladpanthi.seminaronebackend.mapper.base;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class GenericBaseMapperImpl<T, DTO> implements IGenericBaseMapper<T, DTO> {

    private ModelMapper modelMapper;

    private Class<T> entityClass;

    private Class<DTO> dtoClass;

    public GenericBaseMapperImpl(ModelMapper modelMapper, Class<T> entityClass, Class<DTO> dtoClass) {
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public DTO mapToDto(T entity) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public List<DTO> mapToDto(List<T> entities) {
        return entities.stream().map(entity -> mapToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public T mapToEntity(DTO dto) {
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public List<T> mapToEntity(List<DTO> dtos) {
        return dtos.stream().map(dto -> mapToEntity(dto)).collect(Collectors.toList());
    }
}
