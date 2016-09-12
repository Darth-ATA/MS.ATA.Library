/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * This class has all the attributes and methods needed for draw a ellipse.
 * @author Darth-ATA
 */
public class GLine extends GShape{    
    /**
     * Default constructor of the line.
     */
    public GLine(){
        this.attributes = new GAttribute();
    };
    
    /**
     * Constructor of the line.
     * 
     * @param startPoint origin point of the line.
     * @param endPoint end point of the line.
     */
    public GLine(Point2D startPoint, Point2D endPoint){
        this.shape = new Line2D.Double(startPoint, endPoint);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the line.
     * 
     * @param startPoint origin point of the line.
     * @param endPoint end point of the line.
     * @param attributes properties of the line.
     */
    public GLine(Point2D startPoint, Point2D endPoint, GAttribute attributes){
        this.shape = new Line2D.Double(startPoint, endPoint);
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the line.
     * @param line to be like.
     */
    public GLine(GLine line){
        this.shape = new Line2D.Double(line.getStartPoint(), line.getEndPoint());
        this.attributes = line.getAttributes();
    }
    
    /**
     * Creates and return a copy of the line.
     * @return the copy of the line.
     */
    @Override
    public GLine clone(){
        return new GLine(this);
    }
    
    /**
     * Provides if a point its in the line, needed for have a larger field to 
     * click.
     * @param point to check if it in the line.
     * @return true or false depending on the results.
     */
    @Override
    public boolean contains(Point2D point){
        return ((Line2D)this.shape).ptLineDist(point) <= 4.0;
    }

    /**
     * Moves the line to another point.
     * 
     * @param pos the reference point for the traslation of the line.
     */
    @Override
    public void moveShape(Point2D pos) {
        double distX = pos.getX() - ((Line2D)this.shape).getX1();
        double distY = pos.getY() - ((Line2D)this.shape).getY1();
        Point2D point = new Point2D.Double(((Line2D)this.shape).getX2() + distX, ((Line2D)this.shape).getY2() + distY);
        ((Line2D)this.shape).setLine(pos, point);
    }    

    /**
     * Updates the line (redimensionations).
     * @param startPoint new start point of the line.
     * @param endPoint new end point of the line.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        ((Line2D)this.shape).setLine(startPoint, endPoint);
    }

    /**
     * Obtains the start point of a line.
     * @return the start point.
     */
    @Override
    public Point2D getStartPoint() {
        return ((Line2D)this.shape).getP1();
    }

    /**
     * Obtains the end point of a line.
     * @return the end point.
     */
    @Override
    public Point2D getEndPoint() {
        return ((Line2D)this.shape).getP2();
    }

    /**
     * Obtains interesting points of the line.
     * @param index of the line.
     * @return the point desired.
     */
    @Override
    public Point2D getInterestPoint(int index) {
        Point2D point;
        if (index == 0){
            point = getStartPoint();
        }
        else {
            point = getEndPoint();
        }
        return point;
    }
}
