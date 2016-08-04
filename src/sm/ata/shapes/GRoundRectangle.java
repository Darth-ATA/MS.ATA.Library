/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

/**
 * This class have all the attributes and methods needed for draw an rectangle
 * with round corners.
 * 
 * @author Darth-ATA
 */
public class GRoundRectangle extends RoundRectangle2D.Double implements GShape {

    private GAttribute attributes;
    
    /**
     * Default constructor of the round rectangle.
     */
    public GRoundRectangle(){
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the round rectangle.
     * 
     * @param startPoint origin point of the round rectangle.
     * @param endPoint end point of the round rectangle.
     * @param arcw width of the round arc
     * @param arch height of the round arc
     */
    public GRoundRectangle(Point2D startPoint, Point2D endPoint, 
            double arcw, double arch){
        super(startPoint.getX(), startPoint.getY(), 
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                arcw, arch);
        attributes = new GAttribute();
    }
    
    /**
     * Constructor of the round rectangle.
     * 
     * @param startPoint origin point of the round rectangle.
     * @param endPoint end point of the round rectangle.
     * @param arcw width of the round arc.
     * @param arch height of the round arc.
     * @param attributes properties of the round rectangle.
     */
    public GRoundRectangle(Point2D startPoint, Point2D endPoint, 
            double arcw, double arch,
            GAttribute attributes){
        super(startPoint.getX(), startPoint.getY(), 
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                arcw, arch);
        this.attributes = attributes;
    }
    
    /**
     * Copy construcor of the round rectangle.
     * @param roundRectangle to be like.
     */
    public GRoundRectangle(GRoundRectangle roundRectangle){
        super(roundRectangle.getStartPoint().getX(), roundRectangle.getEndPoint().getY(),
                roundRectangle.getEndPoint().getX() - roundRectangle.getStartPoint().getY(),
                roundRectangle.getEndPoint().getY() - roundRectangle.getStartPoint().getY(),
                roundRectangle.getArcWidth(), roundRectangle.getArcHeight());
        this.attributes = roundRectangle.getAttributes();
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
     * Obtains the properties of the round rectangle.
     * @return an gAttribute with the properties of the round rectangle.
     */
    public GAttribute getAttributes() {
        return attributes;
    }
    
    /**
     * Obtains the start point of the round rectangle.
     * 
     * @return the left superior corner of the round rectangle.
     */
    public Point2D getStartPoint(){
        Point2D startPoint = new Point2D.Double(this.getMinX(),this.getMaxY());
        return startPoint;
    }
    
    /**
     * Obtains the end point of the round rectangle.
     * 
     * @return the right inferior corner of the round rectangle.
     */
    public Point2D getEndPoint() {
        Point2D endPoint = new Point2D.Double(this.getMaxX(),this.getMinY());
        return endPoint;
    }

    /**
     * Stablish a new start point of the round rectangle (move the round rectangle).
     * 
     * @param startPoint new origin point of the round rectanble.
     */
    public void setShapePosition(Point2D startPoint) {
       setFrame(startPoint.getX(), startPoint.getY(), getWidth(), getHeight());       
    }
}
