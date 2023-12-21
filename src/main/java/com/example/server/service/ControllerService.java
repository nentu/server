package com.example.server.service;

import com.example.server.CoordinateChecker;
import com.example.server.mapper.CoordinateMapper;
import com.example.server.storage.dto.CoordinateDTO;
import com.example.server.storage.repository.CoordinateRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ControllerService {

    private final CoordinateMapper mapper;

    private final CoordinateRepository coordinateRepository;

    public ControllerService(CoordinateMapper mapper, CoordinateRepository coordinateRepository) {
        this.mapper = mapper;
        this.coordinateRepository = coordinateRepository;
    }

    private float round(float value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp / factor;
    }


    public boolean addCoordinate(CoordinateDTO coordinate, Jwt principal) {


        var owner = principal.getSubject();

        var entity = mapper.dtoToEntity(coordinate);

        entity.setSuccess(CoordinateChecker.check(coordinate));
        entity.setAuthorId(owner);
        coordinateRepository.save(entity);

        return entity.isSuccess();
    }

    private String principalGet(Jwt principal, String key){
        return (String) principal.getClaims().get(key);
    }
    private String getSurnameAndName(Jwt principal){
        return principalGet(principal, "family_name") +" "+ principalGet(principal, "given_name");
    }

    public List<CoordinateDTO> getTable(Jwt principal) {
        String owner = principal.getSubject();
        String ownerName = getSurnameAndName(principal);
        var coordList = coordinateRepository.findAllByAuthorId(owner);
        List<CoordinateDTO> result = new ArrayList<>();
        for (var i : coordList) {
            result.add(new CoordinateDTO(i, ownerName));
        }
        return result;
    }


    public void clear(Jwt principal) {
        var owner = principal.getSubject();
        coordinateRepository.deleteAllByAuthorId(owner);
    }

}
