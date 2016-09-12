/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.geom.QuadCurve2D;
import java.awt.geom.Point2D;

/**
 * This class has all the attributes and methods needed for draw a curve.
 * @author Darth-ATA
 */
public class GQuadCurve extends GFilledShape{
    /**
     * Default constructor of the curve.
     */
    public GQuadCurve(){
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
        this.shape = new QuadCurve2D.Double(startPoint.getX(), startPoint.getY(), 
                controlPoint.getX(), controlPoint.getY(), 
                endPoint.getX(), endPoint.getY());
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
        this.shape = new QuadCurve2D.Double(startPoint.getX(), startPoint.getY(), 
                controlPoint.getX(), controlPoint.getY(), 
                endPoint.getX(), endPoint.getY());
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the curve.
     * @param curve to be like.
     */
    public GQuadCurve(GQuadCurve curve){
        this.shape = new QuadCurve2D.Double(curve.getStartPoint().getX(), curve.getStartPoint().getY(), 
                curve.getControlPoint().getX(), curve.getControlPoint().getY(), 
                curve.getEndPoint().getX(), curve.getEndPoint().getY());
        this.attributes = curve.getAttributes();
    }
    
    /**
     * Obtains the start point of the curve.
     * 
     * @return the right point of the curve.
     */
    @Override
    public Point2D getStartPoint() {
        Point2D point = new Point2D.Double(((QuadCurve2D) this.shape).getX1(), ((QuadCurve2D) this.shape).getY1());
        return point;
    }

    /**
     * Obtains the end point of the curve.
     * 
     * @return the left point of the curve.
     */
    @Override
    public Point2D getEndPoint() {
        Point2D point = new Point2D.Double(((QuadCurve2D) this.shape).getX2(), ((QuadCurve2D) this.shape).getY2());
        return point;
    }
    
    /**
     * Obtains the control point of the curve.
     * 
     * @return the inflexion point of the curve.
     */
    public Point2D getControlPoint(){
        Point2D point = new Point2D.Double(((QuadCurve2D) this.shape).getCtrlX(), ((QuadCurve2D) this.shape).getCtrlY());
        return point;
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
     * Moves the curve to another point.
     * 
     * @param pos the reference point for the traslation of the curve.
     */
    @Override
    public void moveShape(Point2D pos) {
        double distX = pos.getX() - ((QuadCurve2D)this.shape).getX1();
        double distY = pos.getY() - ((QuadCurve2D)this.shape).getY1();
        Point2D pointC = new Point2D.Double(((QuadCurve2D)this.shape).getCtrlX() + distX, ((QuadCurve2D)this.shape).getCtrlY() + distY);
        Point2D pointE = new Point2D.Double(((QuadCurve2D)this.shape).getX2() + distX, ((QuadCurve2D)this.shape).getY2() + distY);
        ((QuadCurve2D)this.shape).setCurve(pos, pointC, pointE);
    }
    
    /**
     * Update the curve (redimensionation)
     * @param startPoint new start point of the line.
     * @param endPoint new end point of the line.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint){
        ((QuadCurve2D)this.shape).setCurve(startPoint, endPoint, endPoint);
    }

    /**
     * Update the position of control point (redimensionation)
     * @param controlPoint new control point of the line.
     */
    public void updateControlPoint(Point2D controlPoint) {
        ((QuadCurve2D)this.shape).setCurve(this.getStartPoint(), controlPoint, this.getEndPoint());
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
}
