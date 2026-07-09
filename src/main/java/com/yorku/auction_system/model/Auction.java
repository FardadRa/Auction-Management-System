package com.yorku.auction_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column
    private String itemName;

    @Column
    private String description;

    @Column
    private String auctionType; // "forward" or "dutch"

    @Column
    private Integer startingPrice;

    @Column
    private Integer currentPrice;

    @Column
    private Integer minPrice; // Only for Dutch auctions

    @Column
    private LocalDateTime endTime;

    @Column
    private String status; // "active" or "closed"

    @ManyToOne
    @JoinColumn(name = "current_highest_bidder_id")
    private User currentHighestBidder;
}
