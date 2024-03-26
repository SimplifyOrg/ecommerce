package com.ecommerce.productservice.DTO;

import com.ecommerce.productservice.models.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    private String title;
    private int pageNumber;
    private int pageSize;
    private List<SortParam> sortParamList;

}
