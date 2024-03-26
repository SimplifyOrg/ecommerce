package com.ecommerce.productservice.services;

import com.ecommerce.productservice.DTO.GenericProductDTO;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.models.SortParam;
import com.ecommerce.productservice.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<GenericProductDTO> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParam) {
        Sort sort = null;

        if(sortParam.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParam.get(0).getSortParamName()).ascending();
        }
        else {
            sort = Sort.by(sortParam.get(0).getSortParamName()).descending();
        }

        for(int index = 1; index < sortParam.size(); ++index) {
            if (sortParam.get(index).getSortType().equals(("ASC"))) {
                sort.and(Sort.by(sortParam.get(index).getSortParamName()).ascending());
            }
            else {
                sort.and(Sort.by(sortParam.get(index).getSortParamName()).descending());
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        List<Product> products = productRepository.findAllByTitleContainingIgnoreCase(query, pageRequest);
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for(Product product : products) {
            genericProductDTOS.add(GenericProductDTO.from(product));
        }

        return genericProductDTOS;
    }
}
