package com.yorku.auction_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "catalogue_items")
public class CatalogueItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "auction_type")
    private String auctionType;

    @Column(name = "current_price")
    private int currentPrice;

    @Column(name = "status")
    private String status; // "active" or "closed"

    @Column(name = "shipping_days")
    private int shippingDays;
}