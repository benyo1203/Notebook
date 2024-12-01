package com.example.notebook;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UzenetRepo extends CrudRepository<Uzenet, Integer> {
    List<Uzenet> findAllByOrderByTimestampDesc();
}
