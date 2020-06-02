package com.capstone.project.controller;

import com.capstone.project.model.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api_games" )
public class GameApi {
//    @Value("${api.key}")
//    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/gameInfo")
    public List<Object> getGameInfo(){
        String url = "https://opentdb.com/api.php?amount=10";
        Object[] objects = restTemplate.getForObject(url, Object[].class);
        assert objects != null;
        return Arrays.asList(objects);
    }
}
