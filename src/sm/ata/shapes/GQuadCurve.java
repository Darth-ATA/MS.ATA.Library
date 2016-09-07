/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.geom.QuadCurve2D;
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
 * This class has all the attributes and methods needed for draw a curve.
 * 
 * @author Darth-ATA
 */
public class GQuadCurve extends QuadCurve2D.Double implements GShape {
    private GAttribute attributes;
    private GradientPaint gradient;
    
    /**
     * Default constructor of the curve.
     */
    public GQuadCurve(){
        super();
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the curve.
     * 
     * @param startPoint origin point of the curve.
     * @param controlPoint inflexion point of the curve.
     * @param endPoint end point of the curve.
     */
    public GQuadCurve(Point2D startPoint, Point2D controlPoint, Point2D endPoint){
        super((float) startPoint.getX(),(float) startPoint.getY(), 
                (float) controlPoint.getX(),(float) controlPoint.getY(), 
                (float) endPoint.getX(), (float) endPoint.getY());
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the curve..
     * 
     * @param startPoint origin point of the curve.
     * @param controlPoint inflexion point of the curve.
     * @param endPoint end point of the curve.
     * @param attributes properties of the curve.
     */
    public GQuadCurve(Point2D startPoint, Point2D controlPoint, Point2D endPoint,
            GAttribute attributes){
        super((float) startPoint.getX(),(float) startPoint.getY(), 
                (float) controlPoint.getX(),(float) controlPoint.getY(), 
                (float) endPoint.getX(), (float) endPoint.getY());
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the curve.
     * @param curve to be like.
     */
    public GQuadCurve(GQuadCurve curve){
        super(curve.x1, curve.x2, curve.ctrlx, curve.ctrly, curve.x2, curve.y2);
        this.attributes = curve.getAttributes();
    }
    
    /**
     * Creates and return a copy of the curve.
     * 
     * @return the copy of the curve.
     */
    public GQuadCurve clone(){
        return new GQuadCurve(this);
    }

    /**
     * Obtains the properties of the curve.
     * @return a GAttribute with the properties of the curve. 
     */
    @Override
    public GAttribute getAttributes() {
        return this.attributes;
    }

    /**
     * Obtains the start point of the curve.
     * 
     * @return the right point of the curve.
     */
    @Override
    public Point2D getStartPoint() {
        Point2D point = new Point2D.Double(this.getX1(), this.getY1());
        return point;
    }

    /**
     * Obtains the end point of the curve.
     * 
     * @return the left point of the curve.
     */
    @Override
    public Point2D getEndPoint() {
        Point2D point = new Point2D.Double(this.getX2(), this.getY2());
        return point;
    }
    
    /**
     * Obtains the control point of the curve.
     * 
     * @return the inflexion point of the curve.
     */
    public Point2D getControlPoint(){
        Point2D point = new Point2D.Double(this.getCtrlX(), this.getCtrlY());
        return point;
    }

    /**
     * Moves the curve to another point.
     * 
     * @param pos the reference point for the traslation of the curve.
     */
    @Override
    public void moveShape(Point2D pos) {
        double distX = pos.getX() - this.getX1();
        double distY = pos.getY() - this.getY1();
        Point2D pointC = new Point2D.Double(this.getCtrlX() + distX, this.getCtrlY() + distY);
        Point2D pointE = new Point2D.Double(this.getX2() + distX, this.getY2() + distY);
        this.setCurve(pos, pointC, pointE);
        if(this.attributes.getFillMode() == FILL_GRADIENT){
            this.setGradient(getInterestPoint(0), getInterestPoint(1));
        }
    }

    /**
     * Draws the curve in the desired Graphic2D.
     * 
     * @param g2d Graphic2D where we want to draw the curve.
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
     * Update the curve (redimensionation)
     * @param startPoint new start point of the line.
     * @param endPoint new end point of the line.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint){
        this.setCurve(startPoint, this.getControlPoint(), endPoint);
        if(this.attributes.getFillMode()== FILL_GRADIENT){
            this.setGradient(this.getInterestPoint(0), this.getInterestPoint(1));
        }
    }

    /**
     * Update the curve (redimensionation)
     * @param startPoint new start point of the line.
     * @param controlPoint new control point of the line.
     * @param endPoint new end point of the line.
     */
    public void updateShape(Point2D startPoint, Point2D controlPoint, Point2D endPoint) {
        this.setCurve(startPoint, controlPoint, endPoint);
        if(this.attributes.getFillMode()== FILL_GRADIENT){
            this.setGradient(this.getInterestPoint(0), this.getInterestPoint(1));
        }
    }

    /**
     * Obtains the start, control or end point of the curve.
     * @param index of the point.
     * @return the point desired.
     */
    @Override
    public Point2D getInterestPoint(int index) {
        Point2D point;
        switch (index) {
            case 0:
                point = this.getStartPoint();
                break;
            case 1:
                point = this.getEndPoint();
                break;
            default:
                point = this.getControlPoint();
                break;
        }
        
        return point;
    }

    /**
     * Stablish the attributes of the line.
     * @param attributes wanted for the curve.
     */
    @Override
    public void setAttributes(GAttribute attributes) {
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
    public void setGradient(Point2D p1, Point2D p2) {
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
        this.gradient = null;
    }    
}
