package com.globomart.productcatalog.service;

import com.globomart.productcatalog.dto.CatalogDTO;
import com.globomart.productcatalog.dto.DataTableRequestDTO;
import com.globomart.productcatalog.dto.DataTableResponseDTO;
import com.globomart.productcatalog.dto.ResponseDTO;

import java.util.List;

public interface CatalogService {

    ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO);

    DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO);
}
