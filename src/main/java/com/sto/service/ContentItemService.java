package com.sto.service;

import com.sto.model.dto.ContentItemDto;
import com.sto.model.entity.business.ContentItem;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ContentItemService {

    List<ContentItemDto> getContentItemsBy(Long sessionId);

    @Transactional
    void create(Long sessionId, MultipartFile image, MultipartFile media, ContentItem contentItem) throws IOException;
}
