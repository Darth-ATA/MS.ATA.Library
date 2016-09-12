/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * This class has all the attributes and methods needed for draw an polygon.
 * @author Darth-ATA
 */
public class GPolygon extends GFilledShape{
    
    public static int MAX_POINTS = 20;
    
    /**
     * Default constructor of the polygon.
     */
    public GPolygon(){
        this.shape = new Polygon();
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor with attributes.
     * @param attributes 
     */
    public GPolygon(GAttribute attributes){
        this.shape = new Polygon();
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor.
     * @param polygon to be like.
     */
    public GPolygon(GPolygon polygon){
        this.shape = new Polygon(polygon.getXCoord(), polygon.getYCoord(), polygon.size());
        this.attributes = polygon.getAttributes();
    }
    
    /**
     * Provides the vector of X coordinates of the polygon points.
     * @return vector of X coordinates.
     */
    public int[] getXCoord(){
        return ((Polygon)this.shape).xpoints;
    }
    
    /**
     * Provides the vector of Y coordinates of the polygon points.
     * @return vector of Y coordinates.
     */
    public int[] getYCoord(){
        return ((Polygon)this.shape).ypoints;
    }
    
    /**
     * Provides the number of points of the polygon.
     * @return number of points.
     */
    public int size(){
        return ((Polygon)this.shape).npoints;
    }

    /**
     * Porvides the first point of the polygon.
     * @return first point of the polygon.
     */
    @Override
    public Point2D getStartPoint() {
        Point2D point = this.getPoint(0);
        return point;
    }

    /**
     * Porvides the last point of the polygon.
     * @return last point of the polygon.
     */
    @Override
    public Point2D getEndPoint() {
        Point2D point = this.getPoint(this.size());
        return point;
    }
    
    /**
     * Provides one point of the polygon.
     * @param index of the point.
     * @return point desired.
     */
    private Point2D getPoint(int index){
        Point2D point = new Point2D.Double(this.getXCoord()[index],this.getYCoord()[index]);
        return point;
    }

    /**
     * Moves the polygon.
     * @param pos desired point.
     */
    @Override
    public void moveShape(Point2D pos) {
        int distX = (int)pos.getX() - (int)this.getPoint(0).getX();
        int distY = (int)pos.getY() - (int)this.getPoint(0).getY();
        
        ArrayList<Point2D> points = new ArrayList<>();
        
        for(int index = 0; index < this.size(); index++){
            points.add(new Point2D.Double(this.getPoint(index).getX() + distX, this.getPoint(index).getY() + distY));
        }
        ((Polygon)this.shape).reset();
        for(Point2D point : points){
            this.addPoint(point);
        }
    }

    /**
     * Updates the last point of the polygon.
     * @param controlPoint new value for the last point.
     */
    public void updateControlPoint(Point2D controlPoint) {
        ArrayList<Point2D> points = new ArrayList<>();
        
        for(int i = 0; i < this.size() - 1; i++){
            points.add(new Point2D.Double(this.getPoint(i).getX(), this.getPoint(i).getY()));
        }
        points.add(controlPoint);
        ((Polygon)this.shape).reset();
        for(Point2D point : points){
            this.addPoint(point);
        }
    }

    /**
     * Obtains interesting points of the polygon.
     * @param index of the polygon.
     * @return the index point desired.
     */
    @Override
    public Point2D getInterestPoint(int index) {
        Point2D point = new Point2D.Double(this.getXCoord()[index],this.getYCoord()[index]);
        return point;
    } 
    
    /**
     * Adds a new point to de polygon.
     * @param point new point to add.
     */
    public void addPoint(Point2D point){
        ((Polygon)this.shape).addPoint((int)point.getX(), (int)point.getY());
    }

    /**
     * 
     * @param startPoint
     * @param endPoint 
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
