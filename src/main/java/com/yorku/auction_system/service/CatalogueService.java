package com.yorku.auction_system.service;

import com.yorku.auction_system.model.CatalogueItem;
import com.yorku.auction_system.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueService {

    @Autowired
    private CatalogueRepository catalogueRepository;

    public List<CatalogueItem> searchItems(String keyword) {
        return catalogueRepository.findByItemNameContainingIgnoreCase(keyword);
    }

    public List<CatalogueItem> getActiveItems() {
        return catalogueRepository.findByStatus("active");
    }
}