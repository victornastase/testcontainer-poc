package com.victornastase.testcontainerpoc.repositories;

import com.victornastase.testcontainerpoc.models.Game;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends ListCrudRepository<Game, Integer> {
}
