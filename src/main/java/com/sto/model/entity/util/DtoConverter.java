package com.sto.model.entity.util;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DtoConverter {
    public static List convert(List sources, Class target) {
        ModelMapper mapper = new ModelMapper();
        List objects = new ArrayList();
        for (Object source : sources) {
            objects.add(mapper.map(source, target));
        }
        return objects;
    }

    public static List convert(List sources, Function<Object, Object> converter) {
        ModelMapper mapper = new ModelMapper();
        List objects = new ArrayList();
        for (Object source : sources) {
            objects.add(converter.apply(source));
        }
        return objects;
    }
}
