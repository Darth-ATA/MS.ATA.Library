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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * This class have all the attributes and methods needed for draw a rectangle
 * with round corners.
 * 
 * @author Darth-ATA
 */
public class GRoundRectangle extends RoundRectangle2D.Double implements GShape {
   
    private GAttribute attributes;
    
    /**
     * Default constructor of the round rectangle.
     */
    public GRoundRectangle(){
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the round rectangle.
     * 
     * @param startPoint origin point of the round rectangle.
     * @param endPoint end point of the round rectangle.
     * @param arcw width of the round arc
     * @param arch height of the round arc
     */
    public GRoundRectangle(Point2D startPoint, Point2D endPoint, 
            double arcw, double arch){
        super(startPoint.getX(), startPoint.getY(), 
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                arcw, arch);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the round rectangle.
     * 
     * @param startPoint origin point of the round rectangle.
     * @param endPoint end point of the round rectangle.
     * @param arcw width of the round arc.
     * @param arch height of the round arc.
     * @param attributes properties of the round rectangle.
     */
    public GRoundRectangle(Point2D startPoint, Point2D endPoint, 
            double arcw, double arch,
            GAttribute attributes){
        super(startPoint.getX(), startPoint.getY(), 
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                arcw, arch);
        this.attributes = attributes;
    }
    
    /**
     * Copy construcor of the round rectangle.
     * @param roundRectangle to be like.
     */
    public GRoundRectangle(GRoundRectangle roundRectangle){
        super(roundRectangle.getStartPoint().getX(), roundRectangle.getEndPoint().getY(),
                roundRectangle.getEndPoint().getX() - roundRectangle.getStartPoint().getY(),
                roundRectangle.getEndPoint().getY() - roundRectangle.getStartPoint().getY(),
                roundRectangle.getArcWidth(), roundRectangle.getArcHeight());
        this.attributes = roundRectangle.getAttributes();
    }
    
    /**
     * Creates and return a copy of the round rectangle.
     * @return the copy of the round rectangle.
     */
    @Override
    public GRoundRectangle clone(){
        return new GRoundRectangle(this);
    }
    
    /**
     * Obtains the properties of the round rectangle.
     * @return an gAttribute with the properties of the round rectangle.
     */
    @Override
    public GAttribute getAttributes() {
        return this.attributes;
    }
    
    /**
     * Obtains the start point of the round rectangle.
     * 
     * @return the left superior corner of the round rectangle.
     */
    @Override
    public Point2D getStartPoint(){
        Point2D startPoint = new Point2D.Double(this.getMinX(),this.getMaxY());
        return startPoint;
    }
    
    
    /**
     * Obtains the end point of the round rectangle.
     * 
     * @return the right inferior corner of the round rectangle.
     */
    @Override
    public Point2D getEndPoint() {
        Point2D endPoint = new Point2D.Double(this.getMaxX(),this.getMinY());
        return endPoint;
    }

    /**
     * Moves the round rectangle to another point.
     * 
     * @param pos the reference point for the traslation of the rectangle.
     */
    @Override
    public void moveShape(Point2D pos) {
        Dimension2D dim = new Dimension();
        double distX;
        double distY;
        Point2D newPoint;
        
        dim.setSize(this.getWidth(),this.getHeight());
        
        distX = pos.getX() - this.getMinX();
        distY = pos.getY() - this.getMinY();
        
        newPoint = new Point2D.Double(distX, distY);
        this.setFrame(newPoint, dim);
    }    
    
    /**
     * Draws the round rectangle in the desired Graphics2D
     * @param g2d Graphics2D where we want to draw the round rectangle
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
     * Updates the round rectangle (redimensionations).
     * @param startPoint new start point of the round rectangle.
     * @param endPoint new end point of the round rectangle.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        this.setFrameFromDiagonal(startPoint, endPoint);
    }

    /**
     * Obtains the corners of the round rectangle, used for move the round rectangle.
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
