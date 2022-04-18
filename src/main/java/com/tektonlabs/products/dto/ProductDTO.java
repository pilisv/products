package com.tektonlabs.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ProductDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is mandatory")
    @NotNull(message = "Provide a name")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Must contain only letters")
    private String name;

    @NotNull(message = "Provide a price")
    private BigDecimal price;

    @NotNull(message = "Provide a quantity")
    private Long quantity;

    private BrandDTO brand;
    private ProductDetailDTO detail;
    private ExternalInfoDTO externalInfoDTO;

}
