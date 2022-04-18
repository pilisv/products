package com.tektonlabs.products.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private Long quantity;

    @OneToOne(mappedBy = "product", fetch=FetchType.LAZY,  cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private ProductDetail detail;

    private Integer idBrand;

}
