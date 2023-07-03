package com.bfl.mapper;

import com.bfl.util.CustomerSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerSearchMapper {

    public  CustomerSearchRequest buildCustomer(Long cardNumber) {
        CustomerSearchRequest obj = CustomerSearchRequest.builder()
                .txnType("BILSRCH")
                .rrn("BILSdf4545001")
                .requestDateTime(20052021212121l)
                .dealerCode(687763)
                .dealerValidationKey(4499162128466537l)
                .cardNumber(cardNumber)   //This one we will get from post request
                .acqChannel(22)
                .build();
        return obj;
    }
}
