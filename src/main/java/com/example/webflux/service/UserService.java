package com.example.webflux.service;

import com.example.webflux.entity.User;
import com.example.webflux.exceptions.ObjectNotFoundException;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.model.request.UserRequest;
import com.example.webflux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Mono<User> save(final UserRequest request) {
        return userRepository.save(userMapper.toEntity(request));
    }

    public Mono<User> findById(final String id) {

        return userRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new ObjectNotFoundException(String.format("Object not found. id: %s, type: %s", id, "user")))
                );

    }

    public Flux<User> findAll(){
        return userRepository.findAll();
    }

    public Mono<User> update(final String id, final UserRequest request){
        return findById(id).map(entity -> userMapper.toEntity(request, entity))
                .flatMap(userRepository::save);

    }

}
