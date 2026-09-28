package com.example.foodordering.FoodOrderingAPI.controller;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.AddressRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.AddressResponse;
import com.example.foodordering.FoodOrderingAPI.services.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/user/{userId}")
    public AddressResponse createAddress(
            @PathVariable Long userId,
            @Valid @RequestBody AddressRequest request) {

        return addressService.createAddress(userId, request);
    }

    @GetMapping("/{id}")
    public AddressResponse getAddressById(
            @PathVariable Long id) {

        return addressService.getAddressById(id);
    }

    @PutMapping("/{id}")
    public AddressResponse updateAddress(
            @PathVariable Long id,
            @Valid @RequestBody AddressRequest request) {

        return addressService.updateAddress(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable Long id) {

        addressService.deleteAddress(id);

        return ResponseEntity.noContent().build();
    }
}
