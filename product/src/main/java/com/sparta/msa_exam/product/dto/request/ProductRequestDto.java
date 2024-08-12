package com.sparta.msa_exam.product.dto.request;

import com.sparta.msa_exam.product.domain.Product;
import lombok.Builder;

@Builder
public class ProductRequestDto {
    private String name;
    private Integer supplyPrice;

    public static Product toEntity(ProductRequestDto productsRequestDto) {
        return Product.builder()
                .name(productsRequestDto.name)
                .supplyPrice(productsRequestDto.supplyPrice)
                .build();
    }
}
