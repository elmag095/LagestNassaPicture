package com.lagestnassapicture.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nasa-client", url = "https://api.nasa.gov")
public interface NasaFein {
    @GetMapping("/mars-photos/api/v1/rovers/curiosity/photos")
    JsonNode getPictures(@RequestParam int sol, @RequestParam String camera, @RequestParam(value = "api_key", required = false, defaultValue = "DEMO_KEY") String apiKey);
}
