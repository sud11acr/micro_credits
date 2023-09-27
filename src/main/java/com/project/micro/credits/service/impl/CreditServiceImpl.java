package com.project.micro.credits.service.impl;

import com.project.micro.credits.integration.CreditRequest;
import com.project.micro.credits.integration.CreditResponse;
import com.project.micro.credits.mapper.CreditMapper;
import com.project.micro.credits.model.Credit;
import com.project.micro.credits.repo.ICreditRepo;
import com.project.micro.credits.service.ICreditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class CreditServiceImpl implements ICreditService {
    @Autowired
    private ICreditRepo repo;
    @Override
    public Mono<CreditResponse> save(Mono<CreditRequest> creditRequest) {
        return creditRequest.map(p-> CreditMapper.toCreditModelReq(p))
                .flatMap(
                        p->{
                            p.setRegistrationDate(new Date());
                            p.setModificationDate(new Date());
                            p.setStatus(true);
                            return repo.save(p);
                        })
                .map(p->CreditMapper.toCreditModelRes(p));
    }

    @Override
    public Mono<CreditResponse> update(String id, Mono<CreditRequest> creditRequest) {
        Mono<Credit> monoBody = creditRequest.map(p-> CreditMapper.toCreditModelReq(p));
        Mono<Credit> monoBD = repo.findById(id);

        return monoBD.zipWith(monoBody,(bd,pl)->{
                    BeanUtils.copyProperties(pl,bd);
                    bd.setIdCredit(id);
                    return bd;
                }).flatMap(p->repo.save(p))
                .map(c->CreditMapper.toCreditModelRes(c));
    }

    @Override
    public Flux<CreditResponse> findAll() {
        return repo.findAll().map(p->CreditMapper.toCreditModelRes(p));
    }

    @Override
    public Mono<CreditResponse> findByid(String id) {
        return repo.findById(id).map(p->CreditMapper.toCreditModelRes(p));
    }

    @Override
    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }
}
