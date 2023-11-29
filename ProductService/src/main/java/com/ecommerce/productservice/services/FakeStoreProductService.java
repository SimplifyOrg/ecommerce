package com.ecommerce.productservice.services;

import com.ecommerce.productservice.DTO.FakeStoreProductDTO;
import com.ecommerce.productservice.DTO.GenericProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    RestTemplateBuilder restTemplateBuilder;
    String singleProductURL = "https://fakestoreapi.com/products/{id}";
    String genericProductURL = "https://fakestoreapi.com/products";


    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private static GenericProductDTO convertToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity(singleProductURL, FakeStoreProductDTO.class, id);

        return convertToGenericProductDTO(fakeStoreProductDTOResponseEntity.getBody());
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreAllProductsResponseEntity = restTemplate.getForEntity(genericProductURL, FakeStoreProductDTO[].class);
        List<FakeStoreProductDTO> fakeStoreProductDTOList = List.of(fakeStoreAllProductsResponseEntity.getBody());
        List<GenericProductDTO> result = new ArrayList<GenericProductDTO>();
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList) {
            result.add(convertToGenericProductDTO((fakeStoreProductDTO)));
        }
        return result;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(singleProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return convertToGenericProductDTO(responseEntity.getBody());
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDTO, FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(singleProductURL, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return convertToGenericProductDTO(responseEntity.getBody());
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(genericProductURL, genericProductDTO, FakeStoreProductDTO.class);

            return convertToGenericProductDTO(responseEntity.getBody());
    }
}
