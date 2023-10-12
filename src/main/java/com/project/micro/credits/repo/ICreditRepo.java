package com.project.micro.credits.repo;

import com.project.micro.credits.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ICreditRepo extends ReactiveMongoRepository<Credit,String> {

    Mono<Credit> findByIdCustomer(String idCustomer);
}
