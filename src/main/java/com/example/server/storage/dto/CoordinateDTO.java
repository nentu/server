package com.example.server.storage.dto;

import com.example.server.storage.entities.Coordinate;
import com.example.server.storage.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateDTO {
    private long id;
    private float x;
    private float y;
    private float r;
    private boolean success;
    private String ownerName;

    public CoordinateDTO(Coordinate source, String authorName){
        this(
                source.getId(),
                source.getX(),
                source.getY(),
                source.getR(),
                source.isSuccess(),
                authorName
        );
    }
}
