package com.bfl.service;

import com.bfl.connector.BflConnector;
import com.bfl.mapper.CustomerSearchMapper;
import com.bfl.util.AESEncryption;
import com.bfl.util.CustomerSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.bfl.util.CommonUtility.convertMD5Hash;

@Slf4j
@Service
public class BflService {

    @Autowired
    private AESEncryption aesEncryption;
    @Value("${bfl.key}")
    private String key;
    @Autowired
    private CustomerSearchMapper customerSearchMapper;

    @Autowired
    private BflConnector bflConnector;
    public void customerSearch(Long cardNumber) {
        CustomerSearchRequest customerRequest = customerSearchMapper.buildCustomer(cardNumber);
        log.info("customerRequest {}",customerRequest);
        String enctptString = aesEncryption.encrypt(customerRequest.toString());
        log.info("enctptString {}",enctptString);
        String str=enctptString+key;
        String sealValue = convertMD5Hash(str);
        log.info("sealValue {}",sealValue);
        bflConnector.bflCustomerSearch(sealValue,enctptString);
    }
}
