package com.tektonlabs.products.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "brands")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Brand implements Serializable {

    @Id
    @Column(name = "id_brand")
    private Long brandIdBrand;

    @Column(name = "name")
    private String brandName;

    @Column(name = "made_in")
    private String brandMadeIn;

}
