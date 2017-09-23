package com.nusbus.logic;

import com.nusbus.model.BusStopManager;
import com.nusbus.model.Vertex;
import java.util.ArrayList;

public class Logic {
    BusStopManager busStopManager;
    
    public Logic(BusStopManager busStopManager){
        this.busStopManager = busStopManager;
    }
    
    /**
     * This method takes in the starting and ending vertex and loop it through each route searching method.
     * If the returned array is > 2 means a route have been found.
     * @param starting
     * @param ending
     * @return
     */
    public ArrayList<Vertex> searchRoute(Vertex starting,Vertex ending){
        ArrayList<Vertex> storedRoute = new ArrayList<>();
        ArrayList<Vertex> storedRouteA1;
        ArrayList<Vertex> storedRouteA2;
        ArrayList<Vertex> storedRouteD1;
        ArrayList<Vertex> storedRouteD2;
        storedRouteD1 = searchD1BusRoute(starting,ending);
        if(storedRouteD1.size() > 2){
            storedRoute = storedRouteD1;
        }
        storedRouteD2 = searchD2BusRoute(starting,ending);
        if(storedRouteD2.size() > 2){
            storedRoute = storedRouteD2;
        }
        storedRouteA1 = searchA1BusRoute(starting,ending);
        if(storedRouteA1.size() > 2){
            storedRoute = storedRouteA1;
        }
        storedRouteA2 = searchA2BusRoute(starting,ending);
        if(storedRouteA2.size() > 2){
            storedRoute = storedRouteA2;
        }
    return  storedRoute;
    }
    
    /**
     * This method is used to identify the available buses that can be taken.
     * @param starting
     * @param ending
     * @return
     */
    //Can be optimized further (Wasted loops)
    public boolean[] identifyBuses(Vertex starting,Vertex ending){
        boolean[] buses = new boolean[4];
        ArrayList<Vertex> storedRouteA1;
        ArrayList<Vertex> storedRouteA2;
        ArrayList<Vertex> storedRouteD1;
        ArrayList<Vertex> storedRouteD2;
        
        storedRouteA1 = searchA1BusRoute(starting,ending);
        if(storedRouteA1.size() > 2){
            buses[0] = true;
        }
        storedRouteA2 = searchA2BusRoute(starting,ending);
        if(storedRouteA2.size() > 2){
            buses[1] = true;
        }
        storedRouteD1 = searchD1BusRoute(starting,ending);
        if(storedRouteD1.size() > 2){
            buses[2] = true;
        }
        storedRouteD2 = searchD2BusRoute(starting,ending);
        if(storedRouteD2.size() > 2){
            buses[3] = true;
        }
        return  buses;
    }
    
