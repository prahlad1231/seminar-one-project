package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.entity.ProductEntity;
import np.com.prahladpanthi.seminaronebackend.repository.ProductRepository;
import np.com.prahladpanthi.seminaronebackend.service.IProductService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductEntity, Long> implements IProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }
}
