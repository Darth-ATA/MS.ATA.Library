/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import static sm.ata.shapes.GAttribute.FILL_OFF;
import static sm.ata.shapes.GAttribute.FILL_ON;

/**
 * This class has all the attributes and methods needed for draw a rectangle
 * with round corners.
 * 
 * @author Darth-ATA
 */
public class GRoundRectangle extends RoundRectangle2D.Double implements GShape {
   
    private GAttribute attributes;
    private GradientPaint gradient;
    
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
        
        if(this.attributes.getFillMode() == FILL_OFF){
            g2d.draw(this);
        }
        else if (this.attributes.getFillMode() == FILL_ON){
            g2d.fill(this);
        }
        else {
            this.setGradient(this.getInterestPoint(0), this.getInterestPoint(1));
            g2d.setPaint(this.gradient);
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

    /**
     * Stablish the attributes of the round rectangle.
     * @param attributes wanted for the round rectangle.
     */
    @Override
    public void setAttributes(GAttribute attributes){
        this.attributes = new GAttribute(attributes);
    }
    
    /**
     * Creates the gradient with the setted colors.
     * @param p1 start point of the gradient.
     * @param p2 end point of the gradient.
     */
    @Override
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
            default:
                this.gradient = new GradientPaint(p1, this.attributes.getColor(), 
                        p2, this.attributes.getGradientColor());
                break;
        }
    }
}
