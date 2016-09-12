/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Dimension;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

/**
 * This class has all the attributes and methods needed for draw an arc.
 * 
 * @author Darth-ATA
 */
public class GArc extends GRectangularShape {
    
    /**
     * Default constructor of the arc.
     */
    public GArc(){
        this.shape = new Arc2D.Double();
        attributes = new GAttribute();
    }
    
    /**
     * Constructor of the arc, stablishes a default arc angle and extent.
     * @param startPoint origin point of the arc.
     * @param endPoint end point of the arc.
     */
    public GArc(Point2D startPoint, Point2D endPoint){
        this.shape = new Arc2D.Double(startPoint.getX(), startPoint.getY(),  
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY(), 
                50, 50, Arc2D.OPEN);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the arc.
     * @param startPoint origin of the arc.
     * @param endPoint end point of the arc.
     * @param controlPoint point that gives the angle and the extent.
     */
    public GArc(Point2D startPoint, Point2D endPoint, Point2D controlPoint){
        this.shape = new Arc2D.Double(startPoint.getX(), startPoint.getY(),  
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY(), 
                50, 50, Arc2D.OPEN);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the arc, stablishes a default arc angle and extent.
     * @param startPoint origin point of the arc.
     * @param endPoint end point of the arc.
     * @param attributes properties of the arc.
     */
    public GArc(Point2D startPoint, Point2D endPoint, GAttribute attributes){
        this.shape = new Arc2D.Double(startPoint.getX(), startPoint.getY(),  
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY(), 
                50, 50, Arc2D.OPEN);
        this.attributes = attributes;
    }
    
    /** ToDO: Use this constructor for do a a better arc creation
     * 
     */
    /**
     * Constructor of the arc.
     * @param startPoint origin of the arc.
     * @param endPoint end point of the arc.
     * @param controlPoint point that gives the angle and the extent.
     * @param attributes properties of the arc.
     */
    public GArc(Point2D startPoint, Point2D endPoint, Point2D controlPoint, GAttribute attributes){
        this.shape = new Arc2D.Double(startPoint.getX(), startPoint.getY(),  
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY(), 
                50, 50, Arc2D.OPEN);
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the arc.
     * @param arc to be like.
     */
    public GArc(GArc arc){
        this.shape = new Arc2D.Double(arc.getStartPoint().getX(), arc.getStartPoint().getY(),
                arc.getEndPoint().getX() - arc.getStartPoint().getX(),
                arc.getEndPoint().getY() - arc.getStartPoint().getY(),
                arc.getAngleStart(), arc.getAngleExtent(), Arc2D.OPEN);
        this.attributes = arc.getAttributes();
    }
    
    /**
     * Creates and return a copy of the arc.
     * @return the copy of the arc.
     */
    @Override
    public GArc clone(){
        return new GArc(this);
    }

    /**
     * Moves the arc to another point.
     * 
     * @param pos the reference point for the traslation of the arc.
     */
    @Override
    public void moveShape(Point2D pos) {
        Arc2D arc = (Arc2D)this.shape;
        Dimension dim = new Dimension((int) arc.getWidth(), (int) arc.getHeight());
        arc.setFrame(pos, dim);
    }
    
    /**
     * Updates the arc (redimensionations).
     * @param startPoint new start point of the arc.
     * @param endPoint new end point of the arc.
     */
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        ((Arc2D) this.shape).setFrameFromDiagonal(startPoint, endPoint);
    }   
    
    /**
     * Obtains the starting angle of the arc.
     * @return the value of the start angle.
     */
    public double getAngleStart(){
        return ((Arc2D) this.shape).getAngleStart();
    }
    
    /**
     * Obtains the exteng angle of the arc.
     * @return the value of the exteng angle.
     */
    public double getAngleExtent(){
        return ((Arc2D) this.shape).getAngleExtent();
    }
}
