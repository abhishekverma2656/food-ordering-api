package com.example.foodordering.FoodOrderingAPI.services;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateItemRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.ItemResponse;
import com.example.foodordering.FoodOrderingAPI.exception.ResourceNotFoundException;
import com.example.foodordering.FoodOrderingAPI.mapper.ItemMapper;
import com.example.foodordering.FoodOrderingAPI.models.Item;
import com.example.foodordering.FoodOrderingAPI.models.Restaurant;
import com.example.foodordering.FoodOrderingAPI.repository.ItemRepository;
import com.example.foodordering.FoodOrderingAPI.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final RestaurantRepository restaurantRepository;
    private final ItemMapper itemMapper;

    public ItemResponse createItem(
            Long restaurantId,
            CreateItemRequest request) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Restaurant not found with id: " + restaurantId
                        ));

        Item item = itemMapper.toEntity(request);

        item.setRestaurant(restaurant);

        Item savedItem = itemRepository.save(item);

        return itemMapper.toResponse(savedItem);
    }

    public ItemResponse getItemById(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Item not found with id: " + id
                        ));

        return itemMapper.toResponse(item);
    }

    public ItemResponse updateItem(
            Long id,
            CreateItemRequest request) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Item not found with id: " + id
                        ));

        item.setName(request.getName());
        item.setType(request.getType());
        item.setPrice(request.getPrice());
        item.setDescription(request.getDescription());

        Item updatedItem = itemRepository.save(item);

        return itemMapper.toResponse(updatedItem);
    }

    public void deleteItem(Long id) {

        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Item not found with id: " + id
            );
        }

        itemRepository.deleteById(id);
    }
}