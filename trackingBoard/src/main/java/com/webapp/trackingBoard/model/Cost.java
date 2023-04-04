package com.webapp.trackingBoard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
@Table(name = "cost")
public class Cost {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer plannedCost;
    private Integer budget;
    private Integer actualCost;



}
