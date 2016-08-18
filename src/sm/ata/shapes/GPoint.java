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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class have all the attributes and methods needed for draw a point.
 * 
 * @author DarthATA
 */
public class GPoint extends Rectangle2D.Double implements GShape {
    private GAttribute attributes;
    
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
        super(startPoint.getX(),startPoint.getY(),
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
        super(startPoint.getX(), startPoint.getY(),
                startPoint.getX() - startPoint.getX(),
                startPoint.getY() - startPoint.getY());
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the point.
     * @param point to be like.
     */
    public GPoint(GPoint point){
        super(point.getStartPoint().getX(), point.getEndPoint().getY(),
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
     * Obtains the properties of the point.
     * @return an gAttribute with the properties of the point.
     */
    @Override
    public GAttribute getAttributes() {
        return this.attributes;
    }
    
    /**
     * Obtains the start point of the point.
     * 
     * @return the left superior corner of the point.
     */
    @Override
    public Point2D getStartPoint(){
        Point2D startPoint = new Point2D.Double(this.getMinX(),this.getMaxY());
        return startPoint;
    }
    
    /**
     * Obtains the end point of the point.
     * 
     * @return the right inferior corner of the point.
     */
    @Override
    public Point2D getEndPoint() {
        Point2D endPoint = new Point2D.Double(this.getMaxX(),this.getMinY());
        return endPoint;
    }

    /**
     * Moves the point to another point.
     * 
     * @param pos the reference point for the traslation of the point.
     */
    @Override
    public void moveShape(Point2D pos) {
        GPoint point = this;
        Dimension dim = new Dimension((int) point.getWidth(), (int) point.getHeight());
        point.setFrame(pos, dim);
    }    

    /**
     * Draws the point in the desired Graphics2D
     * @param g2d Graphics2D where we want to draw the point
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
     * Updates the point (redimensionations).
     * @param startPoint new start point of the point.
     * @param endPoint new end point of the point.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        this.setFrameFromDiagonal(endPoint, endPoint);
    }
    
    /**
     * Obtains the corners of the point, used for move the point.
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
     * Informs if a point is close to the point..
     * @param point the point that wants to know if is close the shape.
     * @return true or flase depending on the situation.
     */
    @Override
    public boolean contains(Point2D point){
        Point2D aux = new Point2D.Double(this.getStartPoint().getX(), this.getStartPoint().getY());
        return aux.distance(point) <= 9.0 / 2;  //+border_width
    }
}
