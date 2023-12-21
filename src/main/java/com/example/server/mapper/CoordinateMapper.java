package com.example.server.mapper;

import com.example.server.service.ControllerService;
import com.example.server.storage.dto.CoordinateDTO;
import com.example.server.storage.entities.Coordinate;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring", uses= ControllerService.class)
public interface CoordinateMapper {
    Coordinate dtoToEntity(CoordinateDTO coordinate);

}
