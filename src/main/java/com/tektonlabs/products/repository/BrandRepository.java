package com.tektonlabs.products.repository;

import com.tektonlabs.products.model.Brand;
import com.tektonlabs.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}
