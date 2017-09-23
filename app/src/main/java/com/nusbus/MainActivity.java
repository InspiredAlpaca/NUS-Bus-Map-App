package com.nusbus;
import java.util.*;
import java.io.*;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.ls.widgets.map.MapWidget;
import com.ls.widgets.map.config.OfflineMap;
import com.ls.widgets.map.events.MapTouchedEvent;
import com.ls.widgets.map.interfaces.Layer;
import com.ls.widgets.map.interfaces.MapEventsListener;
import com.ls.widgets.map.interfaces.OnMapDoubleTapListener;
import com.ls.widgets.map.interfaces.OnMapTouchListener;
import com.ls.widgets.map.model.MapLayer;
import com.ls.widgets.map.model.MapObject;
import com.ls.widgets.map.utils.PivotFactory;
import com.nusbus.logic.Logic;
import com.nusbus.model.BusStopManager;
import com.nusbus.model.Vertex;
import java.util.ArrayList;

public class MainActivity extends Activity {
    public static final int MAP_ID = 1;
    private static final long LAYER_ID = 5;
    private Layer layer;
    Vertex startingVertex;
    Vertex endingVertex;
    BusStopManager busStopManager = new BusStopManager();
    Logic logic = new Logic(busStopManager);
    TextView busStopStart;
    TextView busStopEnd;
    TextView busesList;
    FrameLayout infoView;
    LinearLayout mapSpace;
    TableLayout tableSpace;
    TableRow endTable, busTable;
    private final Handler mHandler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Which xml file to show layout
        final int initialZoomLevel = 12; //This refers to the folder number
        //MAP_ROOT tells the program which asset file to use, inside, there is a xml file that tells the picture size
        final MapWidget mapWidget = new MapWidget(savedInstanceState, this, OfflineMap.MAP_ROOT, initialZoomLevel);
        
