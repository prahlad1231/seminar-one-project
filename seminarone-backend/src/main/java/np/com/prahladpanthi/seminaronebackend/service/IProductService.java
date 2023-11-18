package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.entity.ProductEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

public interface IProductService extends IBaseService<ProductEntity, Long> {

    boolean existsByProductNumber(String productNumber);
}
