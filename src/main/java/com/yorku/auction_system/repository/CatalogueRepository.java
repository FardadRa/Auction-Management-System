package com.yorku.auction_system.repository;

import com.yorku.auction_system.model.CatalogueItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogueRepository extends JpaRepository<CatalogueItem, Long> {
    List<CatalogueItem> findByItemNameContainingIgnoreCase(String keyword);
    List<CatalogueItem> findByStatus(String status);
}