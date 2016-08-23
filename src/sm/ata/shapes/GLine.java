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
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import static sm.ata.shapes.GAttribute.FILL_GRADIENT;
import static sm.ata.shapes.GAttribute.FILL_OFF;
import static sm.ata.shapes.GAttribute.FILL_ON;

/**
 * This class has all the attributes and methods needed for draw a ellipse.
 * 
 * @author Darth-ATA
 */
public class GLine extends Line2D.Double implements GShape {
    private GAttribute attributes;
    private GradientPaint gradient;
    
    /**
     * Default constructor of the line.
     */
    public GLine(){
        this.attributes = new GAttribute();
    };
    
    /**
     * Constructor of the line.
     * 
     * @param startPoint origin point of the line.
     * @param endPoint end point of the line.
     */
    public GLine(Point2D startPoint, Point2D endPoint){
        super(startPoint, endPoint);
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the line.
     * 
     * @param startPoint origin point of the line.
     * @param endPoint end point of the line.
     * @param attributes properties of the line.
     */
    public GLine(Point2D startPoint, Point2D endPoint, GAttribute attributes){
        super(startPoint, endPoint);
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the line.
     * @param line to be like.
     */
    public GLine(GLine line){
        super(line.getStartPoint(), line.getEndPoint());
        this.attributes = line.getAttributes();
    }
    
    /**
     * Creates and return a copy of the line.
     * @return the copy of the line.
     */
    @Override
    public GLine clone(){
        return new GLine(this);
    }
    
    /**
     * Obtains the properties of the line.
     * @return an gAttribute with the properties of the line.
     */
    @Override
    public GAttribute getAttributes() {
        return this.attributes;
    }
    
    /**
     * Obtains the start point of the line.
     * 
     * @return the left superior corner of the line.
     */
    @Override
    public Point2D getStartPoint(){
        Point2D startPoint = this.getP1();
        return startPoint;
    }
    
    /**
     * Obtains the end point of the line.
     * 
     * @return the right inferior corner of the line.
     */
    @Override
    public Point2D getEndPoint() {
        Point2D endPoint = this.getP2();
        return endPoint;
    }
    
    /**
     * Provides if a point its in the line, needed for have a larger field to 
     * click.
     * @param point to check if it in the line.
     * @return true or false depending on the results.
     */
    public boolean contains(Point2D point){
        return this.ptLineDist(point) <= 4.0;
    }

    /**
     * Moves the line to another point.
     * 
     * @param pos the reference point for the traslation of the line.
     */
    @Override
    public void moveShape(Point2D pos) {
        double distX = pos.getX() - this.getX1();
        double distY = pos.getY() - this.getY1();
        Point2D point = new Point2D.Double(this.getX2() + distX, this.getY2() + distY);
        this.setLine(pos, point);
        if(this.attributes.getFillMode() == FILL_GRADIENT){
            this.setGradient(getInterestPoint(0), getInterestPoint(1));
        }
    }    

    /**
     * Draws the line in the desired Graphics2D
     * @param g2d Graphics2D where we want to draw the line
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
        
        /*
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
        }*/
        g2d.draw(this);
    }

    /**
     * Updates the line (redimensionations).
     * @param startPoint new start point of the line.
     * @param endPoint new end point of the line.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        this.setLine(startPoint, endPoint);
        if(this.attributes.getFillMode()== FILL_GRADIENT){
            this.setGradient(this.getInterestPoint(0), this.getInterestPoint(1));
        }
    }
    
    /**
     * Obtains the corners of the line, used for move the line.
     * @param index of the corner
     * @return the corner desired.
     */
    @Override
    public Point2D getInterestPoint(int index) {
        Point2D point;
        if (index == 0){
            point = this.getP1();
        }
        else{
            point = this.getP2();
        }
        return point;
    }
    
    /**
     * Stablish the attributes of the line.
     * @param attributes wanted for the line.
     */
    @Override
    public void setAttributes(GAttribute attributes){
        this.attributes = new GAttribute(attributes);
        if(this.attributes.getFillMode() == FILL_GRADIENT){
            this.setGradient(this.getInterestPoint(0), this.getInterestPoint(1));
        }
    }
    
    /**
     * Creates the gradient with the setted colors.
     * @param p1 start point of the gradient.
     * @param p2 end point of the gradient.
     */
    @Override
    public void setGradient(Point2D p1, Point2D p2){
        /*switch(this.attributes.getGradientType()){
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
        }*/
        this.gradient = null;
    }
}
