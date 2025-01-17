package com.learning.journal.services;

import com.learning.journal.util.CityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class CityService {

    private static  final String externalURL  = "https://staging-tiffinstash.staqo.com";

    public CityResponse getCity() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(externalURL + "/api/v1/master/cities?page&limit&search_q&paginate", CityResponse.class);
        }catch (Exception e) {
            log.error(e.getMessage());
            return  null;
        }
    }
}
