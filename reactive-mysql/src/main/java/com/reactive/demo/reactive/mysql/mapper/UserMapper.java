package com.reactive.demo.reactive.mysql.mapper;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.model.UserRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserDto mapEntityToDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getStatus());
    }

    public User mapDtoToEntity(UserDto userDto) {
        return new User(userDto.id(), userDto.firstName(),
                userDto.lastName(), userDto.email(), userDto.status());
    }

    public UserDto mapRequestToDto(UserRequest userRequest) {
        return new UserDto(null, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(),
                userRequest.getStatus());
    }
}
