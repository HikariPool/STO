package com.sto.controller.rest;

import com.sto.model.dto.SparePartsDto;
import com.sto.model.entity.util.DtoConverter;
import com.sto.service.impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImpl inventoryService;


    @GetMapping("/all")
    public List<SparePartsDto> getAll() {
        return DtoConverter.convert(inventoryService.getAll(), SparePartsDto.class);
    }

    @GetMapping("/create")
    public void create(@RequestParam(value = "img", required = false) MultipartFile image,
                       @RequestParam(value = "dto", required = false) SparePartsDto sparePartsDto) {
        sparePartsDto.setImg(image);
        inventoryService.create(sparePartsDto);
    }
}
