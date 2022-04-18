package com.tektonlabs.products.service;

import com.tektonlabs.products.dto.BrandDTO;

public interface BrandService {

    BrandDTO getBrandInfo(Long brandId);
}
