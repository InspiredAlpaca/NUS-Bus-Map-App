package com.nusbus.model;


import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by Herman on 22/8/2017.
 */

public class Vertex {
    public int x,y;
    String busStopName;
    ArrayList<String> buses = new ArrayList<String>();
    public int vertexNumber;
    public int busService[] = new int[4];
    public Vertex(String name, int x, int y,int vertexNumber, int A1, int A2, int D1, int D2)
    {
        busService[0] = A1;
        busService[1] = A2;
        busService[2] = D1;
        busService[3] = D2;
        busStopName = name;
        this.x = x;
        this.y = y;
        this.vertexNumber = vertexNumber;
    }
    
    public String getBusStopName(){
        return this.busStopName;
    }
}
