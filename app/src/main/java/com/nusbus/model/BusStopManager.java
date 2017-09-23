package com.nusbus.model;

import java.util.ArrayList;

/**
 * Created by Herman on 22/8/2017.
 */

public class BusStopManager {
    public ArrayList<Vertex> busStopsList = new ArrayList<Vertex>(); //Consists of all the bus stops
    public ArrayList<ArrayList<Vertex>> adjList = new  ArrayList<ArrayList<Vertex>> ();
    public ArrayList<Vertex> A1 = new ArrayList<>();
    public ArrayList<Vertex> A2 = new ArrayList<>();
    public ArrayList<Vertex> D1 = new ArrayList<>();
    public ArrayList<Vertex> D2 = new ArrayList<>();
            
    public BusStopManager(){
        ArrayList<String>list = new ArrayList<>();
        list.add("A1");
        
        //0
        Vertex kentRidgeMRT = new Vertex("KentRidgeMRT",2052,332,0,1,0,0,1);
        busStopsList.add(kentRidgeMRT);

        //1
        Vertex LT29 = new Vertex("LT29",1747,333,1,1,1,0,0);
        busStopsList.add(LT29);
        //2
        Vertex universityHall = new Vertex("UniversityHall",1380,334,2,1,1,0,0);
        busStopsList.add(universityHall);
        //3
        Vertex oppUHC = new Vertex("oppUHC",1029,332,3,1,1,0,0);
        busStopsList.add(oppUHC);
        //4
        Vertex YusofIshakHouse = new Vertex("yusofIshakHouse",747,460,4,1,0,1,0);
        busStopsList.add(YusofIshakHouse);
        //5
        Vertex CLB = new Vertex("CLB",747,810,5,1,0,1,0);
        busStopsList.add(CLB);
        //6
        Vertex LT13 = new Vertex("LT13",881,1088,6,1,0,1,0);
        busStopsList.add(LT13);
        //7
        Vertex AS7 = new Vertex("AS7",1158,1089,7,1,0,1,0);
        busStopsList.add(AS7);
        //8
        Vertex com2 = new Vertex("com2",1316,702,8,1,1,1,0);
        busStopsList.add(com2);
        //9
        Vertex biz2 = new Vertex("biz2",1672,876,9,1,0,1,0);
        busStopsList.add(biz2);
        //10
        Vertex oppHouse12 = new Vertex("oppHouse12",1729,691,10,1,0,0,0);
        busStopsList.add(oppHouse12);
        //11
        Vertex house7 = new Vertex("house7",2036,693,11,1,0,0,0);
        busStopsList.add(house7);
        //12
        Vertex pgbEnd = new Vertex("pgbEnd",2182,589,12,1,1,1,0);
        busStopsList.add(pgbEnd);
        
        
        //13
        Vertex oppHonSunSen = new Vertex("OppHonSunSen",1730,917,13,0,1,1,0);
        busStopsList.add(oppHonSunSen);
        //14
        Vertex ventus = new Vertex("Ventus",947,1136,14,0,1,1,0);
        busStopsList.add(ventus);
        //15
        Vertex computerCentre = new Vertex("ComputerCentre",698,878,15,0,1,1,0);
        busStopsList.add(computerCentre);
        //16
        Vertex oppYusofIshak = new Vertex("OppYusofIshak",700,525,16,0,1,1,0);
        busStopsList.add(oppYusofIshak);
        //17
        Vertex museum = new Vertex("museum",479,287,17,0,1,1,1);
        busStopsList.add(museum);
        //18
        Vertex uTown = new Vertex("uTown",729,129,18,0,0,1,1);
        busStopsList.add(uTown);
        
        
        //19
        Vertex uhc = new Vertex("uhc",959,280,19,0,1,0,1);
        busStopsList.add(uhc);
        //20
        Vertex oppUniversityHall = new Vertex("oppUniversityHall",1313,281,20,0,1,0,1);
        busStopsList.add(oppUniversityHall);
        //21
        Vertex s17 = new Vertex("s17",1679,282,21,0,1,0,1);
        busStopsList.add(s17);
        //22
        Vertex oppKrMRT = new Vertex("oppKrMrt",1981,282,22,0,1,0,1);
        busStopsList.add(oppKrMRT);
        
        //23
        Vertex house15 = new Vertex("house15",1902,693,23,0,1,0,0);
        busStopsList.add(house15);
        //24
        Vertex house12 = new Vertex("house12",1798,742,24,0,1,0,0);
        busStopsList.add(house12);
        //25
        Vertex pgb = new Vertex("pgb",2179,589,25,0,1,0,1);
        busStopsList.add(pgb);
    
        A1.add(pgb);
        A1.add(kentRidgeMRT);
        A1.add(LT29);
        A1.add(universityHall);
        A1.add(oppUHC);
        A1.add(YusofIshakHouse);
        A1.add(CLB);
        A1.add(LT13);
        A1.add(AS7);
        A1.add(com2);
        A1.add(biz2);
        A1.add(oppHouse12);
        A1.add(house7);
        A1.add(pgbEnd);
        
        A2.add(pgbEnd);
        A2.add(house15);
        A2.add(house12);
        A2.add(oppHonSunSen);
        A2.add(com2);
        A2.add(ventus);
        A2.add(computerCentre);
        A2.add(oppYusofIshak);
        A2.add(museum);
        A2.add(uhc);
        A2.add(oppUniversityHall);
        A2.add(s17);
        A2.add(oppKrMRT);
        A2.add(pgb);
        
        D1.add(oppHonSunSen);
        D1.add(com2);
        D1.add(ventus);
        D1.add(computerCentre);
        D1.add(oppYusofIshak);
        D1.add(museum);
        D1.add(uTown);
        D1.add(YusofIshakHouse);
        D1.add(CLB);
        D1.add(LT13);
        D1.add(AS7);
        D1.add(com2);
        D1.add(biz2);
        
        D2.add(pgb);
        D2.add(kentRidgeMRT);
        D2.add(LT29);
        D2.add(universityHall);
        D2.add(oppUHC);
        D2.add(museum);
        D2.add(uTown);
        D2.add(uhc);
        D2.add(oppUniversityHall);
        D2.add(s17);
        D2.add(oppKrMRT);
        D2.add(pgb);
    }
}


       /*
        for(int i = 0; i < 26; i++) {
            adjList.add(new ArrayList<Vertex>());
        }
        //adjList.get(0).add(kentRidgeMRT);
        //The index depends on when i add them
        adjList.get(0).add(LT29);
        adjList.get(1).add(universityHall);
        adjList.get(2).add(oppUHC);
        adjList.get(3).add(YusofIshakHouse);
        adjList.get(3).add(museum);
        adjList.get(4).add(CLB);
        adjList.get(5).add(LT13);
        adjList.get(6).add(AS7);
        adjList.get(7).add(com2);
        adjList.get(8).add(biz2);
        adjList.get(8).add(ventus);
        adjList.get(9).add(oppHouse12);
        adjList.get(10).add(house7);
        adjList.get(11).add(pgbEnd);
        
        adjList.get(13).add(ventus);
        adjList.get(14).add(computerCentre);
        adjList.get(15).add(oppYusofIshak);
        adjList.get(16).add(museum);
        adjList.get(17).add(uTown);
        adjList.get(17).add(uhc);
        adjList.get(18).add(YusofIshakHouse);
        adjList.get(18).add(uhc);
        adjList.get(19).add(oppUniversityHall);
        adjList.get(20).add(s17);
        adjList.get(21).add(oppKrMRT);
        adjList.get(22).add(pgbEnd);
        
        adjList.get(25).add(house15);
        adjList.get(23).add(house12);
        adjList.get(24).add(oppHonSunSen);
        */
        