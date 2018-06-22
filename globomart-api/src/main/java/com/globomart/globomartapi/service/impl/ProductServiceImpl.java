package com.globomart.globomartapi.service.impl;

import com.globomart.globomartapi.client.ProductCatalogClient;
import com.globomart.globomartapi.dto.DataTableRequestDTO;
import com.globomart.globomartapi.dto.DataTableResponseDTO;
import com.globomart.globomartapi.dto.ProductDTO;
import com.globomart.globomartapi.dto.ResponseDTO;
import com.globomart.globomartapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

    @Autowired
    ProductCatalogClient productCatalogClient;

    @Override
    public ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO) {
        return productCatalogClient.createProduct(requestDTO);
    }

    @Override
    public ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO) {
        return productCatalogClient.removeProduct(requestDTO);
    }

    public DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO) {
        return productCatalogClient.searchProduct(requestDTO);
    }
}
