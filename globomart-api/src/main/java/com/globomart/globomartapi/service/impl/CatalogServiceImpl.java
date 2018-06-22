package com.globomart.globomartapi.service.impl;

import com.globomart.globomartapi.client.ProductCatalogClient;
import com.globomart.globomartapi.dto.CatalogDTO;
import com.globomart.globomartapi.dto.DataTableRequestDTO;
import com.globomart.globomartapi.dto.DataTableResponseDTO;
import com.globomart.globomartapi.dto.ResponseDTO;
import com.globomart.globomartapi.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl extends BaseServiceImpl implements CatalogService {

    @Autowired
    ProductCatalogClient productCatalogClient;

    @Override
    public ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO) {
        return productCatalogClient.createCatalog(requestDTO);
    }

    @Override
    public DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO) {
        return productCatalogClient.searchCatalog(requestDTO);
    }
}
