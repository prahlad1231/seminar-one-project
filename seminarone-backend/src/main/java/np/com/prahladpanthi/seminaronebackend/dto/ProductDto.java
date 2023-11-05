package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends BaseDto {

    private String productNumber;

    private String description;

    private Double price;
}
