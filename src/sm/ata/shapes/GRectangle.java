/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class have all the attributes and methods needed for draw an rectangle.
 *
 * @author Darth-ATA
 */
public class GRectangle extends Rectangle2D.Double implements GShape {    
    
    //private GAttributes attributes;
    
    /**
     * Constructor of the rectangle shape.
     * 
     * @param startPoint origin point of the shape.
     * @param endPoint end point of the shape.
     */
    public GRectangle(Point2D startPoint, Point2D endPoint){
        super(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        //attributes = new GAttributes();
    }
    /**
     * Obtains the start point of the rectangle.
     * 
     * @return the left superior corner of the rectangle.
     */
    public Point2D getStartPoint(){
        Point2D startPoint = new Point2D.Double(this.getMinX(),this.getMaxY());
        return startPoint;
    }
    
    /**
     * Obtains the end point of the rectangle.
     * 
     * @return the right inferior corner of the rectangle.
     */
    public Point2D getEndPoint() {
        Point2D endPoint = new Point2D.Double(this.getMaxX(),this.getMinY());
        return endPoint;
    }

    /**
     * Stablish a new start point of the rectangle (move the rectangle).
     * 
     * @param startPoint new origin point of the rectanble.
     */
    public void setShapePosition(Point2D startPoint) {
       setFrame(startPoint.getX(), startPoint.getY(), getWidth(), getHeight());       
    }    
}
