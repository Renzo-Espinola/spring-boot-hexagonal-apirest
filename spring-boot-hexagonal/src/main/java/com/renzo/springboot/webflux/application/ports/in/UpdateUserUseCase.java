package com.renzo.springboot.webflux.application.ports.in;

import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Mono;

public interface UpdateUserUseCase {
	Mono<User> execute(User user, String id);
}