        //mapWidget.setId(MAP_ID);
        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);
        //layout.addView(mapWidget); //MapWidget extends view
        infoView = (FrameLayout)this.findViewById(R.id.infoView);
        mapSpace = (LinearLayout)this.findViewById(R.id.map);
        tableSpace = (TableLayout) this.findViewById(R.id.table);
        mapSpace.addView(mapWidget);

        layer = mapWidget.createLayer(LAYER_ID);
        mapWidget.getConfig().setFlingEnabled(true);
        mapWidget.getConfig().setPinchZoomEnabled(true);
        mapWidget.getConfig().setMinZoomLevelLimit(10);
        mapWidget.setMaxZoomLevel(14);//After putting the folder 12, i can zoom to 14.
        mapWidget.setUseSoftwareZoom(true); //Allows digital zoom
        mapWidget.setZoomButtonsVisible(true); //Just the zoom button
        mapWidget.setBackgroundColor(Color.WHITE);
        
        //InfoView stuff
        busStopStart = (TextView)this.findViewById(R.id.busStopStart);
        busStopEnd = (TextView)this.findViewById(R.id.busStopEnd);
        busesList = (TextView)this.findViewById(R.id.buses);
        endTable = (TableRow) this.findViewById(R.id.endTable);
        busTable = (TableRow) this.findViewById(R.id.busTable);
        tableSpace.setVisibility(View.GONE);
        
        //Automatically transfer pixel coordinates to x and y.
        mapWidget.setOnMapTouchListener(new OnMapTouchListener() {
            @Override
            public void onTouch(MapWidget v, MapTouchedEvent event) throws InterruptedException {
                //Creating a new layer which is an orange blob
                Drawable orangeBlob = ResourcesCompat.getDrawable(getResources(),R.drawable.orangeblob,null);
                
                //Whenever you press, you will have a x & y coordinates of the pixels
                int x = event.getMapX();
                int y = event.getMapY();
                Boolean checkWithinCircle = false;
                
                //Loop through the entire arrayList consisting of BusStop Vertex and compares if it is within a radius
                for(int i = 0; i < busStopManager.busStopsList.size();i++){
                    double dx = Math.pow(x-busStopManager.busStopsList.get(i).x,2); //Formula to check if it is within
                    double dy = Math.pow(y-busStopManager.busStopsList.get(i).y,2);
                    if ((dx + dy) < Math.pow(50, 2)) { //If within the radius
                        checkWithinCircle = true;
                        //Toast.makeText(MainActivity.this, "Within Circle", Toast.LENGTH_SHORT).show();
                        
                        //If it is within the circle and starting vertex is null, i will allocate this as starting vertex
                        if(startingVertex == null) {
                            mapWidget.scrollMapTo(busStopManager.busStopsList.get(i).x , busStopManager.busStopsList.get(i).y);
                            //Create a map object to be added, in this case it is a orange blob
                            //Parameter is (ID, orangeBlob's  xml, Point on map coordinate, point on image coordinate,isTouchable, is scalable
                            MapObject mapObject = new MapObject(1, orangeBlob, new Point(busStopManager.busStopsList.get(i).x, busStopManager.busStopsList.get(i).y),
                                                                PivotFactory.createPivotPoint(orangeBlob, PivotFactory.PivotPosition.PIVOT_CENTER), false, true);
                            layer.addMapObject(mapObject);
                            startingVertex = busStopManager.busStopsList.get(i); //Where is is the busStop within radius
                            busStopStart.setText(startingVertex.getBusStopName()); //Set starting text of Starting BusStop
                            tableSpace.setVisibility(View.VISIBLE); //Now i reveal the infoView after i have a starting location
                            
                        } else{ //StartingVertex != null, means it is allocated, since im within the box, i will now allocate ending vertex
                            endingVertex = busStopManager.busStopsList.get(i);
                            layer.clearAll();
                            endTable.setVisibility(View.VISIBLE);
                            busTable.setVisibility(View.VISIBLE);
                            busStopEnd.setText("To " + endingVertex.getBusStopName());
                        }
                        break; //Break out of loop searching for the vertex
                    }
                }
                if(!checkWithinCircle){ //Not within the radius
                    clearInfoView(mapWidget);
                    //Toast.makeText(MainActivity.this, "Not within circle", Toast.LENGTH_SHORT).show();
                }
                //If i have a start and end point, i can now find the route
                if(startingVertex != null && endingVertex != null) {
                    ArrayList<Vertex> routes = logic.searchRoute(startingVertex,endingVertex);
                    for(int i = 0; i < routes.size(); i++){

                        int zoomLevel =mapWidget.getZoomLevel();
                        if(zoomLevel >12){
                            mapWidget.zoomOut();
                        }
                        mapWidget.scrollMapTo(routes.get(i).x,routes.get(i).y);
                        
                        animateDots(routes.get(i));
                    }
                    boolean[] buses = logic.identifyBuses(startingVertex,endingVertex);
                    String busList = "Buses: ";
                    boolean foundBus = false;
                    for(int i = 0; i < 4; i++){
                        if(buses[i]){
                            busesList.setTextColor(Color.WHITE);
                            switch (i){
                                case 0: busList = busList + "A1 ";
                                    foundBus = true;
                                    break;
                                case 1: busList = busList + "A2 ";
                                    foundBus = true;
                                    break;
                                case 2: busList = busList + "D1 ";
                                    foundBus = true;
                                    break;
                                case 3: busList = busList + "D2 ";
                                    foundBus = true;
                                    break;
                            }
                        }
                    }
                    if(!foundBus){
                        busList ="Unable to find direct route";
                        busesList.setTextColor(Color.RED);
                        Toast.makeText(MainActivity.this, "Unable to find a direct route", Toast.LENGTH_SHORT).show();
                    }
                    busesList.setText(busList);
                }
            }
        });
        
        

        mapWidget.addMapEventsListener(new MapEventsListener() {
            @Override
            public void onPreZoomOut() {
                // TODO Auto-generated method stub
            }
            @Override
            public void onPreZoomIn() {
                // TODO Auto-generated method stub
                
            }
            @Override
            public void onPostZoomOut() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTitle("Zoom level: " + mapWidget.getZoomLevel());
                    }
                });
            }
            @Override
            public void onPostZoomIn() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTitle("Zoom level: " + mapWidget.getZoomLevel());
                    }
                });
                
            }
        });
    }
    
    /**
     * This method animates the dots, it takes in a vertex that has the coordinates
     * mapObject is a API call that will paint the object at the respective place
     * .addMapObjects attaches it to the screen
     * @param toPaint
     */
    public void animateDots(Vertex toPaint){
        Drawable icon= ResourcesCompat.getDrawable(getResources(),R.drawable.orangeblob,null);
        MapObject mapObject = new MapObject(28, icon, new Point(toPaint.x, toPaint.y),
                PivotFactory.createPivotPoint(icon, PivotFactory.PivotPosition.PIVOT_CENTER), false, true);
        layer.addMapObject(mapObject);
    }
    
    /**
     * This method clears the dots on screen and hides the infoView window
     * @param mapWidget
     */
    public void clearInfoView(MapWidget mapWidget){
        layer.clearAll();
        mapWidget.scrollBy(1,1);
        startingVertex = null;
        endingVertex = null;
        endTable.setVisibility(View.INVISIBLE);
        busTable.setVisibility(View.INVISIBLE);
        tableSpace.setVisibility(View.GONE);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.a1menu, menu);
        
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        MapWidget map = (MapWidget) findViewById(MAP_ID);
        
        int i = item.getItemId();
        if (i == R.id.scrollTo)
        {
            map.scrollMapTo(new Point(240, 320));
        }
        else if (i == R.id.jumpTo)
        {
            map.jumpTo(new Point(240, 320));
        }
        return super.onOptionsItemSelected(item);
    }
    
}
