package com.tektonlabs.products.service;

import com.tektonlabs.products.dto.ExternalInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductExternalServiceImpl implements ProductExternalService{

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${api.url}")
    private String URL;

    @Override
    public ExternalInfoDTO getById(Long productId) {
        return restTemplate.getForObject(URL + productId, ExternalInfoDTO.class);
    }

}
