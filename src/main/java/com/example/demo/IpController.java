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

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@RestController
@RequestMapping("/api")
public class IpController {

    @GetMapping("/mylocation")
    public String getUserIp(HttpServletRequest request) throws IOException, InterruptedException {
        String ipAddress = request.getHeader("X-Forwarded-For"); // Check for proxy header
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        String url=String.format("http://api.ipapi.com/%s?access_key=49a5c71195237332c37d87fc09b2b19f",ipAddress);
        HttpRequest requestData=HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("GET",HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String>response=HttpClient.newHttpClient().send(requestData, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response.body());
        String city= String.valueOf(jsonResponse.get("city"));
        return searchWeather(city);


    }
    @GetMapping("/location")
    public String searchWeather(@RequestParam String city) throws IOException, InterruptedException {
        String url2=String.format("https://api.openweathermap.org/data/2.5/weather?units=metric&q=%s&appid=4c3fb3dc336e3f3a2ccfc656bf857318","delhi");
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(url2))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper=new ObjectMapper();
        return response2.body();
    }
}
