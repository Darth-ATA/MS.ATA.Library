/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;

/**
 * This class have all the attributes and methods needed for draw an rectangle.
 *
 * @author Darth-ATA
 */
public class GRectangle extends Rectangle2D.Double implements GShape {    
    
    private Point2D startPoint;
    private Point2D endPoint;
    
    private GAttribute attributes;
    
    /**
     * Default constructor of the rectangle.
     */
    public GRectangle(){
        this.attributes = new GAttribute();
    };
    
    /**
     * Constructor of the rectangle.
     * 
     * @param startPoint origin point of the rectangle.
     * @param endPoint end point of the rectangle.
     */
    public GRectangle(Point2D startPoint, Point2D endPoint){
        super(startPoint.getX(),startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.attributes = new GAttribute();
    }
    
    /**
     * Constructor of the rectangle.
     * 
     * @param startPoint origin point of the rectangle.
     * @param endPoint end point of the rectangle.
     * @param attributes properties of the rectangle.
     */
    public GRectangle(Point2D startPoint, Point2D endPoint, GAttribute attributes){
        super(startPoint.getX(), startPoint.getY(),
                endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY());
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.attributes = attributes;
    }
    
    /**
     * Copy constructor of the rectangle.
     * @param rectangle to be like.
     */
    public GRectangle(GRectangle rectangle){
        super(rectangle.getStartPoint().getX(), rectangle.getEndPoint().getY(),
                rectangle.getEndPoint().getX() - rectangle.getStartPoint().getY(),
                rectangle.getEndPoint().getY() - rectangle.getStartPoint().getY());
        this.startPoint = rectangle.getStartPoint();
        this.endPoint = rectangle.getEndPoint();
        this.attributes = rectangle.getAttributes();
    }
    
    /**
     * Creates and return a copy of the rectangle.
     * @return the copy of the rectangle.
     */
    @Override
    public GRectangle clone(){
        return new GRectangle(this);
    }
    
    /**
     * Obtains the properties of the rectangle.
     * @return an gAttribute with the properties of the rectangle.
     */
    @Override
    public GAttribute getAttributes() {
        return this.attributes;
    }
    
    /**
     * Obtains the start point of the rectangle.
     * 
     * @return the left superior corner of the rectangle.
     */
    @Override
    public Point2D getStartPoint(){
        return this.startPoint;
    }
    
    /**
     * Obtains the end point of the rectangle.
     * 
     * @return the right inferior corner of the rectangle.
     */
    @Override
    public Point2D getEndPoint() {
        return this.endPoint;
    }

    /**
     * Moves the rectangle to another point.
     * 
     * @param point new point of the rectangle.
     */
    @Override
    public void moveShape(Point startPoint, Point endPoint) {
        /*double stdistX = endPoint.getX() + (this.startPoint.getX() - startPoint.getX());
        double stdistY = endPoint.getY() + (this.startPoint.getY() - startPoint.getY());
        
        double endistX = endPoint.getX() - (-this.endPoint.getX() + startPoint.getX());
        double endistY = endPoint.getY() - (-this.endPoint.getY() + startPoint.getY());
        this.startPoint.setLocation(this.startPoint.getX() - stdistX, this.startPoint.getY() - stdistY);
        this.endPoint.setLocation(this.endPoint.getX() - endistX, this.endPoint.getY() - endistY);
        this.setFrameFromDiagonal(this.startPoint, this.endPoint);*/
        
        /*((Rectangle2D) this).setFrameFromDiagonal(this.startPoint + startPoint, endPoint);
        System.out.println(startPoint.toString() + " - " + endPoint.toString());
        Point2D cornerPoint = this.getBounds().getLocation();
        System.out.println(cornerPoint.toString());
        //Rectangle nuevo = this.getBounds();
        ((Rectangle) this.getBounds()).setLocation((int) endPoint.getX() + ((int) cornerPoint.getX() - startPoint.x),
                                                   (int) endPoint.getY() + ((int) cornerPoint.getY() - startPoint.y));
        System.out.println("Antes de modificar" + this.startPoint.toString() + " - " + this.endPoint.toString());
        this.startPoint = this.getStartPoint();
        this.endPoint = this.getEndPoint();
        System.out.println("Tras modificar" + this.startPoint.toString() + " - " + this.endPoint.toString());*/
    }    

    /**
     * Draws the rectangle in the desired Graphics2D
     * @param g2d Graphics2D where we want to draw the rectangle
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
     * Updates the rectangle (redimensionations).
     * @param startPoint new start point of the rectangle.
     * @param endPoint new end point of the rectangle.
     */
    @Override
    public void updateShape(Point2D startPoint, Point2D endPoint) {
        this.setFrameFromDiagonal(startPoint, endPoint);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
}
