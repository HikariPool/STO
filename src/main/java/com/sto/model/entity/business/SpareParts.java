package com.sto.model.entity.business;

import com.sto.data.constants.Constants;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "spare_parts")
@Data
public class SpareParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePath;
    private String jsonParams;


    @PostLoad
    private void postLoad() {
        this.imagePath = Constants.UPLOAD_URL + imagePath;
    }

    @PrePersist
    @PreUpdate
    private void prePersist() {
        this.imagePath = imagePath != null ? imagePath.replace(Constants.UPLOAD_URL, "") : null;
    }
}
