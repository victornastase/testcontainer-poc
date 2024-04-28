package com.victornastase.testcontainerpoc.resources;

import com.victornastase.testcontainerpoc.models.entities.Game;
import com.victornastase.testcontainerpoc.repositories.GameRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    private final GameRepo repo;

    public GameController(GameRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/games")
    public List<Game> games(){
        return this.repo.findAll();
    }
}
