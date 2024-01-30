package com.sto.model.dto;

import com.sto.model.entity.business.Message;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@Builder
public class ChatDto {

    public static ChatDto convertToDto(Message message) {
        return new ModelMapper().map(message, ChatDto.class);
    }
}