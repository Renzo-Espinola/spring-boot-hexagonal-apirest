package com.renzo.springboot.webflux.application.ports.in;

import reactor.core.publisher.Mono;

public interface DeleteUserUseCase {
	
	Mono<Void> execute(String id);

}
