/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class has all the attributes and methods needed for draw a point.
 * @author DarthATA
 */
public class GPoint extends GShape{    
    /**
     * Default constructor of the point.
     */
    public GPoint(){
        this.attributes = new GAttribute();
    };
    
    /**
     * Constructor of the point.
     * 
     * @param startPoint origin point of the point.
     */
    public GPoint(Point2D startPoint){
        this.shape = new Rectangle2D.Double(startPoint.getX(),startPoint.getY(),
                startPoint.getX() - startPoint.getX(),
                startPoint.getY() - startPoint.getY());
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the point.
     * 
     * @param startPoint origin point of the point.
     * @param attributes properties of the point.
     */
    public GPoint(Point2D startPoint, GAttribute attributes){
        this.shape = new Rectangle2D.Double(startPoint.getX(), startPoint.getY(),
                startPoint.getX() - startPoint.getX(),
                startPoint.getY() - startPoint.getY());
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the point.
     * @param point to be like.
     */
    public GPoint(GPoint point){
        this.shape = new Rectangle2D.Double(point.getStartPoint().getX(), point.getEndPoint().getY(),
                point.getEndPoint().getX() - point.getStartPoint().getY(),
                point.getEndPoint().getY() - point.getStartPoint().getY());
        this.attributes = point.getAttributes();
    }
    
    /**
     * Creates and return a copy of the point.
     * @return the copy of the point.
     */
    @Override
    public GPoint clone(){
        return new GPoint(this);
    }

    /**
     * Moves the point to another point.
     * 
     * @param pos the reference point for the traslation of the point.
     */
    @Override
    public void moveShape(Point2D pos) {
        Rectangle2D point = (Rectangle2D)this.shape;
        Dimension dim = new Dimension((int) point.getWidth(), (int) point.getHeight());
        point.setFrame(pos, dim);
    }
    
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        ((Rectangle2D)this.shape).setFrameFromDiagonal(startPoint, startPoint);
    }
    
    /**
     * Informs if a point is close to the point..
     * @param point the point that wants to know if is close the shape.
     * @return true or flase depending on the situation.
     */
    @Override
    public boolean contains(Point2D point){
        Point2D aux = new Point2D.Double(this.getStartPoint().getX(), this.getStartPoint().getY());
        return aux.distance(point) <= 9.0 / 2;  //+border_width
    }

    /**
     * Provide the start point of the point. 
     * @return left top corner of the point.
     */
    @Override
    public Point2D getStartPoint() {
        Rectangle2D point = (Rectangle2D)this.shape;
        Point2D p = new Point2D.Double(point.getMinX(), point.getMaxY());
        return p;
    }

    /**
     * Provide the end point of the point. 
     * @return right bot corner of the point.
     */
    @Override
    public Point2D getEndPoint() {
        Rectangle2D point = (Rectangle2D)this.shape;
        Point2D p = new Point2D.Double(point.getMaxX(), point.getMinY());
        return p;
    }
    
    @Override
    public Point2D getInterestPoint(int index) {
        Point2D point;
        if (index == 0){
            point = getStartPoint();
        }
        else {
            point = getEndPoint();
        }
        return point;
    }
}
