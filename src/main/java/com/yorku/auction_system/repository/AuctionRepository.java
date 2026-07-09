package com.yorku.auction_system.repository;

import com.yorku.auction_system.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findByAuctionType(String auctionType);
    List<Auction> findByStatus(String status);
}
