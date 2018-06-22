package com.globomart.productcatalog.service;

import com.globomart.productcatalog.dto.DataTableRequestDTO;
import com.globomart.productcatalog.dto.DataTableResponseDTO;
import com.globomart.productcatalog.dto.ProductDTO;
import com.globomart.productcatalog.dto.ResponseDTO;

import java.util.List;

public interface ProductService {

    ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO);

    ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO);

    DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO);
}
