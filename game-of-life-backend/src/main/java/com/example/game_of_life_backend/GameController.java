package com.example.game_of_life_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/initialize")
    public void initializeGame(@RequestBody int[] dimensions) {
        // You can use dimensions here if needed
        gameService.initializeVerden(dimensions[0], dimensions[1]);
    }   

    @GetMapping("/state")
    public Verden getGameState() {
        gameService.updateVerden();
        return gameService.getVerden();
    }

    @GetMapping("/update")
    public Verden getGameUpdate(){
        gameService.updateVerden();
        return gameService.getVerden();
    }
}
