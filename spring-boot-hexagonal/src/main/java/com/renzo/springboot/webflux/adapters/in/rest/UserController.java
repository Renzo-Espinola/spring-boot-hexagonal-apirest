package com.renzo.springboot.webflux.adapters.in.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.renzo.springboot.webflux.application.ports.in.CreateUserUseCase;
import com.renzo.springboot.webflux.application.ports.in.DeleteUserUseCase;
import com.renzo.springboot.webflux.application.ports.in.FindAllUserUseCase;
import com.renzo.springboot.webflux.application.ports.in.FindUserUseCase;
import com.renzo.springboot.webflux.application.ports.in.UpdateUserUseCase;
import com.renzo.springboot.webflux.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private CreateUserUseCase createUserUseCase;
	@Autowired
	private DeleteUserUseCase deleteUserUseCase;
	@Autowired
	private FindAllUserUseCase findAllUserUseCase;
	@Autowired
	private FindUserUseCase findUserUseCase;
	@Autowired
	private UpdateUserUseCase updateUserUseCase;
	
	@PostMapping
	public Mono<ResponseEntity<User>> createUser(@RequestBody User user) {
		return createUserUseCase.execute(user).map(us -> {
			return ResponseEntity.created((URI.create("/api/user/".concat(us.getId()))))
					.contentType(MediaType.APPLICATION_JSON).body(us);
		});
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<User>> getUserById(@PathVariable String id){
		return findUserUseCase.execute(id).map(usr -> {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(usr);
		}).onErrorResume(err-> Mono.just(ResponseEntity.noContent().build()));
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String id){
		return deleteUserUseCase.execute(id)
				.then(Mono.just((new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public Mono<ResponseEntity<Flux<User>>> findAllUser(){
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(findAllUserUseCase.execute()));
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<User>> updateUser(@PathVariable String id, @RequestBody User user){
		return updateUserUseCase.execute(user, id).map(usr -> {
			return  ResponseEntity.created((URI.create("/api/user/".concat(usr.getId()))))
					.contentType(MediaType.APPLICATION_JSON).body(usr);
		}).onErrorResume(err-> Mono.just(ResponseEntity.noContent().build()));
	}

}
