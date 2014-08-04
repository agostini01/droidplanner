// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 * A simple implementation of the {@link MapMarker} interface. Each map marker
 * is painted as a circle with a black border line and filled with a specified
 * color.
 *
 * @author Jan Peter Stotz
 *
 */
public class MapMarkerCircle extends MapObjectImpl implements MapMarker {

    Coordinate coord;
    double radius;
    STYLE markerStyle;
	private Image img;

    public MapMarkerCircle(Coordinate coord, double radius) {
        this(null, null, coord, radius);
    }
    public MapMarkerCircle(String name, Coordinate coord, double radius) {
        this(null, name, coord, radius);
    }
    public MapMarkerCircle(Layer layer, Coordinate coord, double radius) {
        this(layer, null, coord, radius);
    }
    public MapMarkerCircle(double lat, double lon, double radius) {
        this(null, null, new Coordinate(lat,lon), radius);
    }
    public MapMarkerCircle(Layer layer, double lat, double lon, double radius) {
        this(layer, null, new Coordinate(lat,lon), radius);
    }
    public MapMarkerCircle(Layer layer, String name, Coordinate coord, double radius) {
        this(layer, name, coord, radius, STYLE.VARIABLE, getDefaultStyle());
    }
    public MapMarkerCircle(Layer layer, String name, Coordinate coord, double radius, STYLE markerStyle, Style style) {
        super(layer, name, style);
        this.markerStyle = markerStyle;
        this.coord = coord;
        this.radius = radius;
        
        try {
			img = ImageIO.read(new File("../Android/res/drawable-hdpi/quad.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Coordinate getCoordinate(){
        return coord;
    }
    public double getLat() {
        return coord.getLat();
    }

    public double getLon() {
        return coord.getLon();
    }

    public double getRadius() {
        return radius;
    }

    public STYLE getMarkerStyle() {
        return markerStyle;
    }

    public void paint(Graphics g, Point position, int radio) {
        int size_h = radio;
        int size = size_h * 2;

        g.drawImage(img,50,50,null);
        if(getLayer()==null||getLayer().isVisibleTexts()) paintText(g, position);
    }

    public static Style getDefaultStyle(){
        return new Style(Color.ORANGE, new Color(200,200,200,200), null, getDefaultFont());
    }
    @Override
    public String toString() {
        return "MapMarker at " + getLat() + " " + getLon();
    }
    @Override
    public void setLat(double lat) {
        if(coord==null) coord = new Coordinate(lat,0);
        else coord.setLat(lat);
    }
    @Override
    public void setLon(double lon) {
        if(coord==null) coord = new Coordinate(0,lon);
        else coord.setLon(lon);
    }
}
