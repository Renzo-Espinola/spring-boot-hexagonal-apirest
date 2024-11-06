package com.renzo.springboot.webflux.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;

import com.renzo.springboot.webflux.application.ports.in.UpdateUserUseCase;
import com.renzo.springboot.webflux.application.ports.out.UserRepository;
import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Mono;

public class UpdateUserService implements UpdateUserUseCase{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Mono<User> execute(User user, String id) {
		return userRepository.findById(id).flatMap(u -> {
			u.setEmail(user.getEmail());
			u.setNombre(user.getNombre());
			return Mono.just(u);
		}).defaultIfEmpty(new User()).flatMap(us -> {
			if (us.getId() == null) {
				return Mono.error(new InterruptedException("No existe el usuario con id:" + id));
			}
			return Mono.just(us);
		});
	}

}
