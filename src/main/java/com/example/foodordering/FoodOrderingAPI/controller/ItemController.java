package com.example.foodordering.FoodOrderingAPI.controller;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateItemRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.ItemResponse;
import com.example.foodordering.FoodOrderingAPI.services.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/restaurant/{restaurantId}")
    public ItemResponse createItem(
            @PathVariable Long restaurantId,
            @Valid @RequestBody CreateItemRequest request) {

        return itemService.createItem(restaurantId, request);
    }

    @GetMapping("/{id}")
    public ItemResponse getItemById(
            @PathVariable Long id) {

        return itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public ItemResponse updateItem(
            @PathVariable Long id,
            @Valid @RequestBody CreateItemRequest request) {

        return itemService.updateItem(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(
            @PathVariable Long id) {

        itemService.deleteItem(id);
    }
}
