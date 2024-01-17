package com.flat.model.entity.business;

import com.flat.data.constants.Constants;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sessions")
@EntityListeners(AuditingEntityListener.class)
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String imagePath;

    @CreatedBy
    @ManyToOne(cascade = CascadeType.DETACH)
    private User createdBy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//PERSIST
    @JoinColumn(name = "session_id")
    private List<ContentItem> contentItems;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "sessions")
    private List<User> participants;


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
