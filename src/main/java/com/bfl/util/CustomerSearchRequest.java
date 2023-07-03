package com.bfl.util;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerSearchRequest {
    private String txnType;
    private String rrn;
    private Long requestDateTime;
    private Integer dealerCode;
    private Long dealerValidationKey;
    private Long cardNumber;
    private Integer acqChannel;
}
