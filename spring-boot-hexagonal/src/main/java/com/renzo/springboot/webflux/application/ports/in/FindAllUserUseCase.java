package com.renzo.springboot.webflux.application.ports.in;

import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Flux;

public interface FindAllUserUseCase {
	Flux<User> execute();
}
