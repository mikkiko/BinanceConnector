package com.mikkiko.binanceconnector.repositories.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table
public class Bid {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn (name="partial_book_depth_id",referencedColumnName="id")
    private PartialBookDepth partialBookDepth;

    private Double priceLevel;

    private Integer quantity;
}
