/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
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
     * First corner of the shape
     */
    public final static int FIRST_CORNER = 0;
    /**
     * Second corner of the shape
     */
    public final static int SECOND_CORNER = 1;
    
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
     * @param pos
     */
    public void moveShape(Point2D pos);
    
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
     * Informs if the point is inside of the shape.
     * @param point the point that wants to know if is in the shape.
     * @return true or flase depending on the situation.
     */
    public boolean contains(Point2D point);
    
    /**
     * Obtains some interesting points of the shape.
     * @param index what point it wants to take
     * @return the interest point.
     */
    public Point2D getInterestPoint(int index);
    
    /**
     * Stablish the attributes of the shape.
     * @param attributes wanted for the shape.
     */
    public void setAttributes(GAttribute attributes);
    
    /**
     * Creates the gradient of the shape.
     * @param p1 start of the gradient.
     * @param p2 end of the gradient.
     */
    public void setGradient(Point2D p1, Point2D p2);
}
