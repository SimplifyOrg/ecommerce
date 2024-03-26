package com.ecommerce.productservice.controllers;


import com.ecommerce.productservice.DTO.GenericProductDTO;
import com.ecommerce.productservice.DTO.SearchRequestDTO;
import com.ecommerce.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Page<GenericProductDTO> searchProducts(@RequestBody SearchRequestDTO requestDTO) {
        List<GenericProductDTO> genericProductDTOList = searchService.searchProducts(requestDTO.getTitle(),
                requestDTO.getPageNumber(),
                requestDTO.getPageSize(),
                requestDTO.getSortParamList());

        return new PageImpl<>(
                genericProductDTOList
        );
    }
}
