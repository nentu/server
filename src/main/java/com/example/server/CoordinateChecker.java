package com.example.server;

import com.example.server.storage.dto.CoordinateDTO;

public class CoordinateChecker {


    public static boolean check(CoordinateDTO coordinate){
        return coordinate.getX() > 0;
    }
}
