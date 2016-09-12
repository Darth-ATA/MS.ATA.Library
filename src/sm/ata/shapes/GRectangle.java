/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class has all the attributes and methods needed for draw a rectangle.
 * @author Darth-ATA
 */
public class GRectangle extends GRectangularShape{
    
    /**
     * Default constructor of the rectangle.
     */
    public GRectangle(){
        this.attributes = new GAttribute();
    };
    
    /**
     * Constructor of the rectangle.
     * 
     * @param startPoint origin point of the rectangle.
     * @param endPoint end point of the rectangle.
     */
    public GRectangle(Point2D startPoint, Point2D endPoint){
        this.shape = new Rectangle2D.Double(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the rectangle.
     * 
     * @param startPoint origin point of the rectangle.
     * @param endPoint end point of the rectangle.
     * @param attributes properties of the rectangle.
     */
    public GRectangle(Point2D startPoint, Point2D endPoint, GAttribute attributes){
        this.shape = new Rectangle2D.Double(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the rectangle.
     * @param rectangle to be like.
     */
    public GRectangle(GRectangle rectangle){
        this.shape = new Rectangle2D.Double(rectangle.getStartPoint().getX(), rectangle.getEndPoint().getY(),
                rectangle.getEndPoint().getX() - rectangle.getStartPoint().getY(),
                rectangle.getEndPoint().getY() - rectangle.getStartPoint().getY());
        this.attributes = rectangle.getAttributes();
    }
    
    /**
     * Creates and return a copy of the rectangle.
     * @return the copy of the rectangle.
     */
    @Override
    public GRectangle clone(){
        return new GRectangle(this);
    }

    /**
     * Moves the rectangle to another point.
     * 
     * @param pos the reference point for the traslation of the rectangle.
     */
    @Override
    public void moveShape(Point2D pos) {
        Rectangle2D rectangle = (Rectangle2D)this.shape;
        Dimension dim = new Dimension((int) rectangle.getWidth(), (int) rectangle.getHeight());
        rectangle.setFrame(pos, dim);
    }    
    
    /**
     * Updates the rectangle (redimensionations).
     * @param startPoint new start point of the rectangle.
     * @param endPoint new end point of the rectangle.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        ((Rectangle2D)this.shape).setFrameFromDiagonal(startPoint, endPoint);
    }
}
