package com.yorku.auction_system.controller;

import com.yorku.auction_system.model.CatalogueItem;
import com.yorku.auction_system.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/catalogue")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    @GetMapping("/search")
    public ModelAndView searchItems(@RequestParam(required = false) String keyword) {
        System.out.println("Keyword: " + keyword); // Add a log here
        if (keyword == null || keyword.isEmpty()) {
            return new ModelAndView("search-form");
        }
        List<CatalogueItem> items = catalogueService.searchItems(keyword);
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("items", items);
        return modelAndView;
    }

    @GetMapping("/active")
    public ModelAndView getActiveItems() {
        List<CatalogueItem> items = catalogueService.getActiveItems();
        ModelAndView modelAndView = new ModelAndView("active");
        modelAndView.addObject("items", items);
        return modelAndView;
    }
}
