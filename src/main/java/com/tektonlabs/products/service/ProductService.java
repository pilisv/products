package com.tektonlabs.products.service;

import com.tektonlabs.products.dto.ProductDTO;

public interface ProductService {
    void create(ProductDTO productDTO);
    ProductDTO getById(Long id);
    void update(Long id, ProductDTO productDTO);
}
