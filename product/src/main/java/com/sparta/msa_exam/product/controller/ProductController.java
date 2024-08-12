package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.request.ProductRequestDto;
import com.sparta.msa_exam.product.dto.response.ProductResponseDto;
import com.sparta.msa_exam.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productsService;

    // 상품 추가
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ProductRequestDto productsRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productsService.saveProduct(productsRequestDto));
    }

    // 상품 전체 조회
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productsService.findProducts());
    }
}
