package com.example.server;

import com.example.server.storage.dto.CoordinateDTO;

public class CoordinateChecker {


    public static boolean check(CoordinateDTO coordinate) {
        var x = coordinate.getX();
        var y = coordinate.getY();
        var r = coordinate.getR();

        if (x > 0) {
            if (y > 0) {
                return false;
            } else {
                return y > x - r / 2;
            }
        } else {
            if (y < 0) {
                return r * r / 4 > x*x + y*y;
            }else{
                return (x > - r && y < r / 2);
            }
        }
    }
}
