package com.tektonlabs.products.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.tektonlabs.products.dto.ProductDTO;
import com.tektonlabs.products.dto.ProductDetailDTO;
import com.tektonlabs.products.model.Product;
import com.tektonlabs.products.model.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;

import java.lang.reflect.InvocationTargetException;

@AllArgsConstructor
public class ProductMapper {

    /*@SneakyThrows
    public static ProductDTO entityToDTO(Product entity)  {
        if(entity == null) return null;

        var dto = new ProductDTO();
        BeanUtils.copyProperties(dto, entity);

        var detail = new ProductDetailDTO();
        BeanUtils.copyProperties(detail, entity.getDetail());

        dto.setDetail(detail);

        return dto;
    }

    @SneakyThrows
    public static Product dtoToEntity(ProductDTO dto) {
        if(dto == null) return null;

        var entity = new Product();
        BeanUtils.copyProperties(entity, dto);

        var detail = new ProductDetail();
        BeanUtils.copyProperties(detail, dto.getDetail());

        entity.setDetail(detail);
        detail.setProduct(entity);

        return entity;
    }*/

    private static ModelMapper modelMapper  = new ModelMapper();

    public static ProductDTO entityToDTO(Product entity) {
        if(entity == null) return null;

        modelMapper.getConfiguration().setPreferNestedProperties(false);

        modelMapper.typeMap(Product.class, ProductDTO.class).addMappings(mapper -> {
            mapper.skip(ProductDTO::setDetail);
        }).map(entity);
        modelMapper.typeMap(ProductDetail.class, ProductDetailDTO.class).addMappings(mapper -> {
            mapper.skip(ProductDetailDTO::setProduct);
        }).map(entity.getDetail());

        var dto = modelMapper.map(entity, ProductDTO.class);
        dto.setDetail(modelMapper.map(entity.getDetail(), ProductDetailDTO.class));

        return dto;
    }

    public static Product dtoToEntity(ProductDTO dto) {
        if(dto == null) return null;

        dto.getDetail().setProduct(dto);
        return modelMapper.map(dto, Product.class);
    }
}
