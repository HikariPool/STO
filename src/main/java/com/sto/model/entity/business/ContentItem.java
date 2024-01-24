package com.sto.model.entity.business;

import com.sto.data.constants.Constants;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "content_items")
public class ContentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(name = "item_order")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long order;
    private String previewPath;
    private String sourcePath;


    @PostLoad
    private void postLoad() {
        previewPath = Constants.UPLOAD_URL + previewPath;
    }

    @PrePersist
    @PreUpdate
    private void prePersist() {
        previewPath = previewPath != null ? previewPath.replace(Constants.UPLOAD_URL, "") : null;
    }
}
