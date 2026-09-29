package com.example.foodordering.FoodOrderingAPI.services;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateOrderRequest;
import com.example.foodordering.FoodOrderingAPI.dto.requestDto.OrderItemRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.OrderResponse;
import com.example.foodordering.FoodOrderingAPI.exception.BusinessException;
import com.example.foodordering.FoodOrderingAPI.exception.ResourceNotFoundException;
import com.example.foodordering.FoodOrderingAPI.mapper.OrderMapper;
import com.example.foodordering.FoodOrderingAPI.models.Address;
import com.example.foodordering.FoodOrderingAPI.models.Item;
import com.example.foodordering.FoodOrderingAPI.models.Order;
import com.example.foodordering.FoodOrderingAPI.models.OrderItem;
import com.example.foodordering.FoodOrderingAPI.models.Restaurant;
import com.example.foodordering.FoodOrderingAPI.models.User;
import com.example.foodordering.FoodOrderingAPI.repository.AddressRepository;
import com.example.foodordering.FoodOrderingAPI.repository.ItemRepository;
import com.example.foodordering.FoodOrderingAPI.repository.OrderRepository;
import com.example.foodordering.FoodOrderingAPI.repository.RestaurantRepository;
import com.example.foodordering.FoodOrderingAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;


    public OrderResponse createOrder(CreateOrderRequest request) {



        // 1. Find User
        User user = getCurrentUser();


        // 2. Find Restaurant
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Restaurant not found with id: " + request.getRestaurantId()
                        ));


        // 3. Find Address
        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Address not found with id: " + request.getAddressId()
                        ));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException(
                    "You can only use your own address"
            );
        }


        // 4. Create Order
        Order order = new Order();

        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setAddress(address);
        order.setDateTime(LocalDateTime.now());
        order.setOrderStatus("PLACED");


        // 5. Calculate total price
        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();


        // 6. Create OrderItems
        for (OrderItemRequest itemRequest : request.getItems()) {

            Item item = itemRepository.findById(itemRequest.getItemId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Item not found with id: " +
                                            itemRequest.getItemId()
                            ));


            // Check item belongs to selected restaurant
            if (!item.getRestaurant().getId().equals(restaurant.getId())) {
                throw new BusinessException(
                        "Item does not belong to this restaurant"
                );
            }


            // Calculate subtotal
            BigDecimal subtotal = item.getPrice()
                    .multiply(
                            BigDecimal.valueOf(itemRequest.getQuantity())
                    );


            // Create OrderItem
            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setQuantity(itemRequest.getQuantity());

            // Store price at the time of ordering
            orderItem.setPrice(item.getPrice());

            orderItems.add(orderItem);

            totalPrice = totalPrice.add(subtotal);
        }


        // 7. Set OrderItems and total price
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);


        // 8. Save Order
        Order savedOrder = orderRepository.save(order);


        // 9. Return response
        return orderMapper.toResponse(savedOrder);
    }

    public OrderResponse getOrderById(Long orderId){
        return orderMapper.toResponse(orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("Payment not found with id: " + orderId)));
    }




    public List<OrderResponse> getOrdersByUser(Long userId) {

        User currentUser = getCurrentUser();

        if (!currentUser.getId().equals(userId)) {
            throw new AccessDeniedException(
                    "You can only access your own orders"
            );
        }

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(
                    "User not found with id: " + userId
            );
        }

        List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream()
                .map(orderMapper::toResponse)
                .toList();
    }


    public OrderResponse updateOrderStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + orderId
                        ));

        order.setOrderStatus(status);

        Order updatedOrder = orderRepository.save(order);

        return orderMapper.toResponse(updatedOrder);
    }

    public OrderResponse cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + orderId
                        ));

        if (!order.getOrderStatus().equals("PLACED")) {
            throw new BusinessException(
                    "Order cannot be cancelled in current status: "
                            + order.getOrderStatus()
            );
        }

        order.setOrderStatus("CANCELLED");

        Order cancelledOrder = orderRepository.save(order);

        return orderMapper.toResponse(cancelledOrder);
    }


    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }


}

