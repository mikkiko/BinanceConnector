package com.mikkiko.binanceconnector.repositories.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "partial_book_depth")
public class PartialBookDepth {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String eventType;
    private Long eventTime;
    private Long transctionTime;
    private String symbol;
    private String pair;
    private Long firthUpdateId;
    private Long finalUpdateId;
    private Long lastStreamFinalUpdateId;

    @OneToMany(mappedBy = "partialBookDepth", cascade = CascadeType.ALL)
    private List<Bid> bids;

    @OneToMany(mappedBy = "partialBookDepth", cascade = CascadeType.ALL)
    private List<Ask> asks;

}
