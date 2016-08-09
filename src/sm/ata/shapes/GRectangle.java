/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class have all the attributes and methods needed for draw an rectangle.
 *
 * @author Darth-ATA
 */
public class GRectangle extends Rectangle2D.Double implements GShape {    
    
    private GAttribute attributes;
    
    /**
     * Default constructor of the rectangle.
     */
    public GRectangle(){
        attributes = new GAttribute();
    };
    
    /**
     * Constructor of the rectangle.
     * 
     * @param startPoint origin point of the rectangle.
     * @param endPoint end point of the rectangle.
     */
    public GRectangle(Point2D startPoint, Point2D endPoint){
        super(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        attributes = new GAttribute();
    }
    
    /**
     * Constructor of the rectangle.
     * 
     * @param startPoint origin point of the rectangle.
     * @param endPoint end point of the rectangle.
     * @param attributes properties of the rectangle.
     */
    public GRectangle(Point2D startPoint, Point2D endPoint, GAttribute attributes){
        super(startPoint.getX(), startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the rectangle.
     * @param rectangle to be like.
     */
    public GRectangle(GRectangle rectangle){
        super(rectangle.getStartPoint().getX(), rectangle.getEndPoint().getY(),
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
     * Obtains the properties of the rectangle.
     * @return an gAttribute with the properties of the rectangle.
     */
    public GAttribute getAttributes() {
        return attributes;
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

    /**
     * Draws the rectangle in the desired Graphics2D
     * @param g2d Graphics2D where we want to draw the rectangle
     */
    @Override
    public void draw(Graphics2D g2d) {
        
        // Stablish the color
        g2d.setColor(this.attributes.getColor());
        
        // Stablish the border style
        g2d.setStroke(this.attributes.getBorder());
        
        // Stablish the shape's transparency
        Composite composite;
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.attributes.getTransparency());
        g2d.setComposite(composite);
        
        // Stablish the antialiasing mode
        RenderingHints render;
        if(this.attributes.getAntialiasing()){
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        else{
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        
        if(this.attributes.getFillMode() == 0){
            g2d.draw(this);
        }
        else if (this.attributes.getFillMode() == 1){
            g2d.fill(this);
        }
    }
}
