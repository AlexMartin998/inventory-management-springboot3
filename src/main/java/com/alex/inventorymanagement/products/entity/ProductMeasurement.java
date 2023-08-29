package com.alex.inventorymanagement.products.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;


@Data
@Entity
@Table(name = "product_measurement")
@Where(clause = "product.deleted = false")
//@Where(clause = "deleted = false")
public class ProductMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "measurement_type")
    private Double measurementType;

    @Column(name = "measurement_value")
    private Double measurementValue;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;


    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("product_measurement_ref")
    private Product product;            // SII crea la FK aqui

}
