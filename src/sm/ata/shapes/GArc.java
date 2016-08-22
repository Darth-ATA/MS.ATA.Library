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
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

/**
 * This class has all the attributes and methods needed for draw a rectangle.
 * 
 * @author Darth-ATA
 */
public class GArc extends Arc2D.Double implements GShape {

    private GAttribute attributes;
    
    /**
     * Default constructor of the arc.
     */
    public GArc(){
        attributes = new GAttribute();
    }
    
    /**
     * Constructor of the arc, stablishes a default arc angle and extent.
     * 
     * @param startPoint origin point of the arc.
     * @param endPoint end point of the arc.
     */
    public GArc(Point2D startPoint, Point2D endPoint){
        super(startPoint.getX(), startPoint.getY(),  
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY(), 
                50, 50, Arc2D.OPEN);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the arc.
     * @param startPoint origin of the arc.
     * @param endPoint end point of the arc.
     * @param controlPoint point that gives the angle and the extent.
     */
    public GArc(Point2D startPoint, Point2D endPoint, Point2D controlPoint){
        super(startPoint.getX(), startPoint.getY(),  
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY(), 
                50, 50, Arc2D.OPEN);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the arc, stablishes a default arc angle and extent.
     * 
     * @param startPoint origin point of the arc.
     * @param endPoint end point of the arc.
     * @param attributes properties of the arc.
     */
    public GArc(Point2D startPoint, Point2D endPoint, GAttribute attributes){
        super(startPoint.getX(), startPoint.getY(),  
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY(), 
                50, 50, Arc2D.OPEN);
        this.attributes = attributes;
    }
    
    /**
     * Constructor of the arc.
     * @param startPoint origin of the arc.
     * @param endPoint end point of the arc.
     * @param controlPoint point that gives the angle and the extent.
     * @param attributes properties of the arc.
     */
    public GArc(Point2D startPoint, Point2D endPoint, Point2D controlPoint, GAttribute attributes){
        super(startPoint.getX(), startPoint.getY(),  
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY(), 
                50, 50, Arc2D.OPEN);
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the arc.
     * @param arc to be like.
     */
    public GArc(GArc arc){
        super(arc.getStartPoint().getX(), arc.getStartPoint().getY(),
                arc.getEndPoint().getX() - arc.getStartPoint().getX(),
                arc.getEndPoint().getY() - arc.getStartPoint().getY(),
                arc.start, arc.extent, Arc2D.OPEN);
        this.attributes = arc.getAttributes();
    }
    
    /**
     * Creates and return a copy of the arc.
     * @return the copy of the arc.
     */
    @Override
    public GArc clone(){
        return new GArc(this);
    }
    
    /**
     * Obtains the properties of the arc.
     * @return an gAttribute with the properties of the arc.
     */
    @Override
    public GAttribute getAttributes() {
        return this.attributes;
    }
    
    /**
     * Obtains the start point of the arc.
     * 
     * @return the left superior corner of the arc.
     */
    @Override
    public Point2D getStartPoint(){
        Point2D startPoint = new Point2D.Double(this.getMinX(),this.getMaxY());
        return startPoint;
    }
    
    /**
     * Obtains the end point of the arc.
     * 
     * @return the right inferior corner of the arc.
     */
    @Override
    public Point2D getEndPoint() {
        Point2D endPoint = new Point2D.Double(this.getMaxX(),this.getMinY());
        return endPoint;
    }

    /**
     * Moves the arc to another point.
     * 
     * @param pos the reference point for the traslation of the arc.
     */
    @Override
    public void moveShape(Point2D pos) {
        GArc arc = this;
        Dimension dim = new Dimension((int) arc.getWidth(), (int) arc.getHeight());
        arc.setFrame(pos, dim);
    }

    /**
     * Draws the arc in the desired Graphics2D
     * @param g2d Graphics2D where we want to draw the arc
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
        
        g2d.setRenderingHints(render);
        
        if(this.attributes.getFillMode() == 0){
            g2d.draw(this);
        }
        else if (this.attributes.getFillMode() == 1){
            g2d.fill(this);
        }
    }

    /**
     * Updates the arc (redimensionations).
     * @param startPoint new start point of the arc.
     * @param endPoint new end point of the arc.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        this.setFrameFromDiagonal(startPoint, endPoint);
    }

    /**
     * Obtains the corners of the arc, used for move the arc.
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
