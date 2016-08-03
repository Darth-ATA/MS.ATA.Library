/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.geom.Point2D;


/**
 * This interface stablish the minimum functionalaties that all our shapes
 * must have.
 * 
 * @author Darth-ATA
 */
public interface GShape {
    /**
     * For not filled shapes.
     */
    public final static int NOT_FILLED = 0;
    
    /**
     * For filled shapes.
     */
    public final static int FILLED = 1;
    
    /**
     * Obtains all the properties of the shape.
     * 
     * @return an gAttribute with the attributes of the shapè.
     */
    //public GAttribute getAttributes();
    
    /**
     * Obtains the width of the shape.
     * 
     * @return the width of the shape.
     */
    public double getWidth();
    
    /**
     * Obtains the heigth of the shape.
     * 
     * @return the height of the shape.
     */
    public double getHeight();
    
    /**
     * Obtains the start point of the shape.
     * 
     * @return the point of the left superior corner of the shape.
     */
    public Point2D getStartPoint();
    
    /**
     * Obtains the end point of the shape.
     * 
     * @return the point of the right inferior corner of the shape.
     */
    public Point2D getEndPoint();
    
    /**
     * Stablish a new start point of the shape (move the shape).
     * 
     * @param startPoint new origin point of the shape.
     */
    public void setShapePosition(Point2D startPoint);   
}
