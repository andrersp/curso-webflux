package com.example.webflux.controller.impl;

import com.example.webflux.controller.UserController;
import com.example.webflux.entity.User;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.model.request.UserRequest;
import com.example.webflux.model.response.UserResponse;
import com.example.webflux.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl  implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    @Override
    public ResponseEntity<Mono<Void>> save(UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request).then());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(String id) {
        return ResponseEntity.ok().body(userService.findById(id).map(userMapper::toDTO));
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        Flux<UserResponse> users = userService.findAll().map(userMapper::toDTO);

        return ResponseEntity.ok().body(
                users
        );

    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {
        return ResponseEntity.ok().body(userService.update(id, request).map(userMapper::toDTO));
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(String id) {
        return null;
    }
}
