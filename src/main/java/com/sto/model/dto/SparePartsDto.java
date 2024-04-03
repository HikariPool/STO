package com.sto.model.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sto.model.entity.business.SpareParts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SparePartsDto {

    private Long id;
    private String imagePath;
    private String imageName;
    private byte[] img;
    private Map<String, String> params = new HashMap<>();

    @SneakyThrows
    public static SparePartsDto convertToDto(SpareParts spareParts) {
        SparePartsDto dto = new ModelMapper().map(spareParts, SparePartsDto.class);
        dto.setParams(new ObjectMapper().readValue(spareParts.getJsonParams(), Map.class));
        return dto;
    }

    @SneakyThrows
    public static SpareParts convertToEntity(SparePartsDto sparePartsDto) {
        SpareParts entity = new ModelMapper().map(sparePartsDto, SpareParts.class);
        entity.setJsonParams(new ObjectMapper().writeValueAsString(sparePartsDto.getParams()));
        return entity;
    }

    @JsonAnyGetter
    public Map<String, String> otherFields() {
        return params;
    }

    @JsonAnySetter
    public void setOtherField(String name, String value) {
        params.put(name, value);
    }
}
