/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Dimension2D;
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
        this.attributes = new GAttribute();
    }
    
    /**
     * Obtains the properties of the ellipse.
     * @return the properties of the ellipse.
     */
    @Override
    public GAttribute getAttributes() {
        return this.attributes;
    }
    
    /**
     * Obtains the start point of the ellipse.
     * 
     * @return the left superior corner of the ellipse.
     */
    @Override
    public Point2D getStartPoint(){
        Point2D startPoint = new Point2D.Double(this.getMinX(),this.getMaxY());
        return startPoint;
    }
    
    /**
     * Obtains the end point of the ellipse.
     * 
     * @return the right inferior corner of the ellipse.
     */
    @Override
    public Point2D getEndPoint() {
        Point2D endPoint = new Point2D.Double(this.getMaxX(),this.getMinY());
        return endPoint;
    }

    /**
     * Moves the ellipse to another point.
     * 
     * @param pos the reference point for the traslation of the ellipse.
     */
    @Override
    public void moveShape(Point2D pos) {
        GEllipse ellipse = this;
        Dimension dim = new Dimension((int) ellipse.getWidth(), (int) ellipse.getHeight());
        ellipse.setFrame(pos, dim);
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

    /**
     * Updates the ellipse (redimensionations).
     * @param startPoint new start point of the ellipse.
     * @param endPoint new end point of the ellipse.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        this.setFrameFromDiagonal(startPoint, endPoint);
    }
    
    /**
     * Obtains the corners of the ellipse, used for move the ellipse.
     * @param index of the corner (0 for left top and 1 for right bot)
     * @return the corner desired.
     */
    @Override
    public Point2D getInterestPoint(int index) {
        Point2D point;
        if(index == 0)
            point = this.getBounds().getLocation();
        else
            point = new Point2D.Double(this.getBounds().x + this.getBounds().width,
                this.getBounds().y + this.getBounds().height);
        return point;
    }
}
