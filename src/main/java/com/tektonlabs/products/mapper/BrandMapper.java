package com.tektonlabs.products.mapper;

import com.tektonlabs.products.dto.BrandDTO;
import com.tektonlabs.products.model.Brand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class BrandMapper {

    private static ModelMapper modelMapper  = new ModelMapper();

    public static BrandDTO entityToDTO(Brand entity) {
        return entity == null ? null : modelMapper.map(entity, BrandDTO.class);
    }

    public static Brand dtoToEntity(BrandDTO dto) {
        return dto == null ? null : modelMapper.map(dto, Brand.class);
    }
}
