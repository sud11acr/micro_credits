package com.project.micro.credits.service;

import com.project.micro.credits.integration.CreditRequest;
import com.project.micro.credits.integration.CreditResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {

    Mono<CreditResponse> save(Mono<CreditRequest> credit);
    Mono<CreditResponse> update(String id,Mono<CreditRequest> credit);
    Flux<CreditResponse> findAll();
    Mono<CreditResponse>findByid(String id);
    Mono<Void> delete(String id);
}
