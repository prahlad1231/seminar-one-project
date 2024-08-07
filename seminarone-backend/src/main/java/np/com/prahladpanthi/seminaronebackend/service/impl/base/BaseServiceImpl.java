package np.com.prahladpanthi.seminaronebackend.service.impl.base;

import np.com.prahladpanthi.seminaronebackend.entity.BaseEntity;
import np.com.prahladpanthi.seminaronebackend.exception.CannotDeleteDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl <T extends BaseEntity, ID> implements IBaseService<T, ID> {

    protected JpaRepository<T, ID> repository;

    public BaseServiceImpl() {}

    public BaseServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T findById(ID id) {
        Optional<T> optionalT = repository.findById(id);
        return optionalT.orElseThrow(NotFoundException::new);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}
