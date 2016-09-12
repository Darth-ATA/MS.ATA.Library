/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import static sm.ata.shapes.GAttribute.FILL_GRADIENT;
import static sm.ata.shapes.GAttribute.FILL_OFF;
import static sm.ata.shapes.GAttribute.FILL_ON;

/**
 * This class provides of the methods needed for draw an filled shape.
 * @author Darth-ATA
 */
public abstract class GFilledShape extends GShape {
    /**
     * Obtains the corners of the rectangle, used for move the filled shape.
     * @param index of the corner (0 for left top and 1 for right bot)
     * @return the corner desired.
     */
    public Point2D getBoundPoint(int index) {
        Point2D point;
        if(index == 0)
            point = shape.getBounds().getLocation();
        else
            point = new Point2D.Double(shape.getBounds().x + shape.getBounds().width,
                shape.getBounds().y + shape.getBounds().height);
        return point;
    }
    
    /**
     * Creates the gradient with the setted colors.
     * @param p1 start point of the gradient.
     * @param p2 end point of the gradient.
     */
    public void setGradient(Point2D p1, Point2D p2){
        switch(this.attributes.getGradientType()){
            case 0:
                this.gradient = new GradientPaint(new Point((int) p1.getX(),0), this.attributes.getColor(), 
                        new Point((int) p2.getX(),0), this.attributes.getGradientColor());
                break;
            case 1:
                this.gradient = new GradientPaint(new Point(0,(int) p1.getY()), this.attributes.getColor(), 
                        new Point(0,(int) p2.getY()), this.attributes.getGradientColor());
                break;
            case 2:
                this.gradient = new GradientPaint(p1, this.attributes.getColor(), 
                        p2, this.attributes.getGradientColor());
                break;
            default:
                this.gradient = null;
                break;
        }
    }
    
    /**
     * Draws the filled shape in a Graphics.
     * @param g2d desired.
     */
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
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
        
        g2d.setRenderingHints(render);
        
        if (this.attributes.getFillMode() == FILL_ON){
            g2d.fill(this.shape);
        }
        else if (this.attributes.getFillMode() == FILL_GRADIENT){
            this.setGradient(this.getBoundPoint(0), this.getBoundPoint(1));
            g2d.setPaint(this.gradient);
            g2d.fill(this.shape);
        }
    }
}
