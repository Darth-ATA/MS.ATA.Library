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
    
    public static final boolean ANTIALIASING_OFF = false;
    public static final boolean ANTIALIASING_ON = true;
    
    public static final int S_CONTINUOUS = 0;
    public static final int S_BROKEN = 1;
    
    public static final int FILL_OFF = 0;
    public static final int FILL_ON = 1;
    public static final int FILL_GRADIENT = 2;
    
    public static final int HORIZONTAL_GRADIENT = 0;
    public static final int VERTICAL_GRADIENT = 1;
    public static final int DIAGONAL_GRADIENT = 2;
    
    private boolean antialiasing;   
    private float thick;
    private Stroke border;    
    private Color borderColor;
    private Color color; 
    private Color gradientColor;
    private int gradientType;
    private int fillMode;       
    private float transparency;
    
    /**
     * Default constructor.
     */
    public GAttribute(){
        this.antialiasing = ANTIALIASING_OFF;
        this.thick  = 1;
        this.border = new BasicStroke(0);
        this.color = Color.BLACK;
        this.borderColor = Color.BLACK;
        this.gradientColor = Color.WHITE;
        this.gradientType = HORIZONTAL_GRADIENT;
        this.fillMode = 0;
        this.transparency = 1.0f;
    }
    
    /**
     * Constructor of the attribute.
     * @param antialiasing.
     * @param border.
     * @param color.
     * @param borderColor.
     * @param gradientColor.
     * @param gradientType.
     * @param fillMode.
     * @param transparency .
     */
    public GAttribute(boolean antialiasing, float border, Color color, 
            Color borderColor, Color gradientColor, int gradientType, 
            int fillMode, float transparency){
        this.antialiasing = antialiasing;
        this.border = new BasicStroke(border);
        this.color = color;
        this.borderColor = borderColor;
        this.gradientColor = gradientColor;
        this.gradientType = gradientType;
        this.fillMode = fillMode;
        this.transparency = transparency;
    }
    
    /**
     * Copy constructor of the attribute.
     * @param attribute to be like.
     */
    public GAttribute(GAttribute attribute){
        this.color = attribute.color;
        this.borderColor = attribute.borderColor;
        this.gradientColor = attribute.gradientColor;
        this.gradientType = attribute.gradientType;
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
     * Obtains the border color vector.
     * @return border color vector.
     */
    public Color getBorderColor(){
        return this.borderColor;
    }
    
    /**
     * Obtains the gradient color vector.
     * @return color vector.
     */
    public Color getGradientColor(){
        return this.gradientColor;
    }
    
    /**
     * Obtains the gradient type.
     * @return gradient type.
     */
    public int getGradientType(){
        return this.gradientType;
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
     * Sets the border color value
     * @param color value
     */
    public void setBorderColor(Color color){
        this.borderColor = color;
    }
    
    /**
     * Sets the gradient color value
     * @param gradientColor value
     */
    public void setGradientColor(Color gradientColor) {
        this.gradientColor = gradientColor;
    }
    
    /**
     * Sets the gradient type.
     * @param gradientType value.
     */
    public void setGradientType(int gradientType){
        this.gradientType = gradientType;
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
    
    /**
     * Sets tje border style.
     * @param borderStyle.
     */
    public void setBorderStyle(int borderStyle){
        if (borderStyle == S_CONTINUOUS)
            this.border = new BasicStroke(this.thick);
        else if(borderStyle == S_BROKEN){
            float dash[] = {1.0f, 0.0f, 10.0f};
            this.border = new BasicStroke(this.thick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 2.0f, dash, 0.0f);
        }
    }
}
