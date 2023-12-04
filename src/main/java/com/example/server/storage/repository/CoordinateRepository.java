package com.example.server.storage.repository;

import com.example.server.storage.entities.Coordinate;
import com.example.server.storage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    List<Coordinate> findAllByAuthor(User user);

    void deleteAllByAuthor(User user);
}
