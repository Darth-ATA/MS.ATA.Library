/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

/**
 * This class provide an object that encapsulates all the GShapes properties.
 * @author Darth-ATA
 */
public class GAttribute {
    
    public static boolean ANTIALIASING_OFF = false;
    public static boolean ANTIALIASING_ON = true;
    
    public static int FILL_OFF = 0;
    public static int FILL_ON = 1;
    public static int FILL_GRADIENT = 2;
    
    private boolean antialiasing;   
    private float thick;
    private Stroke border;      
    private Color color;        
    private int fillMode;       
    private float transparency;
    
    /**
     * Default constructor.
     */
    public GAttribute(){
        this.antialiasing = ANTIALIASING_OFF;
        this.thick  = 0;
        this.border = new BasicStroke(0);
        this.color = Color.BLACK;
        this.fillMode = 0;
        this.transparency = 1.0f;
    }
    
    /**
     * Constructor of the attribute.
     * @param antialiasing.
     * @param border.
     * @param color.
     * @param fillMode.
     * @param transparency .
     */
    public GAttribute(boolean antialiasing, float border, Color color, int fillMode, float transparency){
        this.antialiasing = antialiasing;
        this.border = new BasicStroke(border);
        this.color = color;
        this.fillMode = fillMode;
        this.transparency = transparency;
    }
    
    /**
     * Constructor of the attribute.
     * @param antialiasing.
     * @param border.
     * @param color.
     * @param fillMode.
     * @param transparency .
     */
    public GAttribute(boolean antialiasing, Stroke border, Color color, int fillMode, float transparency){
        this.antialiasing = antialiasing;
        this.border = border;
        this.color = color;
        this.fillMode = fillMode;
        this.transparency = transparency;
    }
    
    /**
     * Copy constructor of the attribute.
     * @param attribute to be like.
     */
    public GAttribute(GAttribute attribute){
        this.color = attribute.color;
        this.fillMode = attribute.fillMode;
        this.antialiasing = attribute.antialiasing;
        this.transparency = attribute.transparency;
        this.thick = attribute.thick;
        this.border = attribute.border;
    }
    
    /**
     * Creates and return a copy of the object.
     * @return the copy of the object.
     * @throws java.lang.CloneNotSupportedException .
     */
    @Override
    public GAttribute clone() throws CloneNotSupportedException{
        return new GAttribute(this);
    }

    /**
     * Obtains the antialiasing mode.
     * @return antialiasing mode.
     */
    public boolean getAntialiasing() {
        return this.antialiasing;
    }

    /**
     * Obtains the border style.
     * @return border style.
     */
    public Stroke getBorder() {
        return this.border;
    }
    
    /**
     * Obtains the thick float.
     * @return thick float.
     */
    public float getThick(){
        return this.thick;
    }

    /**
     * Obtains the color vector.
     * @return color vector.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Obtains the fill mode.
     * @return fill mode.
     */
    public int getFillMode() {
        return this.fillMode;
    }

    /**
     * Obtains the transparency mode.
     * @return transparency mode.
     */
    public float getTransparency() {
        return this.transparency;
    }

    /**
     * Sets the antialiasing value.
     * @param antialiasing value.
     */
    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
    }

    /**
     * Sets the border mode stablishing the Stroke.
     * @param border value.
     */
    public void setBorder(Stroke border) {
        this.border = border;
    }
    
    /**
     * Sets thick value.
     * @param thick float value.
     */
    public void setThick(float thick) {
        this.thick = thick;
        this.border = new BasicStroke(thick);
    }

    /**
     * Sets the color value
     * @param color value
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the fill mode.
     * @param fillMode mode.
     */
    public void setFillMode(int fillMode) {
        this.fillMode = fillMode;
    }

    /**
     * Sets the transparency mode.
     * @param transparency mode.
     */
    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }
}
