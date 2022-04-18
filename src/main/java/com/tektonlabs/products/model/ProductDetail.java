package com.tektonlabs.products.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products_detail")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetail implements Serializable {

    @Id
    private Long idProduct;

    private String description;
    private String color;
    private Double width;
    private Double high;
    private Double large;
    private Double weight;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    @MapsId
    @ToString.Exclude
    private Product product;

}
