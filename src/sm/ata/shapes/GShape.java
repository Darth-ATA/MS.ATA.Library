/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Point;

/**
 * This interface stablish the minimum functionalaties that all our shapes
 * must have.
 * 
 * @author Darth-ATA
 */
public interface GShape {
    /**
     * For not filled shapes
     */
    public final static int NOT_FILLED = 0;
    
    /**
     * For filled shapes
     */
    public final static int FILLED = 1;
    
    /**
     * Obtains all the properties of the shape
     * 
     * @return an gAttribute with the attributes of the shapè
     */
    //public GAttribute getAttributes();
    
    /**
     * Obtains the width of the shape
     * 
     * @return the width of the shape
     */
    public double getWidth();
    
    /**
     * Obtains the heigth of the shape
     * 
     * @return the height of the shape
     */
    public double getHeight();
    
    /**
     * Obtains the start position of the shape
     * 
     * @return the point of the left superior corner of the shape
     */
    public Point getStartPosition();
    
    /**
     * Obtains the end position of the shape
     * 
     * @return the point of the right inferior corner of the shape
     */
    public Point getEndPosition();
    
    /**
     * Stablish the start point and the end point of the shape
     * 
     * @param startPoint origin point of the shape
     * @param endPoint end point of the shape
     */
    public void setShapeCoordinates(Point startPoint, Point endPoint);   
}
