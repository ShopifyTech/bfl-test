package com.bfl.service;

import com.bfl.connector.BflConnector;
import com.bfl.mapper.CustomerSearchMapper;
import com.bfl.util.AESEncryption;
import com.bfl.util.CustomerSearchRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired
    ObjectMapper objectMapper;
    public String customerSearch(Long cardNumber) {
        CustomerSearchRequest customerRequest = customerSearchMapper.buildCustomer(cardNumber);
        String custmerJsonStr = convertJson(customerRequest);
        log.info("customerRequest json {},{}",customerRequest,custmerJsonStr);
        String enctptString = aesEncryption.encrypt(custmerJsonStr);
        log.info("enctptString {}",enctptString);
        String str=enctptString+key;
        String sealValue = convertMD5Hash(str);
        log.info("sealValue {}",sealValue);
        String response = bflConnector.bflCustomerSearch(sealValue, enctptString);
        //String result = response.replaceAll("^\"|\"$", "");
       // log.info("result {}",result);
       aesEncryption.decrypt(response);
        return response;
    }

    public String convertJson(CustomerSearchRequest customerRequest)
    {
        try {
            return objectMapper.writeValueAsString(customerRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
