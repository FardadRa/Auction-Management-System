package com.yorku.auction_system.controller;

import com.yorku.auction_system.model.Auction;
import com.yorku.auction_system.model.Bid;
import com.yorku.auction_system.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    /**
     * Endpoint for placing a bid in a forward auction.
     * Expects auctionId, bidderId, and bidAmount as parameters.
     */
    @PostMapping("/forward")
    public ResponseEntity<Auction> placeForwardBid(
            @RequestParam Long auctionId,
            @RequestParam Long bidderId,
            @RequestParam int bidAmount) {
        try {
            Auction updatedAuction = bidService.placeForwardBid(auctionId, bidderId, bidAmount);
            return ResponseEntity.ok(updatedAuction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint for "buy now" action in a Dutch auction.
     * Expects auctionId and bidderId as parameters.
     */
    @PostMapping("/dutch")
    public ResponseEntity<Auction> buyNow(
            @RequestParam Long auctionId,
            @RequestParam Long bidderId) {
        try {
            Auction updatedAuction = bidService.buyNow(auctionId, bidderId);
            return ResponseEntity.ok(updatedAuction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * h
     * Endpoint to retrieve all bids for debugging or verification.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Bid>> getAllBids() {
        return ResponseEntity.ok(bidService.findAllBids());
    }
}
