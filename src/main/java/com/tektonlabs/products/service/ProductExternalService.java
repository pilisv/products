package com.tektonlabs.products.service;

import com.tektonlabs.products.dto.ExternalInfoDTO;

public interface ProductExternalService {
    ExternalInfoDTO getById(Long productId);
}