    /**
     * This method takes in the starting and ending vertex
     * It will loop through the entire bus route stored in busStopManger,
     * If the startingBusStop is found, boolean changed to true
     * Each new route will be added to the array list subsequently
     * Lastly, if the ending busstop is found, it is also added to the array list.
     * If the starting and ending route is found, return the array else return an empty array
     * @param starting
     * @param ending
     * @return
     */
    public ArrayList<Vertex> searchA1BusRoute(Vertex starting, Vertex ending){
        ArrayList<Vertex>storeRoute = new ArrayList<>();
        boolean startingBusStop = false;
        boolean endingBusStop = false;
        for(int i = 0; i < busStopManager.A1.size(); i++){
            String busStopName = busStopManager.A1.get(i).getBusStopName();
            if(busStopName.equals(starting.getBusStopName())){
                startingBusStop = true;
            }
            if(startingBusStop){
                storeRoute.add(busStopManager.A1.get(i));
            }
            if(startingBusStop){
                if(busStopName.equals(ending.getBusStopName())){
                    endingBusStop = true;
                    storeRoute.add(busStopManager.A1.get(i));
                    break;
                }
            }
        }
            if(startingBusStop && endingBusStop) {
                return storeRoute;
            }
            else{
                storeRoute.clear();
                return storeRoute;
            }
    }
    public ArrayList<Vertex> searchA2BusRoute(Vertex starting, Vertex ending){
        ArrayList<Vertex>storeRoute = new ArrayList<>();
        boolean startingBusStop = false;
        boolean endingBusStop = false;
        for(int i = 0; i < busStopManager.A2.size(); i++){
            String busStopName = busStopManager.A2.get(i).getBusStopName();
            if(busStopName.equals(starting.getBusStopName())){
                startingBusStop = true;
            }
            if(startingBusStop){
                storeRoute.add(busStopManager.A2.get(i));
            }
            if(startingBusStop){
                if(busStopName.equals(ending.getBusStopName())){
                    endingBusStop = true;
                    storeRoute.add(busStopManager.A2.get(i));
                    break;
                }
            }
        }
        if(startingBusStop && endingBusStop) {
            return storeRoute;
        }
        else{
            storeRoute.clear();
            return storeRoute;
        }
    }
    public ArrayList<Vertex> searchD1BusRoute(Vertex starting, Vertex ending){
        ArrayList<Vertex>storeRoute = new ArrayList<>();
        boolean startingBusStop = false;
        boolean endingBusStop = false;
        for(int i = 0; i < busStopManager.D1.size(); i++){
            String busStopName = busStopManager.D1.get(i).getBusStopName();
            if(busStopName.equals(starting.getBusStopName())){
                startingBusStop = true;
            }
            if(startingBusStop){
                storeRoute.add(busStopManager.D1.get(i));
            }
            if(startingBusStop){
                if(busStopName.equals(ending.getBusStopName())){
                    endingBusStop = true;
                    storeRoute.add(busStopManager.D1.get(i));
                    break;
                }
            }
        }
        if(startingBusStop && endingBusStop) {
            return storeRoute;
        }
        else{
            storeRoute.clear();
            return storeRoute;
        }
    }
    public ArrayList<Vertex> searchD2BusRoute(Vertex starting, Vertex ending){
        ArrayList<Vertex>storeRoute = new ArrayList<>();
        boolean startingBusStop = false;
        boolean endingBusStop = false;
        for(int i = 0; i < busStopManager.D2.size(); i++){
            String busStopName = busStopManager.D2.get(i).getBusStopName();
            if(busStopName.equals(starting.getBusStopName())){
                startingBusStop = true;
            }
            if(startingBusStop){
                storeRoute.add(busStopManager.D2.get(i));
            }
            if(startingBusStop){
                if(busStopName.equals(ending.getBusStopName())){
                    endingBusStop = true;
                    storeRoute.add(busStopManager.D2.get(i));
                    break;
                }
            }
        }
        if(startingBusStop && endingBusStop) {
            return storeRoute;
        }
        else{
            storeRoute.clear();
            return storeRoute;
        }
    }
}




    /*
    public int[] bfs(Vertex starting, Vertex ending){
        System.out.println("start vertex is: "+ starting.vertexNumber);
        if(starting.vertexNumber != 12 && ending.vertexNumber != 12){
            System.out.println("This executed!!!!");
            if(busStopManager.adjList.get(12).size() > 0)
                busStopManager.adjList.get(12).remove(0);
        }
        else{
            if(busStopManager.adjList.get(12).size() == 0){

                busStopManager.adjList.get(12).add(busStopManager.busStopsList.get(0));
                System.out.println("HELLO" + busStopManager.adjList.get(12).get(0));
            }
        }
        int[] visited = new int[26];
        int[] parent = new int[26];
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.offer(starting); //Initializing the starting point
        System.out.println("starting vertex number: " + starting.vertexNumber);
        visited[starting.vertexNumber] = 1;
        Arrays.fill(visited,0);//Reset the vertex so we have not visit them yet
        Arrays.fill(parent,-1);
    
        while(!queue.isEmpty()){
            Vertex index = queue.poll();
            System.out.println("Vertex that was polled: " + index.vertexNumber);
            System.out.println("Number of neighbour: "+busStopManager.adjList.get(index.vertexNumber).size());
            for(int neighbour = 0; neighbour < busStopManager.adjList.get(index.vertexNumber).size(); neighbour++){
                boolean haveSerivce = false;
                Vertex neighbourNode = busStopManager.adjList.get(index.vertexNumber).get(neighbour);
                for(int i = 0; i < 4; i++){
                    if(starting.busService[i] == neighbourNode.busService[i]){
                        haveSerivce = true;
                    }
                }
                System.out.println("The neighbour is vertex: "+ neighbourNode.vertexNumber);
                if(visited[neighbourNode.vertexNumber] == 0 && haveSerivce ){
                    visited[neighbourNode.vertexNumber] = 1;
                    parent[neighbourNode.vertexNumber] = index.vertexNumber;
                    queue.offer(neighbourNode);
                }
            }
        }
        return parent;
    }
    */
