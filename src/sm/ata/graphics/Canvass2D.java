/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import sm.ata.shapes.GArc;
import sm.ata.shapes.GAttribute;
import sm.ata.shapes.GEllipse;
import sm.ata.shapes.GLine;
import sm.ata.shapes.GPoint;
import sm.ata.shapes.GRectangle;
import sm.ata.shapes.GShape;
import static sm.ata.shapes.GShape.FIRST_CORNER;

/**
 *
 * @author Darth-ATA
 */
public class Canvass2D extends javax.swing.JPanel {
    // Differents figure modes
    public static final int M_POINTS = 0;
    public static final int M_LINES = 1;
    public static final int M_RECTANGLES = 2;
    public static final int M_ELLIPSES = 3;
    public static final int M_ARC = 4;
    
    protected ArrayList <GShape> vShape; // Container of the shapes in the canvass
    
    private GShape currentShape;
    private Shape clip;
    
    private GAttribute attributes;
    
    /*protected Color color;
    protected boolean fill;*/
    private int thick;
    
    /*protected Stroke stroke;
    protected boolean smooth;
    protected boolean transparency;*/
    
    private Point2D pressPoint;
    private Point2D cornerPoint;
    
    private int drawMode;
    private int figureMode;
    private boolean editMode;
    
    /**
     * Creates new form Canvass2D
     */
    public Canvass2D() {
        initComponents();
        this.vShape = new ArrayList();
        this.attributes = new GAttribute();
        //this.stroke = new BasicStroke(thick);
        this.drawMode = M_POINTS;
        this.editMode = false;
        //this.color = Color.BLACK;
        //this.fill = false;
        this.thick = 1;
        //this.smooth = false;
        //this.transparency = false;
    }
    
    // Getters
    /**
     * This method provides the current color of the canvass
     * @return color of the canvass.
     */
    public Color getColor(){
        return this.attributes.getColor();
    }
    
    /**
     * This method provides the status of fill of the canvass
     * @return fill mode of the canvass
     */
    public int getFillMode() {
        return this.attributes.getFillMode();
    }
    
    /**
     * This method provides the thickness of the canvass
     * @return thick number of the canvass
     */
    public float getThick() {
        return this.attributes.getThick();
    }
    
    /**
     * This method provides the stroke of the canvass
     * @return stroke of the canvass
     */
    public Stroke getStroke(){
        return this.attributes.getBorder();
    }
    
    /**
     * This method provides the smooth status of the canvass
     * @return smooth status of the canvass
     */
    public boolean isSmooth() {
        return this.attributes.getAntialiasing();
    }
    
    /**
     * This method provides the transparency value of the canvass
     * @return transparency value of the canvass
     */
    public float getTransparency() {
        return this.attributes.getTransparency();
    }
    
    /**
     * This method provides if the edit mode is activated or not
     * @return the state of edit mode
     */
    public boolean isEditMode(){
        return this.editMode;
    }
    
    /**
     * This method provides the current draw mode
     * @return the draw mode of the canvass
     */
    public int getDrawMode(){
        return this.drawMode;
    }
    
    /**
     * This method provides the current figure mode
     * @return the figure mode of the canvass
     */
    public int getFigureMode(){
        return this.figureMode;
    }
    
    // TPODO: BORRAR SI NO LO USAMOS
    /**
     * This method provides the start point
     * @return start point of the canvass
     */
    //public Point2D getStartPoint() {
    //    return startPoint;
    //}

    /**
     * This method provides the end point
     * @return end point of the canvass
     */
    //public Point2D getEndPoint() {
    //    return endPoint;
    //}
    
    // Setters
    /**
     * This method change the clip
     * @param clip of the canvass
     */
    public void setClip(Shape clip){
        this.clip = clip;
    }
    /**
     * This method change the thickness 
     * @param thick of the canvass
     */
    public void setThick(int thick) {
        this.attributes.setThick(thick);
        if(this.currentShape != null){
            this.currentShape.setAttributes(this.attributes);
            this.repaint();
        }
    }
    
    /**
     * This method activates or desactivates the antialiashing.
     */
    public void changeAntialiashing() {
        if (this.attributes.getAntialiasing()){
            this.attributes.setAntialiasing(false);
            //this.currentShape.getAttributes().setAntialiasing(GAttribute.ANTIALIASING_OFF);
        }
        else{
            this.attributes.setAntialiasing(true);
            //this.currentShape.getAttributes().setAntialiasing(GAttribute.ANTIALIASING_ON);
        }
        if(this.currentShape != null){
            this.currentShape.setAttributes(this.attributes);
            this.repaint();
        }
    }
    
    /**
     * This method activates or desactivates the transparency status
     * @param transparency value to be set.
     */
    public void setTransparency(float transparency) {
        this.attributes.setTransparency(transparency);
        if(this.currentShape != null){
            this.currentShape.setAttributes(this.attributes);
            this.repaint();
        }
    }
    
    /**
     * This method changes the color used by the canvass.
     * @param color: Color whose you want paint
     */
    public void setColor(Color color){
        this.attributes.setColor(color);
        if(this.currentShape != null){
            this.currentShape.setAttributes(this.attributes);
            this.repaint();
        }
    }
    
