package com.globomart.globomartapi.service;

import com.globomart.globomartapi.dto.CatalogDTO;
import com.globomart.globomartapi.dto.DataTableRequestDTO;
import com.globomart.globomartapi.dto.DataTableResponseDTO;
import com.globomart.globomartapi.dto.ResponseDTO;

import java.util.List;

public interface CatalogService {

    ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO);

    DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO);
}
