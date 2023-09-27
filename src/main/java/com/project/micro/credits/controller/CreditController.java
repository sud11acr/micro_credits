package com.project.micro.credits.controller;

import com.project.micro.credits.integration.CreditRequest;
import com.project.micro.credits.integration.CreditResponse;
import com.project.micro.credits.service.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    private ICreditService service;

    @GetMapping("/findAll")
    public Mono<ResponseEntity<Flux<CreditResponse>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findAll()));
    }

    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<CreditResponse>> findById(@PathVariable String id) {
        return service.findByid(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<CreditResponse>>save(@Validated @RequestBody  Mono<CreditRequest> creditRequest){
        return service.save(creditRequest)
                .map(p -> ResponseEntity.created(URI.create("/create".concat("/").concat(p.getIdCredit())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<CreditResponse>>update(@PathVariable String id,@RequestBody Mono<CreditRequest> creditRequest ){
        return service.update(id,creditRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }
}
