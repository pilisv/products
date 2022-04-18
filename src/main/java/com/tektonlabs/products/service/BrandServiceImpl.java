package com.tektonlabs.products.service;

import com.tektonlabs.products.dto.BrandDTO;
import com.tektonlabs.products.mapper.BrandMapper;
import com.tektonlabs.products.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    @Cacheable("product-catalog")
    public BrandDTO getBrandInfo(Long brandId){
        log.debug("brandId: {} is not catched", brandId);
        return brandRepository.findById(brandId).map(BrandMapper::entityToDTO).orElseThrow();
    }

}
