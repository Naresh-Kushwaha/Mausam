package com.example.demo;


import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@RestController
@RequestMapping("/api")
public class IpController {
    private final RestTemplate restTemplate;

    public IpController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping("/mylocation")
    public String getUserIp(HttpServletRequest request) throws IOException, InterruptedException {
        String ipAddress = request.getHeader("X-Forwarded-For"); // Check for proxy header
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        String url=String.format("http://api.ipapi.com/%s?access_key=49a5c71195237332c37d87fc09b2b19f",ipAddress);
          IpResponse response=restTemplate.getForObject(url, IpResponse.class);
        return searchWeather(response.getCity());

    }
    @GetMapping("/location")
    public String searchWeather(@RequestParam String city) throws IOException, InterruptedException {
        String url=String.format("https://api.openweathermap.org/data/2.5/weather?units=metric&q=%s&appid=4c3fb3dc336e3f3a2ccfc656bf857318",city);
        return restTemplate.getForObject(url, String.class);
    }
}
