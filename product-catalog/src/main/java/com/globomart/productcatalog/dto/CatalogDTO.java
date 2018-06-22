package com.globomart.productcatalog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CatalogDTO extends BaseEntityDTO<Long> {
    protected Long id;

    private String name;

    private Set<ProductDTO> products = new HashSet<>();
}
