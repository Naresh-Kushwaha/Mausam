package com.example.demo;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
          WeatherResponse response=restTemplate.getForObject(url, WeatherResponse.class);
        return searchWeather(response.getCity());


    }
    @GetMapping("/location")
    public String searchWeather(@RequestParam String city) throws IOException, InterruptedException {
        String url=String.format("https://api.openweathermap.org/data/2.5/weather?units=metric&q=%s&appid=4c3fb3dc336e3f3a2ccfc656bf857318",city);
        return restTemplate.getForObject(url, String.class);
//              String url=String.format("%s/current?place=%s&units=matric&lang=en&mode=json",weatherurl,city);
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("x-rapidapi-key", weatherkey)
//                .header("x-rapidapi-host", "weather-api167.p.rapidapi.com")
//                .header("Accept", "application/json")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        return  response.body();

    }

}
