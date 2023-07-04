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
        headers.set("SUPPLIERID", "123888");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set the request body
        String requestBody = "\"" + body + "\"";
        //String requestBody = "\"J5ytcjQ2lX/1amH/0F5qf5Kdp0VPFFOqcY3Hq4XzDi8XBV2F6oppCmsXU4tDtjdLDe0+MNaxYSfNZYUDSl9sY29SCH28P7Yjqzp0OoIGPC+BSdZrNbiUoLCqjEWl1LZ5nAOyHjmUS57eUhP8lAYr7e2URdDCA3CFJIQYxtieiHb9L5ivOaaug6jehA7Em+J2QxRyF1n3Z+hJKWGo3LIxLm6fiZvioKEOBE7BpRB3w520Qi5Z9vvSh0p04N2JIcez\"";

        // Create the request entity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

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

    public void test()
    {
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("sealValue", "aaddb485dd0705b4ff9d7be6746138d0");
        headers.set("SUPPLIERID", "123888");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", "ASP.NET_SessionId=imqpfa3agn0hxojtkggfwytg");

        // Set the request body
       // String requestBody = "\"J5ytcjQ2lX/1amH/0F5qf5Kdp0VPFFOqcY3Hq4XzDi8XBV2F6oppCmsXU4tDtjdLDe0+MNaxYSfNZYUDSl9sY29SCH28P7Yjqzp0OoIGPC+BSdZrNbiUoLCqjEWl1LZ5nAOyHjmUS57eUhP8lAYr7e2URdDCA3CFJIQYxtieiHb9L5ivOaaug6jehA7Em+J2QxRyF1n3Z+hJKWGo3LIxLm6fiZvioKEOBE7BpRB3w520Qi5Z9vvSh0p04N2JIcez\"";
        String  requestBody="\"STNLzUwl00lp+Y4psc7UyYcXf+mhzGJPF03yPgG3KwHh0gXkFqlcH//gmw3W1DzBLxPNTeKBOtHJLzrQ1A59OOvnGPtF42FAfP20uMbenxGnDXEvKNJUvKO6MoEavji14umjs5tU1QRvPWVgBH8VhlpuXXIHFq+HmJieTfbUUD2G9cSgSZogZ/ewiC86T6H/d9CK0Tc4q3F3kbpcsIrt32QzUDv838Mu0LuevuqWxZYcjH1KoArNsxsztbjXYqxry0S3t0li/Pw6DbwnFw1hGA==\"";
        log.info("requestBody {}",requestBody);
        // Create the request entity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

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
        System.out.println("dec"+responseBody);
    }
}









