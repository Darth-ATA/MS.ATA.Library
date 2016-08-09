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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * This class have all the attributes and methods needed for draw an ellipse.
 * 
 * @author Darth-ATA
 */
public class GEllipse extends Ellipse2D.Double implements GShape {
    
     private GAttribute attributes;
    
    /**
     * Constructor of the ellipse.
     * 
     * @param startPoint origin point of the ellipse.
     * @param endPoint end point of the ellipse.
     */
    public GEllipse(Point2D startPoint, Point2D endPoint){
        super(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        //attributes = new GAttributes();
    }
    
    /**
     * Obtains the properties of the ellipse.
     * @return the properties of the ellipse.
     */
    public GAttribute getAttributes() {
        return this.attributes;
    }
    
    /**
     * Obtains the start point of the ellipse.
     * 
     * @return the left superior corner of the ellipse.
     */
    public Point2D getStartPoint(){
        Point2D startPoint = new Point2D.Double(this.getMinX(),this.getMaxY());
        return startPoint;
    }
    
    /**
     * Obtains the end point of the ellipse.
     * 
     * @return the right inferior corner of the ellipse.
     */
    public Point2D getEndPoint() {
        Point2D endPoint = new Point2D.Double(this.getMaxX(),this.getMinY());
        return endPoint;
    }

    /**
     * Stablish a new start point of the ellipse (move the ellipse).
     * 
     * @param startPoint new origin point of the ellipse.
     */
    public void setShapePosition(Point2D startPoint) {
       setFrame(startPoint.getX(), startPoint.getY(), getWidth(), getHeight());       
    }        
    
    
    /**
     * Draws the ellipse in the desired Graphics2D
     * @param g2d Graphics2D where we want to draw the ellipse
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
