package com.tektonlabs.products;

import com.tektonlabs.products.dto.BrandDTO;
import com.tektonlabs.products.dto.ProductDTO;
import com.tektonlabs.products.dto.ProductDetailDTO;
import com.tektonlabs.products.mapper.ProductMapper;
import com.tektonlabs.products.repository.BrandRepository;
import com.tektonlabs.products.repository.ProductRepository;
import com.tektonlabs.products.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void init(){
        productRepository = mock(ProductRepository.class);
        BrandRepository brandRepository = mock(BrandRepository.class);

        BrandService brandService = new BrandServiceImpl(brandRepository);
        ProductExternalService productExternalService = new ProductExternalServiceImpl();
        productService = new ProductServiceImpl(productRepository, brandService, productExternalService);
    }

    @Test
    public void whenUpdate_ThrowNoSuchElement() {
        Long id = 30L;
        var productDTO = ProductDTO.builder()
                .name("Mirinda")
                .price(BigDecimal.valueOf(50))
                .quantity(10L)
                .brand(BrandDTO.builder().idBrand(5L).build())
                .detail(ProductDetailDTO.builder()
                        .color("orange")
                        .build())
                .build();

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> productService.update(id, productDTO));

    }

    @Test
    public void whenUpdate_Success() {
        Long id = 30L;
        var productDTOOld = ProductDTO.builder()
                .id(id)
                .name("Mirinda")
                .price(BigDecimal.valueOf(50))
                .quantity(10L)
                .brand(BrandDTO.builder().idBrand(5L).build())
                .detail(ProductDetailDTO.builder()
                        .color("orange")
                        .build())
                .build();

        var productDTONew = ProductDTO.builder()
                .name("Mirinda")
                .price(BigDecimal.valueOf(50))
                .quantity(15L)
                .brand(BrandDTO.builder().idBrand(5L).build())
                .detail(ProductDetailDTO.builder()
                        .color("orange")
                        .build())
                .build();

        var productEntityOpt = Optional.of(ProductMapper.dtoToEntity(productDTOOld));
        when(productRepository.findById(id)).thenReturn(productEntityOpt);
        productService.update(id, productDTONew);
        //productEntityOpt.get().setId(id);
        verify(productRepository, times(1)).save(any());

    }
}
