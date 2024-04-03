package com.sto.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sto.model.dto.SparePartsDto;
import com.sto.model.entity.util.DtoConverter;
import com.sto.service.impl.InventoryServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryServiceImpl inventoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/all")
    public List<SparePartsDto> getAll() {
        return DtoConverter.convert(inventoryService.getAll(), SparePartsDto.class);
    }

    @SneakyThrows
    @PostMapping("/create")
    public void create(@RequestBody String json) {
//        sparePartsDto.setImg();

        inventoryService.create(objectMapper.readValue(json, SparePartsDto.class));
    }
}
