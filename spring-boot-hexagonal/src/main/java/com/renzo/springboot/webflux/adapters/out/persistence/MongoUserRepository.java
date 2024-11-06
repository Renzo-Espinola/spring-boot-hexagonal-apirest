package com.renzo.springboot.webflux.adapters.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.renzo.springboot.webflux.domain.User;

public interface MongoUserRepository extends ReactiveMongoRepository<User,String> {
}
