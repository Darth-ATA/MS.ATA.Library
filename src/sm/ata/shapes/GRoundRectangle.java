/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * This class has all the attributes and methods needed for draw a rectangle
 * with round corners.
 * 
 * @author Darth-ATA
 */
public class GRoundRectangle extends GRectangularShape {
    
    /**
     * Default constructor of the round rectangle.
     */
    public GRoundRectangle(){
        this.shape = new RoundRectangle2D.Double();
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the round rectangle.
     * 
     * @param startPoint origin point of the round rectangle.
     * @param endPoint end point of the round rectangle.
     */
    public GRoundRectangle(Point2D startPoint, Point2D endPoint){
        this.shape = new RoundRectangle2D.Double(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                0.5f, 0.5f);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the round rectangle.
     * 
     * @param startPoint origin point of the round rectangle.
     * @param endPoint end point of the round rectangle.
     * @param arcw arc with of the round arc.
     * @param arch arc height of the round arc.
     */
    public GRoundRectangle(Point2D startPoint, Point2D endPoint, double arcw, double arch){
        this.shape = new RoundRectangle2D.Double(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                arcw, arch);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the rectangle.
     * 
     * @param startPoint origin point of the round rectangle.
     * @param endPoint end point of the round rectangle.
     * @param attributes properties of the round rectangle.
     * @param arcw arc with of the round arc.
     * @param arch arc height of the round arc.
     */
    public GRoundRectangle(Point2D startPoint, Point2D endPoint, GAttribute attributes, double arcw, double arch){
        this.shape = new RoundRectangle2D.Double(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                arcw, arch);
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the round rectangle.
     * @param roundDectangle to be like.
     */
    public GRoundRectangle(GRoundRectangle roundDectangle){
        this.shape = new RoundRectangle2D.Double(roundDectangle.getStartPoint().getX(), roundDectangle.getEndPoint().getY(),
                roundDectangle.getEndPoint().getX() - roundDectangle.getStartPoint().getX(),
                roundDectangle.getEndPoint().getY() - roundDectangle.getStartPoint().getY(),
                roundDectangle.getArcw(), roundDectangle.getArch());
        this.attributes = roundDectangle.getAttributes();
    }
    
    public double getArcw(){
        return ((RoundRectangle2D)this.shape).getArcWidth();
    }
    
    public double getArch(){
        return ((RoundRectangle2D)this.shape).getArcHeight();
    }
    
    /**
     * Creates and return a copy of the round rectangle.
     * @return the copy of the round rectangle.
     */
    @Override
    public GRoundRectangle clone(){
        return new GRoundRectangle(this);
    }

    /**
     * Moves the round rectangle to another point.
     * 
     * @param pos the reference point for the traslation of the round rectangle.
     */
    @Override
    public void moveShape(Point2D pos) {
        RoundRectangle2D rectangle = (RoundRectangle2D)this.shape;
        Dimension dim = new Dimension((int) rectangle.getWidth(), (int) rectangle.getHeight());
        rectangle.setFrame(pos, dim);
    }    
    
    /**
     * Updates the round rectangle (redimensionations).
     * @param startPoint new start point of the round rectangle.
     * @param endPoint new end point of the round rectangle.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        ((Rectangle2D)this.shape).setFrameFromDiagonal(startPoint, endPoint);
    }
}
