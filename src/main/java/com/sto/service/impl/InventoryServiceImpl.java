package com.sto.service.impl;

import com.sto.model.dto.SparePartsDto;
import com.sto.model.entity.business.SpareParts;
import com.sto.repository.SparePartsRepo;
import com.sto.service.FileService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class InventoryServiceImpl {

    @Autowired
    private SparePartsRepo sparePartsRepo;
    @Autowired
    private FileService fileService;


    public List<SpareParts> getAll() {
        return sparePartsRepo.findAll();
    }

    @SneakyThrows
    @Transactional
    public void create(SparePartsDto sparePartsDto) {
        SpareParts spareParts = SparePartsDto.convertToEntity(sparePartsDto);

        String imageFileTitle = null;
        MultipartFile img = sparePartsDto.getImg();
        if (img != null) {
            imageFileTitle = fileService.write(img.getBytes(),
                    fileService.getMemType(img.getOriginalFilename()));
        }
        spareParts.setImagePath(imageFileTitle);

        sparePartsRepo.saveAndFlush(spareParts);
    }

}