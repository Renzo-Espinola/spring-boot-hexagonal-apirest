package com.renzo.springboot.webflux.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renzo.springboot.webflux.application.ports.in.CreateUserUseCase;
import com.renzo.springboot.webflux.application.ports.out.UserRepository;
import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Mono;

@Service
public class CreateUserService implements CreateUserUseCase{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Mono<User> execute(User user) {
		return userRepository.save(user);
	}

}
