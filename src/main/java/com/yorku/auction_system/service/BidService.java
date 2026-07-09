package com.yorku.auction_system.service;

import com.yorku.auction_system.model.Auction;
import com.yorku.auction_system.model.Bid;
import com.yorku.auction_system.model.User;
import com.yorku.auction_system.repository.AuctionRepository;
import com.yorku.auction_system.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserService userService;

    /**
     * Process a bid for a forward auction.
     */
    public Auction placeForwardBid(Long auctionId, Long bidderId, int bidAmount) throws Exception {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new Exception("Auction not found"));

        if (!auction.getAuctionType().equalsIgnoreCase("forward")) {
            throw new Exception("This is not a forward auction");
        }
        if (!auction.getStatus().equalsIgnoreCase("active")) {
            throw new Exception("Auction is not active");
        }
        if (bidAmount <= auction.getCurrentPrice()) {
            throw new Exception("Bid must be higher than the current price");
        }

        User bidder = userService.findById(bidderId);
        if (bidder == null) {
            throw new Exception("Bidder not found");
        }

        auction.setCurrentPrice(bidAmount);
        auction.setCurrentHighestBidder(bidder);
        Auction updatedAuction = auctionRepository.save(auction);

        // Record the bid in history
        Bid bid = new Bid();
        bid.setAuction(auction);
        bid.setBidderId(bidderId);
        bid.setBidAmount(bidAmount);
        bid.setBidTime(new Date());
        bidRepository.save(bid);

        return updatedAuction;
    }

    /**
     * Process a “Buy Now” action for a Dutch auction.
     */
    public Auction buyNow(Long auctionId, Long bidderId) throws Exception {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new Exception("Auction not found"));

        if (!auction.getAuctionType().equalsIgnoreCase("dutch")) {
            throw new Exception("This is not a Dutch auction");
        }
        if (!auction.getStatus().equalsIgnoreCase("active")) {
            throw new Exception("Auction is not active");
        }

        User bidder = userService.findById(bidderId);
        if (bidder == null) {
            throw new Exception("Bidder not found");
        }

        auction.setCurrentHighestBidder(bidder);
        auction.setStatus("ended");
        Auction updatedAuction = auctionRepository.save(auction);

        // Record the bid in history
        Bid bid = new Bid();
        bid.setAuction(auction);
        bid.setBidderId(bidderId);
        bid.setBidAmount(auction.getCurrentPrice());
        bid.setBidTime(new Date());
        bidRepository.save(bid);

        return updatedAuction;
    }

    /**
     * Retrieve all bids from the database.
     */
    public List<Bid> findAllBids() {
        return bidRepository.findAll();
    }
}
