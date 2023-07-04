package com.bfl.mapper;

import com.bfl.util.CustomerSearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class CustomerSearchMapper {



    public  CustomerSearchRequest buildCustomer(Long cardNumber) {
        CustomerSearchRequest obj = CustomerSearchRequest.builder()
                .txnType("BILSRCH")
                .rrn(OffsetDateTime.now().toString())
                .requestDateTime(03072023142121l)
                .dealerCode(123888)
                .dealerValidationKey(4462137033265896l)
                .cardNumber(cardNumber)   //This one we will get from post request
                .acqChannel(22)
                .build();
        return obj;
    }
}
