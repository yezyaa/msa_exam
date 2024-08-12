package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.dto.request.ProductRequestDto;
import com.sparta.msa_exam.product.dto.response.ProductResponseDto;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productsRepository;

    // 상품 추가
    public ProductResponseDto saveProduct(ProductRequestDto productsRequestDto) {
        Product savedProduct = productsRepository.save(ProductRequestDto.toEntity(productsRequestDto));
        return ProductResponseDto.fromEntity(savedProduct);
    }


    // 상품 전체 조회
    public List<ProductResponseDto> findProducts() {
        List<ProductResponseDto> productsResponse = new ArrayList<>();
        for (Product products : productsRepository.findAll()) {
            productsResponse.add(ProductResponseDto.fromEntity(products));
        }
        return productsResponse;
    }
}
