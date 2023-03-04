package com.reactive.demo.reactive.mysql.mapper;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserDto mapEntityToDto(User user) {
        return new UserDto(user.getId(), user.getUserId(),
                user.getSubscriptionId(), user.getStatus());
    }

    public User mapDtoToEntity(UserDto userDto) {
        return new User(userDto.id(), userDto.userId(),
                userDto.subscriptionId(), userDto.status());
    }
}
