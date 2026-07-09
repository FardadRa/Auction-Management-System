package com.yorku.auction_system.service;

import com.yorku.auction_system.model.Auction;
import com.yorku.auction_system.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;

    public List<Auction> getAllActiveAuctions() {
        return auctionRepository.findByStatus("active");
    }

    public Auction createAuction(Auction auction) {
        return auctionRepository.save(auction);
    }
}
