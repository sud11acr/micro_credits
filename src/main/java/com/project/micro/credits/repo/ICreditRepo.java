package com.project.micro.credits.repo;

import com.project.micro.credits.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICreditRepo extends ReactiveMongoRepository<Credit,String> {
}
