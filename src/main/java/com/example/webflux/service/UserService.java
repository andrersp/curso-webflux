package com.example.webflux.service;

import com.example.webflux.entity.User;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.model.request.UserRequest;
import com.example.webflux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Mono<User> save(final UserRequest request) {

        return userRepository.save(userMapper.toEntity(request));


    }

}
