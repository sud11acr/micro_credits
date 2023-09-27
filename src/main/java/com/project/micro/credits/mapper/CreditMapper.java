package com.project.micro.credits.mapper;

import com.project.micro.credits.dto.CreditDto;
import com.project.micro.credits.integration.CreditRequest;
import com.project.micro.credits.integration.CreditResponse;
import com.project.micro.credits.model.Credit;
import org.springframework.beans.BeanUtils;

import java.net.CacheRequest;

public class CreditMapper {

    public CreditMapper(){

    }

    public static Credit toCreditModelReq(CreditRequest creditRequest){

        Credit credit=new Credit();
        BeanUtils.copyProperties(creditRequest,credit);
        return credit;
    }

    public static CreditResponse toCreditModelRes(Credit credit){

        CreditResponse creditResponse=new CreditResponse();
        BeanUtils.copyProperties(credit,creditResponse);
        return creditResponse;
    }
}
