/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * This class has all the attributes and methods needed for draw an ellipse.
 * @author Darth-ATA
 */
public class GEllipse extends GRectangularShape{
    /**
     * Default constructor of the ellipse.
     */
    public GEllipse(){
        this.attributes = new GAttribute();
    };
    
    /**
     * Constructor of the ellipse.
     * 
     * @param startPoint origin point of the ellipse.
     * @param endPoint end point of the ellipse.
     */
    public GEllipse(Point2D startPoint, Point2D endPoint){
        this.shape = new Ellipse2D.Double(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the ellipse.
     * 
     * @param startPoint origin point of the ellipse.
     * @param endPoint end point of the ellipse.
     * @param attributes properties of the ellipse.
     */
    public GEllipse(Point2D startPoint, Point2D endPoint, GAttribute attributes){
        this.shape = new Ellipse2D.Double(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the ellipse.
     * @param ellipse to be like.
     */
    public GEllipse(GEllipse ellipse){
        this.shape = new Ellipse2D.Double(ellipse.getStartPoint().getX(), ellipse.getEndPoint().getY(),
                ellipse.getEndPoint().getX() - ellipse.getStartPoint().getY(),
                ellipse.getEndPoint().getY() - ellipse.getStartPoint().getY());
        this.attributes = ellipse.getAttributes();
    }
    
    /**
     * Creates and return a copy of the ellipse.
     * @return the copy of the ellipse.
     */
    @Override
    public GEllipse clone(){
        return new GEllipse(this);
    }

    /**
     * Moves the ellipse to another point.
     * 
     * @param pos the reference point for the traslation of the ellipse.
     */
    @Override
    public void moveShape(Point2D pos) {
        Ellipse2D ellipse = (Ellipse2D)this.shape;
        Dimension dim = new Dimension((int) ellipse.getWidth(), (int) ellipse.getHeight());
        ellipse.setFrame(pos, dim);
    }         
    
    /**
     * Updates the ellipse (redimensionations).
     * @param startPoint new start point of the ellipse.
     * @param endPoint new end point of the ellipse.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        ((Ellipse2D)this.shape).setFrameFromDiagonal(startPoint, endPoint);
    }
}
