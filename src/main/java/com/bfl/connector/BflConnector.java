package com.bfl.connector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
@Slf4j
public class BflConnector {

    public String bflCustomerSearch(String sealValue, String body) {
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("sealValue", sealValue);
        headers.set("SUPPLIERID", sealValue);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set the request body
        //String requestBody = "\"J5ytcjQ2lX/1amH/0F5qf5Kdp0VPFFOqcY3Hq4XzDi8XBV2F6oppCmsXU4tDtjdLDe0+MNaxYSfNZYUDSl9sY29SCH28P7Yjqzp0OoIGPC+BSdZrNbiUoLCqjEWl1LZ5nAOyHjmUS57eUhP8lAYr7e2URdDCA3CFJIQYxtieiHb9L5ivOaaug6jehA7Em+J2QxRyF1n3Z+hJKWGo3LIxLm6fiZvioKEOBE7BpRB3w520Qi5Z9vvSh0p04N2JIcez\"";

        // Create the request entity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Send the HTTP POST request
        ResponseEntity<String> response = restTemplate.exchange(
                "https://bfluat.in.worldline-solutions.com/worldlineinterfaceexperia/WorldlineInterfaceExperia.svc/BILINTRequest",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Get the response body
        String responseBody = response.getBody();

        // Print the response
        log.info("responseBody {}", responseBody);
        return responseBody;
    }

}
