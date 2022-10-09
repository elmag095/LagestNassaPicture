package com.lagestnassapicture.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

@RestController()
@RequestMapping("/nasa")
@RequiredArgsConstructor
public class Nassa {

    private final NasaFein nasaFein;

    @GetMapping("/mars")
    public RedirectView getLagestPiccher() {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode pictures = nasaFein.getPictures(100, "NAVCAM", "DEMO_KEY");

        String lagestUrl = "";
        long lugestContentLength = -1L;
        for (JsonNode photo : pictures.get("photos")) {
            String img_url = photo.get("img_src").asText();
            long contentLength = restTemplate.headForHeaders(img_url).getContentLength();

            if (contentLength > lugestContentLength) {
               lugestContentLength = contentLength;
               lagestUrl = img_url;
            }
        }

        return new RedirectView(lagestUrl);
    }
}
