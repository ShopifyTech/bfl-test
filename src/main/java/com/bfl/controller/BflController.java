package com.bfl.controller;

import com.bfl.service.BflService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequestMapping("/bfl")
public class BflController {

    @Autowired
    private BflService bflService;
    @GetMapping("/customer/{cardNumber}/search")
    public ResponseEntity<String> customerSearch(@PathVariable Long cardNumber) {
        log.info("cardNumber {}", cardNumber);
        bflService.customerSearch(cardNumber);
        return ResponseEntity.status(OK).body("Success");
    }
}
