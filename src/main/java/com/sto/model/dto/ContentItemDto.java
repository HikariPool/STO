package com.sto.model.dto;

import com.sto.model.entity.business.ContentItem;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ContentItemDto {
    private Long id;
    private String title;
    private Long order;
    private String previewPath;
    private String sourcePath;


    public static ContentItemDto convertToDto(ContentItem contentItem) {
        return new ModelMapper().map(contentItem, ContentItemDto.class);
    }

    public static ContentItem convertToEntity(ContentItemDto contentItemDto) {
        return new ModelMapper().map(contentItemDto, ContentItem.class);
    }
}
