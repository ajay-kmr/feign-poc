package com.globomart.globomartapi.dto;

import com.globomart.globomartapi.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseEntityDTO<Long> {
    protected Long id;

    private String name;

    private ProductType type;

    protected Long catalogId;

    private String catalogName;

}
