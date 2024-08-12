package com.sparta.msa_exam.product.dto.response;

import com.sparta.msa_exam.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private Integer supplyPrice;

    public static ProductResponseDto fromEntity(Product entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .supplyPrice(entity.getSupplyPrice())
                .build();
    }
}
