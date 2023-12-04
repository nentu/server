package com.example.server.storage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {
    @Id @GeneratedValue
    private long id;
    private float x;
    private float y;
    private float r;
    private boolean success;
    @ManyToOne
    private User author;
}
