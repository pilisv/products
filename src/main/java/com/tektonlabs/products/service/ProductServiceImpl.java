package com.tektonlabs.products.service;

import com.tektonlabs.products.dto.BrandDTO;
import com.tektonlabs.products.dto.ProductDTO;
import com.tektonlabs.products.mapper.ProductMapper;
import com.tektonlabs.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final ProductExternalService productExternalService;

    @Override
    public void create(ProductDTO productDTO) {
        productRepository.save(ProductMapper.dtoToEntity(productDTO));
    }

    @Override
    public ProductDTO getById(Long id) {
        var productDTO = productRepository.findById(id)
                .map(ProductMapper::entityToDTO)
                .orElseThrow();

        //CACHE
        var brandInfo = brandService.getBrandInfo(productDTO.getBrand().getIdBrand());
        productDTO.setBrand(BrandDTO.builder()
                .idBrand(brandInfo.getIdBrand())
                .name(brandInfo.getName())
                .madeIn(brandInfo.getMadeIn())
                .build());

        //API
        productDTO.setExternalInfoDTO(productExternalService.getById(productDTO.getId()));
        return productDTO;
    }

    @Override
    public void update(Long id, ProductDTO productDTO) {
        productDTO.setId(id);
        var entityOpt = productRepository.findById(id);
        if(entityOpt.isPresent())
            productRepository.save(ProductMapper.dtoToEntity(productDTO));
        else
            throw new NoSuchElementException();
    }
}
