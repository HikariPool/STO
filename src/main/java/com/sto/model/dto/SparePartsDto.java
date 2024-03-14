package com.sto.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sto.model.entity.business.SpareParts;
import com.sto.util.Util;
import lombok.Data;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Data
public class SparePartsDto {

    private Long id;
    private String imagePath;
    @JsonIgnore
    private MultipartFile img;

    private Map<Object, Object> params = new HashMap();


    @SneakyThrows
    public static SparePartsDto convertToDto(SpareParts spareParts) {
        SparePartsDto dto = new ModelMapper().map(spareParts, SparePartsDto.class);
        dto.setParams(Util.OBJECT_MAPPER.readValue(spareParts.getJsonParams(), Map.class));
        return dto;
    }

    @SneakyThrows
    public static SpareParts convertToEntity(SparePartsDto sparePartsDto) {
        SpareParts entity = new ModelMapper().map(sparePartsDto, SpareParts.class);
        entity.setJsonParams(Util.OBJECT_MAPPER.writeValueAsString(sparePartsDto.getParams()));
        return entity;
    }
}
