package com.example.foodordering.FoodOrderingAPI.services;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.AddressRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.AddressResponse;
import com.example.foodordering.FoodOrderingAPI.exception.ResourceNotFoundException;
import com.example.foodordering.FoodOrderingAPI.mapper.AddressMapper;
import com.example.foodordering.FoodOrderingAPI.models.Address;
import com.example.foodordering.FoodOrderingAPI.models.User;
import com.example.foodordering.FoodOrderingAPI.repository.AddressRepository;
import com.example.foodordering.FoodOrderingAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    public AddressResponse createAddress(
            Long userId,
            AddressRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        ));

        Address address = addressMapper.toEntity(request);

        address.setUser(user);

        Address savedAddress = addressRepository.save(address);

        return addressMapper.toResponse(savedAddress);
    }

    public AddressResponse getAddressById(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Address not found with id: " + id
                        ));



        return addressMapper.toResponse(address);
    }

    public AddressResponse updateAddress(
            Long id,
            AddressRequest request) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Address not found with id: " + id
                        ));

        address.setPinCode(request.getPinCode());
        address.setCity(request.getCity());
        address.setArea(request.getArea());
        address.setLandmark(request.getLandmark());

        Address updatedAddress = addressRepository.save(address);

        return addressMapper.toResponse(updatedAddress);
    }

    public void deleteAddress(Long id) {

        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Address not found with id: " + id
            );
        }

        addressRepository.deleteById(id);
    }
}