package com.renzo.springboot.webflux.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;

import com.renzo.springboot.webflux.application.ports.in.DeleteUserUseCase;
import com.renzo.springboot.webflux.application.ports.out.UserRepository;
import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Mono;

public class DeleteUserService implements DeleteUserUseCase {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Mono<Void> execute(String id) {
		return userRepository.findById(id).defaultIfEmpty(new User()).flatMap(usr -> {
			if (usr.getId() == null) {
				return Mono.error(new InterruptedException("No se encontro id:" + id));
			}
			return Mono.just(usr);
		}).flatMap(us -> {
			return userRepository.deleteById(us.getId());
		});
	}
}
