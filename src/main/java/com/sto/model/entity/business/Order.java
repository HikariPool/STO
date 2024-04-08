package com.sto.model.entity.business;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedBy
    @ManyToOne(cascade = CascadeType.ALL)
    private User createdBy;
    @OneToOne(cascade = CascadeType.ALL)
    private SpareParts sparePart;
    private String address;
}
