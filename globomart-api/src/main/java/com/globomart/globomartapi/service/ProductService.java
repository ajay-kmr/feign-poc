package com.globomart.globomartapi.service;

import com.globomart.globomartapi.dto.DataTableRequestDTO;
import com.globomart.globomartapi.dto.DataTableResponseDTO;
import com.globomart.globomartapi.dto.ProductDTO;
import com.globomart.globomartapi.dto.ResponseDTO;

import java.util.List;

public interface ProductService {

    ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO);

    ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO);

    DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO);
}
