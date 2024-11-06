package com.renzo.springboot.webflux.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;

import com.renzo.springboot.webflux.application.ports.in.FindAllUserUseCase;
import com.renzo.springboot.webflux.application.ports.out.UserRepository;
import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Flux;

public class FindAllUsersService implements FindAllUserUseCase{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Flux<User> execute() {
		return userRepository.findAll();
	}
}
