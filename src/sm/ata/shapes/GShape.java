/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
     * @return an gAttribute with the attributes of the shape.
     */
    public GAttribute getAttributes();
    
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
     * Moves the shape to another point.
     * 
     * @param startPoint
     * @param endPoint
     */
    public void moveShape(Point startPoint, Point endPoint);
    
    /**
     * Draw the shape in the desired Graphics2D.
     * @param g2d Graphics2D where we want to draw the shape.
     */
    public void draw(Graphics2D g2d);
    
    /**
     * Updates the shape (redimensionations).
     * @param startPoint new start point of the shape.
     * @param endPoint new end point of the shape.
     */
    public void updateShape(Point2D startPoint, Point2D endPoint);
    
    /**
     * Obtains the Rectangle that has the shape.
     * @return Rectnagle object that has the shape.
     */
    public Rectangle getBounds();
    
    /**
     * Informs if the a point is inside of the shape.
     * @param point the point that wants to know if is in the shape.
     * @return true or flase depending on the situation.
     */
    public boolean contains(Point2D point);
    
}
