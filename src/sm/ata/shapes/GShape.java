/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import static sm.ata.shapes.GAttribute.S_BROKEN;
import static sm.ata.shapes.GAttribute.S_CONTINUOUS;

/**
 * This abstract class stablish the minimum functionalaties and attributes 
 * that all our shapes must have and implements some shared methods.
 * @author Darth-ATA
 */
public abstract class GShape {
    protected Shape shape;
    protected GAttribute attributes;
    protected GradientPaint gradient;
    
    /**
     * Obtains the start point of the shape.
     * 
     * @return the left superior corner of the shape.
     */
    public abstract Point2D getStartPoint();
    
    /**
     * Obtains the end point of the shape.
     * @return the right inferior corner of the shape.
     */
    public abstract Point2D getEndPoint();
    
    /**
     * Obtains interesting points of the shape.
     * @param index of the shape.
     * @return the point desired.
     */
    public abstract Point2D getInterestPoint(int index);
    
    /**
     * Moves the shape to another point.
     * 
     * @param pos the reference point for the traslation of the shape.
     */
    public abstract void moveShape(Point2D pos);
    
    /**
     * Updates the shape (redimensionations).
     * @param startPoint new start point of the shape.
     * @param endPoint new end point of the shape.
     */
    public abstract void updateShape(Point2D startPoint, Point2D endPoint);
    
    /**
     * Obtains the properties of the shape.
     * @return the properties of the shape.
     */
    public GAttribute getAttributes(){
        return this.attributes;
    }
    
    /**
     * Stablish the attributes of the shape.
     * @param attributes wanted for the shape.
     */
    public void setAttributes(GAttribute attributes){
        this.attributes = new GAttribute(attributes);
    }
    
    /**
     * Stablish the border color of the shape.
     * @param color wanted for the border of the shape.
     */
    public void setBorderColor(Color color){
        this.attributes.setBorderColor(color);
    }
    
    /**
     * Stablihs the border style of the shape.
     * @param borderStyle wanted for the border of the shape
     *      - S_CONTINUOUS: continuous line border.
     *      - S_BROKEN: separate line border.
     */
    public void setBorderType(int borderStyle) {
        switch(borderStyle){
            case S_CONTINUOUS:  this.attributes.setBorderStyle(S_CONTINUOUS);
                                break;
            case S_BROKEN:      this.attributes.setBorderStyle(S_BROKEN);
                                break;
        }
    }
    
    /**
     * Check if the shape contains the provided point.
     * @param point to check.
     * @return true or false depending on the result.
     */
    public boolean contains(Point2D point) {
        return this.shape.contains(point);
    }
    
    /**
     * Draw the shape in the desired Graphics2D.
     * @param g2d where draw the shape.
     */
    public void draw(Graphics2D g2d) {
        // Stablish the color
        g2d.setColor(this.attributes.getBorderColor());
        
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
        
        g2d.setRenderingHints(render);
        g2d.draw(this.shape);
    }  
}
