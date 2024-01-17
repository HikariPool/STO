package com.flat.model.dto;

import com.flat.model.entity.business.ContentItem;
import lombok.Data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class SessionSyncDto {
    private Map<Long, Float> userTimes = new HashMap<>();
    private ContentItem currentContentItem;


    public SessionSyncDto() {
    }

    public SessionSyncDto(long userId, float time) {
        userTimes.put(userId, time);
    }


    public Optional<Map.Entry<Long, Float>> getEarliestTimeWithout(long userId) {
        return userTimes.entrySet().stream()
                .filter(entry -> entry.getKey() != userId)
                .min(Comparator.comparing(entry -> entry.getValue()));
    }
}
