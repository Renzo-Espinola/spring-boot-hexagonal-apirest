package com.renzo.springboot.webflux.application.ports.in;

import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Mono;

public interface CreateUserUseCase {
	
	Mono<User> execute(User user);

}
