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
    
    private Point2D startPoint;
    private Point2D endPoint;
    
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
        this.startPoint = startPoint;
        this.endPoint = endPoint;
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
        return this.startPoint;
    }
    
    /**
     * Obtains the end point of the ellipse.
     * 
     * @return the right inferior corner of the ellipse.
     */
    @Override
    public Point2D getEndPoint() {
        return this.endPoint;
    }

    /**
     * Moves the ellipse to another point.
     * 
     * @param point new point of the ellipse.
     */
    @Override
    public void moveShape(Point2D point) {
        /*Rectangle auxRectangle = this.getBounds();
        Point point = new Point((int) endPoint.getX() + ((int) this.startPoint.getX() + (int) startPoint.getX()), (int) endPoint.getY() + ((int) this.startPoint.getY() + (int) startPoint.getY()));
        auxRectangle.setLocation(point);    */
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
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
}
