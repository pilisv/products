package com.tektonlabs.products.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class BrandDTO implements Serializable {

    @NotNull(message = "Provide an idBrand")
    private Long idBrand;

    private String name;
    private String madeIn;

}
