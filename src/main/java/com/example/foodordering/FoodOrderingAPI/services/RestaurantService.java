package com.example.foodordering.FoodOrderingAPI.services;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateRestaurantRequest;
import com.example.foodordering.FoodOrderingAPI.dto.requestDto.UpdateRestaurantRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.RestaurantResponse;
import com.example.foodordering.FoodOrderingAPI.exception.BusinessException;
import com.example.foodordering.FoodOrderingAPI.exception.ResourceNotFoundException;
import com.example.foodordering.FoodOrderingAPI.mapper.RestaurantMapper;
import com.example.foodordering.FoodOrderingAPI.models.Restaurant;
import com.example.foodordering.FoodOrderingAPI.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RestaurantService {
   private final RestaurantRepository restaurantRepository;
   private final RestaurantMapper restaurantMapper;

   public RestaurantResponse addRestaurant(CreateRestaurantRequest request){
       if(restaurantRepository.existsByEmail(request.getEmail())){
           throw new BusinessException("Email Already Exists");
       }

       return restaurantMapper.toResponse( restaurantRepository.save(restaurantMapper.toEntity(request)));

   }

    public RestaurantResponse getRestaurantById(Long id){
        return restaurantMapper.toResponse( restaurantRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Restaurant not found with id: " + id)));
    }

    public RestaurantResponse updateRestaurantById(Long id, UpdateRestaurantRequest restaurantRequest){
       Restaurant restaurant= restaurantRepository.findById(id).
               orElseThrow(()-> new ResourceNotFoundException("Restaurant not found with id: " + id));

        restaurant.setName(restaurantRequest.getName());
        restaurant.setContactNo(restaurantRequest.getContactNo());
        restaurant.setEmail(restaurantRequest.getEmail());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        return restaurantMapper.toResponse(restaurant);
    }

    public void deleteRestaurantById(Long id){
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "User not found with id: " + id
            );
        }

        restaurantRepository.deleteById(id);
    }



}
