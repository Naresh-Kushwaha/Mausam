package com.example.demo;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class WeatherController {
    private final RestTemplate restTemplate;

    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${ipapi.api}")
    private String ipapiurl;
    @Value("${ipapi.key}")
    private String ipapikey;
    @Value("${weather.api}")
    private String weatherurl;
    @Value("${weather.key}")
    private String weatherkey;
    @GetMapping("/mylocation")
    public String getUserIp(HttpServletRequest request) throws IOException, InterruptedException {
        String ipAddress = request.getHeader("X-Forwarded-For"); // Check for proxy header
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        String url=String.format("%s/%s?access_key=%s",ipapiurl,ipAddress,ipapikey);
          IpapiResponse response=restTemplate.getForObject(url, IpapiResponse.class);
        assert response != null;
        String city=response.getCity();
        for(int i=0;i<response.getCity().length();i++){
            if(response.getCity().charAt(i)==','){
                city=response.getCity().substring(0,i);
                break;
            }

        }
        return searchWeather(city);


    }
    @GetMapping("/location")
    public String searchWeather(@RequestParam String city) throws IOException, InterruptedException {
        String url=String.format("https://api.openweathermap.org/data/2.5/weather?units=metric&q=%s&appid=4c3fb3dc336e3f3a2ccfc656bf857318",city);
        return restTemplate.getForObject(url, String.class);

    }

}
