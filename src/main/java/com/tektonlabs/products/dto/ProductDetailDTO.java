package com.tektonlabs.products.dto;

import lombok.*;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetailDTO implements Serializable {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Must contain only letters and numbers")
    private String description;
    private String color;
    private Double width;
    private Double high;
    private Double large;
    private Double weight;

    private ProductDTO product;

}
