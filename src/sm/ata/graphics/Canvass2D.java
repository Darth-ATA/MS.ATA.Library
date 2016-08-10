/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import sm.ata.shapes.GAttribute;
import sm.ata.shapes.GEllipse;
import sm.ata.shapes.GRectangle;
import sm.ata.shapes.GShape;

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
    
    protected ArrayList <GShape> vShape; // Container of the shapes in the canvass
    
    private GShape currentShape;
    private Shape clip;
    
    protected Color color;
    protected boolean fill;
    private int thick;
    
    protected Stroke stroke;
    protected boolean smooth;
    protected boolean transparency;
    
    private Point startPoint;
    private Point endPoint;
    private Point cornerPoint;
    
    private int drawMode;
    private int figureMode;
    private boolean editMode;
    
    /**
     * Creates new form Canvass2D
     */
    public Canvass2D() {
        initComponents();
        this.vShape = new ArrayList();
        this.stroke = new BasicStroke(thick);
        this.drawMode = M_POINTS;
        this.editMode = false;
        this.color = Color.BLACK;
        this.fill = false;
        this.thick = 1;
        this.smooth = false;
        this.transparency = false;
    }
    
    // Getters
    /**
     * This method provides the current color of the canvass
     * @return color of the canvass.
     */
    public Color getColor(){
        return this.color;
    }
    
    /**
     * This method provides the status of fill of the canvass
     * @return fill status of the canvass
     */
    public boolean isFill() {
        return fill;
    }
    
    /**
     * This method provides the thickness of the canvass
     * @return thick number of the canvass
     */
    public int getThick() {
        return thick;
    }
    
    /**
     * This method provides the stroke of the canvass
     * @return stroke of the canvass
     */
    public Stroke getStroke() {
        return stroke;
    }
    
    /**
     * This method provides the smooth status of the canvass
     * @return smooth status of the canvass
     */
    public boolean isSmooth() {
        return smooth;
    }
    
    /**
     * this method provides the transparency status of the canvass
     * @return transparency status of the canvass
     */
    public boolean isTransparency() {
        return smooth;
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
    
    /**
     * This method provides the start point
     * @return start point of the canvass
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * This method provides the end point
     * @return end point of the canvass
     */
    public Point getEndPoint() {
        return endPoint;
    }
    
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
        this.stroke = new BasicStroke(thick);
        this.repaint();
    }
    
    /**
     * This method activates or desactivates the antialiashing.
     */
    public void changeAntialiashing() {
        if (this.smooth){
            this.smooth = false;
            this.currentShape.getAttributes().setAntialiasing(GAttribute.ANTIALIASING_OFF);
        }
        else{
            this.smooth = true;
            this.currentShape.getAttributes().setAntialiasing(GAttribute.ANTIALIASING_ON);
        }
        this.repaint();
    }
    
    /**
     * This method activates or desactivates the transparency status
     */
    public void changeTransparency() {
        if (this.transparency)
            this.transparency = false;
        else
            this.transparency = true;
        this.repaint();
    }
    
    /**
     * @param color: Color whose you want paint
     * This method changes the color used by the canvass.
     */
    public void setColor(Color color){
        this.color = color;
    }
    
    /**
     * Change the stroke of the canvass
     * @param stroke change the stroke used by the canvass
     */
    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
    
    /**
     * Change the start point of the canvass
     * @param startPoint change the start point used by the canvass
     */
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    
    /**
     * Change the end point of the canvass
     * @param endPoint change the end point used by the canvass
     */
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

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
     * This method activates or desactivates the fill draw mode.
     */
    public void changeFillMode(){
        if (this.fill){
            this.fill = false;
            this.currentShape.getAttributes().setFillMode(GAttribute.FILL_OFF);
        }    
        else{
            this.fill = true;
            this.currentShape.getAttributes().setFillMode(GAttribute.FILL_ON);
        }
        this.repaint();
    }
    
    /**
     * This method change the figure that will be painted on the canvass.
     * @param figureMode: Figure whose you want paint
     */
    public void setFigureMode(int figureMode){
        this.figureMode = figureMode;
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
        g2d.setPaint(color);
        g2d.setStroke(stroke);
        
        Composite composite;
        if (this.transparency){
            composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        }
        else{
            composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        }
        g2d.setComposite(composite);
        
        RenderingHints render;
        if (smooth){
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        else {
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        g2d.setRenderingHints(render);
        
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
            if (s instanceof Line2D){
                Line2D line = (Line2D) s;
                if (line.ptLineDist(p) <= 5.0){
                    return s;
                }
            }
            else if (s.contains(p)){
                return s;
            }
        return null;
    }
    
    /**
     * This method creates the shape depend on the figure mode
     * (M_POINTS, M_LINES, M_RECTANGLES or M_ELLIPSE)
     */
    public void createShape(){
        switch (this.figureMode){
            case M_POINTS:      this.currentShape = new GRectangle(this.startPoint,this.startPoint);
                                break;
            //case M_LINES:       this.currentShape = new Line2D.Float(this.startPoint, this.startPoint);     
            //                    break;
            case M_RECTANGLES:  this.currentShape = new GRectangle(this.startPoint, this.endPoint);
                                break;
            case M_ELLIPSES:    this.currentShape = new GEllipse(this.startPoint,this.endPoint);
                                break;
        }
        this.vShape.add(this.currentShape);      
    }
    
    /**
     * This method updates the figure dimensions
     */
    public void updateShape(){
        this.currentShape.updateShape(this.startPoint, this.endPoint);
        /*switch (this.figureMode){
            case M_LINES:       ((Line2D) this.currentShape).setLine(this.startPoint, this.endPoint);
                                break;
            case M_RECTANGLES:  ((Rectangle) this.currentShape).setFrameFromDiagonal(this.startPoint, this.endPoint);
                                break;
            case M_ELLIPSES:    ((Ellipse2D) this.currentShape).setFrameFromDiagonal(this.startPoint, this.endPoint);
                                break;
        }*/
    }
    
    /**
     * This method move the figure to another position
     */
    public void moveShape(){
        if (this.currentShape != null){
            if (this.currentShape instanceof Line2D){
                Line2D line = (Line2D) this.currentShape;
                
                Point2D auxPoint = new Point2D.Double(line.getX2()+(this.endPoint.getX() - line.getX1()),
                                                      line.getY2()+(this.endPoint.getY()-line.getY1()));
                line.setLine( (int)this.endPoint.getX() + ((int) this.cornerPoint.getX() - this.startPoint.x),
                              (int)this.endPoint.getY() + ((int) this.cornerPoint.getY() - this.startPoint.y),
                              (int)auxPoint.getX() + ((int) this.cornerPoint.getX() - this.startPoint.x),
                              (int)auxPoint.getY() + ((int) this.cornerPoint.getY() - this.startPoint.y));
            }
            else if (this.currentShape instanceof GRectangle){
                this.currentShape.moveShape(this.startPoint, this.endPoint);
                //((Rectangle) this.currentShape).setLocation((int) this.endPoint.getX() + ((int) this.cornerPoint.getX() - this.startPoint.x),
                //                                            (int) this.endPoint.getY() + ((int) this.cornerPoint.getY() - this.startPoint.y));
            }
            else if (this.currentShape instanceof GEllipse){
                this.currentShape.moveShape(this.startPoint, this.endPoint);
                //Rectangle auxRectangle = ((Ellipse2D) this.currentShape).getBounds();
                //auxRectangle.setLocation((int) this.endPoint.getX() + ((int) this.cornerPoint.getX() - this.startPoint.x),
                //                         (int) this.endPoint.getY() + ((int) this.cornerPoint.getY() - this.startPoint.y));
                //((Ellipse2D) this.currentShape).setFrame(auxRectangle);
            }
        }
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
        // TODO add your handling code here:
        this.startPoint = evt.getPoint();
        this.endPoint = evt.getPoint();
        if (this.editMode){
            currentShape = getSelectedShape(this.startPoint);
            if (this.currentShape != null)
                this.cornerPoint = this.currentShape.getBounds().getLocation();
        }
        else
            this.createShape();
        
    }//GEN-LAST:event_canvassMousePressed
    
    /**
     * This method capture the mouse dragg event and take the point to operate
     * with the canvass
     * @param evt is the mouse event
     */
    private void canvassMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvassMouseDragged
        // TODO add your handling code here:
        this.endPoint = evt.getPoint();
        if (this.editMode)
            this.moveShape();
        else
            this.updateShape();
        this.repaint();
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
