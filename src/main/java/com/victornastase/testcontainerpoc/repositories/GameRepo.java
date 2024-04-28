package com.victornastase.testcontainerpoc.repositories;

import com.victornastase.testcontainerpoc.models.entities.Game;
import org.springframework.data.repository.ListCrudRepository;

public interface GameRepo extends ListCrudRepository<Game, Integer> {
}
