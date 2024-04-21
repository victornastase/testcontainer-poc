package com.victornastase.testcontainerpoc.models;

import org.springframework.data.annotation.Id;

public record Game(@Id Integer id, String title) {
}
