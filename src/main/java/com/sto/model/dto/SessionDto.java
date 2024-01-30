package com.sto.model.dto;

import com.sto.model.entity.business.Session;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class SessionDto {
    private Long id;
    private String title;
    private String imagePath;
    private List<ContentItemDto> contentItems;


    public static SessionDto convertToDto(Session session) {
        return new ModelMapper().map(session, SessionDto.class);
    }

    public static Session convertToEntity(SessionDto sessionDto) {
        return new ModelMapper().map(sessionDto, Session.class);
    }
}
