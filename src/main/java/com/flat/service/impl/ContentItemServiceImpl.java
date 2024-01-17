package com.flat.service.impl;

import com.flat.model.dto.ContentItemDto;
import com.flat.model.entity.business.ContentItem;
import com.flat.model.entity.util.DtoConverter;
import com.flat.repository.ContentItemRepo;
import com.flat.repository.SessionRepo;
import com.flat.service.ContentItemService;
import com.flat.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ContentItemServiceImpl implements ContentItemService {
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private ContentItemRepo contentItemRepo;
    @Autowired
    private FileService fileService;


    @Override
    public List<ContentItemDto> getContentItemsBy(Long sessionId) {
        return DtoConverter.convert(contentItemRepo.getBy(sessionId), ContentItemDto.class);
    }

    @Override
    public void create(Long sessionId, MultipartFile image, MultipartFile media, ContentItem contentItem) throws IOException {
        String imageFileTitle = null;
        String mediaFileTitle = null;
        if (image != null) {
            imageFileTitle = fileService.write(image.getBytes(),
                    getMemType(image.getOriginalFilename()));
        }
        if (media != null) {
            mediaFileTitle = fileService.write(media.getBytes(),
                    getMemType(media.getOriginalFilename()));
        }


        contentItem.setPreviewPath(imageFileTitle);
        contentItem.setSourcePath(mediaFileTitle);
        sessionRepo.findById(sessionId).get().getContentItems().add(contentItem);
    }

    private String getMemType(String fileTitle) {
        return fileTitle.substring(fileTitle.lastIndexOf("."));
    }

}
