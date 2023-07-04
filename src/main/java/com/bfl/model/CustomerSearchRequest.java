package com.bfl.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class CustomerSearchRequest {
    private String txnType;
    private String rrn;
    private Integer requestDateTime;
    private Integer dealerCode;
    private Integer dealerValidationKey;
    private Integer cardNumber;
    private Integer acqChannel;
}
