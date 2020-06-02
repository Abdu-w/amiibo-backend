package com.capstone.project.controller;

import com.capstone.project.model.Games;
import com.capstone.project.repository.GameRepository;
import com.capstone.project.Services.ResourceException;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;


@RestController
@RequestMapping("/api_v1/" )
public class GameController {

    @Autowired
    private GameRepository gameRepository;


    @GetMapping("/games")
    public List<Games> getAllGames(Model model){
        return this.gameRepository.findAll();

    }



    @GetMapping("/games/{id}")
    public ResponseEntity<Games> getGamesId(@PathVariable(value="id") Long gameId)
            throws ResourceException {
        Games games = gameRepository.findById(gameId)
                .orElseThrow(()-> new ResourceException("Games not found for this id ::"+
                        gameId));
        return ResponseEntity.ok().body(games);
    }

    //save games
    @PostMapping("/games")
    public Games CreateGames(@Valid @RequestBody Games games) {
        return gameRepository.save(games);
    }

    //update  games
    @PutMapping("/games/{id}")
    public ResponseEntity<Games> updateGames(@PathVariable(value = "id") Long gameId,
                                                   @Valid @RequestBody Games gamesDetails)
            throws ResourceException {
        Games games = gameRepository.findById(gameId)
                .orElseThrow(()-> new ResourceException("Game not found for this id :: " + gameId));


        games.setGame_name(gamesDetails.getGame_name());
        games.setRelease_date(gamesDetails.getRelease_date());
        games.setSummary(gamesDetails.getSummary());


        final Games updatedGames = gameRepository.save(games);


        return ResponseEntity.ok(updatedGames);
    }

 
    //  Delete Game

    @DeleteMapping("/games/{id}")
    public Map<String, Boolean> deletedGames(@PathVariable(value = "id") Long gameId)
            throws ResourceException {
        Games games = gameRepository.findById(gameId)
                .orElseThrow(()-> new ResourceException("Game not found for this id :: " + gameId));

        gameRepository.delete(games);
        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted game", Boolean.TRUE);

        return response;

    }





}

