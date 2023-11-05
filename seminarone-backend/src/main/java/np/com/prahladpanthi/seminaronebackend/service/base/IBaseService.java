package np.com.prahladpanthi.seminaronebackend.service.base;

import java.util.List;

public interface IBaseService<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(ID id);

    boolean existsById(ID id);
}
