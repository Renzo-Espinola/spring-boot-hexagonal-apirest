package com.renzo.springboot.webflux.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renzo.springboot.webflux.application.ports.in.FindUserUseCase;
import com.renzo.springboot.webflux.application.ports.out.UserRepository;
import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Mono;
@Service
public class FindUserService implements FindUserUseCase{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Mono<User> execute(String id) {	
		return userRepository.findById(id)
				.defaultIfEmpty(new User())
				.flatMap(usr -> {
					if(usr.getId()==null) {
						return Mono.error(new InterruptedException("No se encontro el id: "+id));
					}
					return Mono.just(usr);
				});
		
	}

}
