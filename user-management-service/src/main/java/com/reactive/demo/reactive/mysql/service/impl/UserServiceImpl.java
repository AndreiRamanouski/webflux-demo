package com.reactive.demo.reactive.mysql.service.impl;


import com.reactive.demo.UserAction;
import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.mapper.UserMapper;
import com.reactive.demo.reactive.mysql.model.UpdateEmailRequest;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import com.reactive.demo.reactive.mysql.service.UserService;
import com.reactive.demo.reactive.mysql.service.exception.UserServiceException;
import com.reactive.demo.reactive.mysql.util.RabbitHistoryUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RabbitHistoryUtil rabbitHistoryUtil;

    /**
     * Saves a new user. Produces REGISTRATION event.
     *
     * @param userDto contains userinfo
     * @return Mono<UserDto> that represents the saved user
     * @throws UserServiceException if the user email is already taken
     */
    @Override
    @SneakyThrows
    public Mono<UserDto> saveUser(Mono<UserDto> userDto) {
        log.info("saveUser");
        if (Objects.nonNull(findByEmail(userDto.toFuture().get().email()))) {
            return Mono.error(new UserServiceException("Email is already taken"));
        } else {
            UserDto dto = userDto.map(UserMapper::mapDtoToEntity).flatMap(userRepository::save)
                    .map(UserMapper::mapEntityToDto).toFuture().get();
            rabbitHistoryUtil.produceEvent(dto.id(), UserAction.REGISTRATION);
            return Mono.just(dto);
        }
    }

    /**
     * Updates the userinfo. Produces UPDATE_INFO event.
     *
     * @param userDto contains userinfo to update the user
     * @param id userId
     * @return Mono<UserDto> that represents the updated
     * @throws UserServiceException if the user email is already taken
     */
    @Override
    @SneakyThrows
    public Mono<UserDto> updateUser(Mono<UserDto> userDto, Long id) {
        log.info("updateUser");
        log.info("id {}", id);
        String email = userDto.toFuture().get().email();
        User userWithEmail = findByEmail(email);
        if (Objects.nonNull(userWithEmail)) {
            return userWithEmail.getId().equals(id) ? Mono.error(new UserServiceException("The same email")) :
                    Mono.error(new UserServiceException("Email is already taken"));
        } else {
            rabbitHistoryUtil.produceEvent(id, UserAction.UPDATE_INFO);
            return userRepository.findById(id)
                    .flatMap(user -> userDto.map(UserMapper::mapDtoToEntity))
                    .doOnNext(user -> user.setId(id)).flatMap(userRepository::save).map(UserMapper::mapEntityToDto);
        }
    }

    /**
     * Deletes the user. Produces DELETE_USER event.
     *
     * @param id userId
     */
    @Override
    public Mono<Void> deleteUser(Long id) {
        log.info("deleteUser");
        rabbitHistoryUtil.produceEvent(id, UserAction.DELETE_USER);
        return userRepository.deleteById(id);
    }

    /**
     * Finds the user.
     *
     * @param id userId
     * @return Mono<UserDto> found user
     */
    @Override
    public Mono<UserDto> getByUserId(Long id) {
        log.info("getByUserId");
        return userRepository.findById(id).map(UserMapper::mapEntityToDto);
    }


    /**
     * Updates the email. Produces EMAIL_UPDATE.
     *
     * @param updateEmailRequest contains userId and email to use
     * @throws UserServiceException if the user email is already taken
     */
    @Override
    public Mono<Void> updateEmail(UpdateEmailRequest updateEmailRequest) {
        log.info("updateEmail");
        User userWithEmail = findByEmail(updateEmailRequest.getEmail());
        if (Objects.nonNull(userWithEmail)) {
            return userWithEmail.getId().equals(updateEmailRequest.getId()) ? Mono.error(
                    new UserServiceException("The same email")) :
                    Mono.error(new UserServiceException("Email is already taken"));
        } else {
            rabbitHistoryUtil.produceEvent(updateEmailRequest.getId(), UserAction.EMAIL_UPDATE);
            return userRepository.findById(updateEmailRequest.getId())
                    .flatMap(user -> userRepository.updateEmail(updateEmailRequest.getEmail(), user.getId()));
        }
    }

    /**
     * Finds the user by email.
     *
     * @param email email to use
     * @return User found user or null
     */
    @SneakyThrows
    private User findByEmail(String email) {
        log.info("findByEmail");
        // do not really know how to do the email checking without blocking. just block() does not work with the current version of netty.
        return userRepository.findByEmail(email).toFuture().get();
    }
}
