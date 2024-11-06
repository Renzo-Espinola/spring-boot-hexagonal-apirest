package com.renzo.springboot.webflux.adapters.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.renzo.springboot.webflux.application.ports.out.UserRepository;
import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private MongoUserRepository userRepository;
	
	@Override
	public Mono<User> save(User user) {	
		return userRepository.save(user);
	}

	@Override
	public Mono<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return userRepository.deleteById(id);
	}

}
