package com.ecommerce.productservice.clients;

import com.ecommerce.productservice.DTO.FakeStoreProductDTO;
import com.ecommerce.productservice.DTO.GenericProductDTO;
import com.ecommerce.productservice.exceptions.ProductNotFound;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Component
public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;
    private String singleProductURL; //= "https://fakestoreapi.com/products/{id}";
    private String genericProductURL; //= "https://fakestoreapi.com/products";

    FakeStoreClient(RestTemplateBuilder restTemplateBuilder,
                    @Value("${fakestore.generic.url}") String fakeStoreURL,
                    @Value("${fakestore.product}") String productPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.singleProductURL = fakeStoreURL + productPath + "/{id}";
        this.genericProductURL = fakeStoreURL + productPath;
    }
    public FakeStoreProductDTO getProductById(Long id) throws ProductNotFound {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity(singleProductURL, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO == null) {
            throw new ProductNotFound(id);
        }
        return fakeStoreProductDTO;
    }

    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreAllProductsResponseEntity = restTemplate.getForEntity(genericProductURL, FakeStoreProductDTO[].class);
        return List.of(fakeStoreAllProductsResponseEntity.getBody());
    }

    public FakeStoreProductDTO deleteProductById(Long id) throws ProductNotFound {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(singleProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        if(fakeStoreProductDTO == null) {
            throw new ProductNotFound(id);
        }
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) throws ProductNotFound {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDTO, FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(singleProductURL, HttpMethod.PUT, requestCallback, responseExtractor, id);
        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        if(fakeStoreProductDTO == null) {
            throw new ProductNotFound(id);
        }
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO createProduct(GenericProductDTO genericProductDTO) throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(genericProductURL, genericProductDTO, FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        if(fakeStoreProductDTO == null) {
            throw new Exception("Product creation failed");
        }
        return fakeStoreProductDTO;
    }
}
