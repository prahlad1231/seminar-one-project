package np.com.prahladpanthi.seminaronebackend.controller;

import np.com.prahladpanthi.seminaronebackend.dto.ProductDto;
import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.entity.ProductEntity;
import np.com.prahladpanthi.seminaronebackend.exception.AlreadyExistsException;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.mapper.ProductMapper;
import np.com.prahladpanthi.seminaronebackend.service.IProductService;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping(APIConstants.PRODUCT)
public class ProductController extends BaseController {

    private final IProductService productService;

    private final ProductMapper productMapper;

    private final IUserService userService;

    @Autowired
    public ProductController(IProductService productService,
                             ProductMapper productMapper,
                             IUserService userService) {
        super(userService);
        this.productService = productService;
        this.productMapper = productMapper;
        this.userService = userService;
    }

    @GetMapping(APIConstants.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll() {
        List<ProductEntity> productEntityList = productService.findAll();
        if (productEntityList.isEmpty()) throw new NotFoundException("Products not found!");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", productMapper.mapToDto(productEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstants.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", productMapper.mapToDto(productService.findById(id))), HttpStatus.OK);
    }

    @PostMapping(APIConstants.SAVE)
    public ResponseEntity<ResponseDto> save(@RequestBody ProductDto productDto) {
        if (productDto.getProductNumber().isEmpty() || productDto.getPrice() == null) throw new InsufficientDataException("Please provide all the details!");
        if (productService.existsByProductNumber(productDto.getProductNumber())) throw new AlreadyExistsException("Product already exists! Please check product number!");
        ProductEntity productEntity = productService.save(productMapper.mapToEntity(productDto));
        return new ResponseEntity<>(new ResponseDto("Successfully saved!", productMapper.mapToDto(productEntity)), HttpStatus.CREATED);
    }

    @DeleteMapping(APIConstants.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(new ResponseDto("Successfully deleted!"), HttpStatus.OK);
    }
}