    /**
     * Change the stroke of the canvass
     * @param stroke change the stroke used by the canvass
     */
    public void setStroke(Stroke stroke) {
        this.attributes.setBorder(stroke);
        if(this.currentShape != null){
            this.currentShape.setAttributes(this.attributes);
            this.repaint();
        }
    }
    
    /**
     * Change the start point of the canvass
     * @param startPoint change the start point used by the canvass
     */
    //public void setStartPoint(Point startPoint) {
    //   this.startPoint = startPoint;
    //}
    
    /**
     * Change the end point of the canvass
     * @param endPoint change the end point used by the canvass
     */
    //public void setEndPoint(Point endPoint) {
    //    this.endPoint = endPoint;
    //}

    /**
     * Change the draw mode of the canvass
     * @param drawMode change the draw mode used by the canvass
     */
    public void setDrawMode(int drawMode) {
        this.drawMode = drawMode;
    }
    
    /**
     * This method activates or desactivates the edit mode.
     */
    public void changeEditMode(){
        if (this.editMode)
            this.editMode = false;
        else
            this.editMode = true;
    }
    
    /**
     * This method change the fill draw mode.
     * @param fillMode to be set.
     */
    public void setFillMode(int fillMode){
        this.attributes.setFillMode(fillMode);
        if(this.currentShape != null){
            this.currentShape.setAttributes(this.attributes);
            this.repaint();
        }
    }
    
    /**
     * This method change the figure that will be painted on the canvass.
     * @param figureMode: Figure whose you want paint
     */
    public void setFigureMode(int figureMode){
        this.figureMode = figureMode;
        this.editMode = false;
    }
    
    // Other methods
    /**
     * 
     * @param g: Graphics in that you want paint
     * This method paints the differents figures in the canvass.
     * Depend on the differents extra attributes in the canvass, the figures
     * will be painted fill, smoothed and transparent.
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if (clip != null){
            g2d.clip(this.clip);
        }
        
        for (GShape s:vShape) {
            s.draw(g2d);
        }
    }
    
    /**
     * This method provides the figure in a point
     * @param p the point to search the figure
     * @return the figure in the provided point
     */
    private GShape getSelectedShape(Point2D p){
        for (GShape s:vShape)
            if (s.contains(p)){
                return s;
            }
        return null;
    }
    
    /**
     * This method creates the shape depend on the figure mode
     * (M_POINTS, M_LINES, M_RECTANGLES, M_ELLIPSE, M_ARC)
     */
    public void createShape(Point2D point){
        switch (this.figureMode){
            case M_POINTS:      this.currentShape = new GPoint(point);
                                break;
            case M_LINES:       this.currentShape = new GLine(point, point);     
                                break;
            case M_RECTANGLES:  this.currentShape = new GRectangle(point, point);
                                break;
            case M_ELLIPSES:    this.currentShape = new GEllipse(point, point);
                                break;
            case M_ARC:         this.currentShape = new GArc(point, point);
                                break;
        }
        this.currentShape.setAttributes(this.attributes);
        this.vShape.add(this.currentShape);
    }
    
    /**
     * This method updates the figure dimensions
     */
    public void updateShape(Point2D point){
        this.currentShape.updateShape(this.pressPoint, point);
        this.repaint();
    }
    
    /**
     * This method move the figure to another position
     */
    public void moveShape(Point2D point){
        if (this.currentShape != null){
            this.currentShape.moveShape(point);
        }
        this.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                canvassMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                canvassMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                canvassMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method capture the mouse pressed event and take the point to operate
     * with the canvass
     * @param evt is the mouse event
     */
    private void canvassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvassMousePressed
        this.pressPoint = evt.getPoint();
        
        if (this.editMode){
            currentShape = getSelectedShape(evt.getPoint());
            if (this.currentShape != null){
                this.cornerPoint = this.currentShape.getInterestPoint(FIRST_CORNER);
                this.currentShape.setAttributes(this.attributes);
            }
        }
        else
            this.createShape(evt.getPoint());
        
    }//GEN-LAST:event_canvassMousePressed
    
    /**
     * This method capture the mouse dragg event and take the point to operate
     * with the canvass
     * @param evt is the mouse event
     */
    private void canvassMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvassMouseDragged
        // TODO add your handling code here:
        Point dragPoint = evt.getPoint();
        if (this.editMode){
            if(this.currentShape != null){
                Point2D point = new Point2D.Double(
                    dragPoint.x - (this.pressPoint.getX() - cornerPoint.getX()),
                    dragPoint.y - (this.pressPoint.getY() - cornerPoint.getY()));
                this.moveShape(point);
            }
        }
        else
            this.updateShape(dragPoint);
    }//GEN-LAST:event_canvassMouseDragged

    /**
     * This method capture the ouse released event and take the point to operate
     * with the canvass
     * @param evt 
     */
    private void canvassMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvassMouseReleased
        // TODO add your handling code here:
        this.repaint();
    }//GEN-LAST:event_canvassMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
