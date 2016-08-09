/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.shapes;

import java.awt.Color;
import java.awt.Stroke;

/**
 *
 * @author Darth-ATA
 */
public class GAttribute {
    
    private static boolean ANTIALIASING_OFF = false;
    private static boolean ANTIALIASING_ON = true;
    
    private static int FILL_OFF = 0;
    private static int FILL_ON = 1;
    private static int FILL_GRADIENT = 2;
    
    private boolean antialiasing;   
    private Stroke border;      
    private Color color;        
    private int fillMode;       
    private float transparency;
    
    /**
     * Default constructor.
     */
    public GAttribute(){}
    
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
        return antialiasing;
    }

    /**
     * Obtains the border style.
     * @return border style.
     */
    public Stroke getBorder() {
        return border;
    }

    /**
     * Obtains the color vector.
     * @return color vector.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Obtains the fill mode.
     * @return fill mode.
     */
    public int getFillMode() {
        return fillMode;
    }

    /**
     * Obtains the transparency mode.
     * @return transparency mode.
     */
    public float getTransparency() {
        return transparency;
    }

    /**
     * Sets the antialiasing value.
     * @param antialiasing value.
     */
    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
    }

    /**
     * Sets the border mode.
     * @param border value.
     */
    public void setBorder(Stroke border) {
        this.border = border;
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
