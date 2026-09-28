package com.example.foodordering.FoodOrderingAPI.services;


import com.example.foodordering.FoodOrderingAPI.dto.requestDto.RegisterUserRequest;
import com.example.foodordering.FoodOrderingAPI.dto.requestDto.UpdateUserRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.UserResponse;
import com.example.foodordering.FoodOrderingAPI.exception.BusinessException;
import com.example.foodordering.FoodOrderingAPI.exception.ResourceNotFoundException;
import com.example.foodordering.FoodOrderingAPI.mapper.UserMapper;
import com.example.foodordering.FoodOrderingAPI.models.User;
import com.example.foodordering.FoodOrderingAPI.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse creteUser(RegisterUserRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
           throw new BusinessException("User Already Exists");
        }
        return userMapper.toResponse(userRepository.save(userMapper.toEntity(request)));
    }

    public UserResponse getUserById(Long id){

        return userMapper.toResponse( userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id)));

    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id)
                );

        user.setName(request.getName());
        user.setContactNo(request.getContactNo());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "User not found with id: " + id
            );
        }

        userRepository.deleteById(id);
    }


}
