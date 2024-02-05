package com.ecommerce.productservice.services;

import com.ecommerce.productservice.DTO.FakeStoreProductDTO;
import com.ecommerce.productservice.DTO.GenericProductDTO;
import com.ecommerce.productservice.clients.FakeStoreClient;
import com.ecommerce.productservice.exceptions.ProductNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private final FakeStoreClient fakeStoreClient;

    FakeStoreProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
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
    public GenericProductDTO getProductById(Long id) throws ProductNotFound {
        return convertToGenericProductDTO(this.fakeStoreClient.getProductById(id));
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<FakeStoreProductDTO> fakeStoreProductDTOList = this.fakeStoreClient.getAllProducts();
        List<GenericProductDTO> result = new ArrayList<GenericProductDTO>();
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList) {
            result.add(convertToGenericProductDTO((fakeStoreProductDTO)));
        }
        return result;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) throws ProductNotFound {
        return convertToGenericProductDTO(this.fakeStoreClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) throws ProductNotFound {
        return convertToGenericProductDTO(this.fakeStoreClient.updateProductById(id, genericProductDTO));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) throws Exception {
        return convertToGenericProductDTO(this.fakeStoreClient.createProduct(genericProductDTO));
    }
}
