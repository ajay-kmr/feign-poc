package com.globomart.globomartapi.controller;


import com.globomart.globomartapi.dto.CatalogDTO;
import com.globomart.globomartapi.dto.DataTableRequestDTO;
import com.globomart.globomartapi.dto.DataTableResponseDTO;
import com.globomart.globomartapi.dto.ResponseDTO;
import com.globomart.globomartapi.service.CatalogService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/catalog")
@CommonsLog
public class CatalogController extends BaseController {

    @Autowired
    CatalogService catalogService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDTO<CatalogDTO> createCatalog(@RequestBody CatalogDTO requestDTO) {
        return catalogService.createCatalog(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(@RequestBody DataTableRequestDTO<CatalogDTO> requestDTO) {
        return catalogService.searchCatalog(requestDTO);
    }
}
