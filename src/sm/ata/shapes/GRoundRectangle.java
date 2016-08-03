/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Darth-ATA
 */
public class GRoundRectangle extends RoundRectangle2D.Double implements GShape {

    //private GAttributes attributes;
    
    /**
     * Constructor of the round rectangle shape.
     * 
     * @param startPoint origin point of the shape.
     * @param endPoint end point of the shape.
     * @param arcw width of the round arc
     * @param arch height of the round arc
     */
    public GRoundRectangle(Point2D startPoint, Point2D endPoint, 
            double arcw, double arch){
        super(startPoint.getX(), startPoint.getY(), 
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                arcw, arch);
        //attributes = new GAttributes();
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
