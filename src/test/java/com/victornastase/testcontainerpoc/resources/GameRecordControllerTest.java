package com.victornastase.testcontainerpoc.resources;

import com.victornastase.testcontainerpoc.models.entities.Game;
import com.victornastase.testcontainerpoc.repositories.GameRepo;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameRecordControllerTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private GameRepo gameRepo;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        gameRepo.deleteAll();
    }

    @Test
    void fetchGamesTest() {
        gameRepo.saveAll(List.of(
                new Game(1, "Call of Duty"),
                new Game(2, "Helldivers 2"),
                new Game(3, "Elden Ring"),
                new Game(4, "GTA VI")
        ));

        when().get("/games")
                .then()
                .statusCode(200)
                .body(".", hasSize(4));
    }

}
