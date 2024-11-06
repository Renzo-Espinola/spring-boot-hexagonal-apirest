package com.renzo.springboot.webflux.application.ports.out;

import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
	Mono<User> save(User user);

	Mono<User> findById(String id);

	Flux<User> findAll();

	Mono<Void> deleteById(String id);
}
