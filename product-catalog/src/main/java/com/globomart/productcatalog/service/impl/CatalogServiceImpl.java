package com.globomart.productcatalog.service.impl;

import com.globomart.productcatalog.dao.entity.Catalog;
import com.globomart.productcatalog.dao.repoService.CatalogRepoService;
import com.globomart.productcatalog.databinder.CatalogDataBinder;
import com.globomart.productcatalog.dto.CatalogDTO;
import com.globomart.productcatalog.dto.DataTableRequestDTO;
import com.globomart.productcatalog.dto.DataTableResponseDTO;
import com.globomart.productcatalog.dto.ResponseDTO;
import com.globomart.productcatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogServiceImpl extends BaseServiceImpl implements CatalogService {

    @Autowired
    CatalogRepoService catalogRepoService;

    @Override
    @Transactional
    public ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO) {
        ResponseDTO<CatalogDTO> responseDTO = new ResponseDTO<CatalogDTO>(Boolean.FALSE, getMessage("unable.to.save.record"), requestDTO);
        //TODO:- Validate Catalog eg check for duplicate catalog etc
        Catalog catalog = CatalogDataBinder.bind(requestDTO);
        try {
            catalog = catalogRepoService.save(catalog);
            requestDTO.setId(catalog.getId());
            responseDTO.setStatus(Boolean.TRUE);
            responseDTO.setMessage(getMessage("record.successfully.saved"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    public DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO) {
        return catalogRepoService.searchCatalog(requestDTO);
    }
}
