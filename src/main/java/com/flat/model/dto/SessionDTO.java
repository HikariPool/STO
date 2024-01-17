package com.flat.model.dto;

import com.flat.model.entity.business.Session;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class SessionDTO {
    private Long id;
    private String title;
    private String imagePath;
    private List<ContentItemDto> contentItems;


    public static SessionDTO convertToDto(Session session) {
        return new ModelMapper().map(session, SessionDTO.class);
    }

    public static Session convertToEntity(SessionDTO sessionDto) {
        return new ModelMapper().map(sessionDto, Session.class);
    }
}
