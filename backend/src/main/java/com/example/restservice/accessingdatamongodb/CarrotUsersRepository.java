package com.example.restservice.accessingdatamongodb;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarrotUsersRepository extends MongoRepository<CarrotUser, String> {

  CarrotUser findByEmail(String email);

}