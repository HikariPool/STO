package com.sto.controller.rest;

import com.sto.model.dto.ContentItemDto;
import com.sto.service.ContentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/session")
public class ContentItemController {

    @Autowired
    private ContentItemService contentItemService;


    @PostMapping("/content/create")
    public void addContentItem(@RequestParam("session_id") Long sessionId,
                               @RequestParam(value = "img", required = false) MultipartFile image,
                               @RequestParam(value = "media", required = false) MultipartFile media,
                               ContentItemDto contentItemDto) throws IOException {
        String title = contentItemDto.getTitle();
        if (title != null && !title.isEmpty()) {
            contentItemService.create(sessionId, image, media, ContentItemDto.convertToEntity(contentItemDto));
            return;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Title is empty!");
    }
}
