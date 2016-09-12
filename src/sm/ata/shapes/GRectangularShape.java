/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.geom.Point2D;

/**
 * This class provides all the methods needed for rectangular shapes.
 * @author Darth-ATA
 */
public abstract class GRectangularShape extends GFilledShape {
    @Override
    public Point2D getStartPoint() {
        Point2D startPoint = new Point2D.Double(shape.getBounds().getMinX(), shape.getBounds().getMaxY());
        return startPoint;
    }

    @Override
    public Point2D getEndPoint() {
        Point2D startPoint = new Point2D.Double(shape.getBounds().getMinX(), shape.getBounds().getMaxY());
        return startPoint;
    }    
    
    @Override
    public Point2D getInterestPoint(int index){
        return this.getBoundPoint(index);
    }
}
