/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.ui;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import static java.lang.Math.toRadians;
import sm.ata.graphics.Canvass2D;
import sm.image.KernelProducer;
import sm.image.LookupTableProducer;
import sm.ata.image.SepiaOp;
import sm.image.BinaryOp;
import sm.image.BlendOp;
import sm.image.SubtractionOp;

/**
 *
 * @author alejandro
 */
public class ImageCanvass2D extends Canvass2D {
    //Differents addition operation mode
    public static final int ADD_IMAGE = 0;
    public static final int SUBS_IMAGE = 1;
    public static final int DYN_IMAGE = 2;

    private BufferedImage img;
    private BufferedImage originalImg;
    
    /**
     * Creates new form ImageCanvass2D
     */
    public ImageCanvass2D() {
        initComponents();
        this.img = null;
    }

    public BufferedImage getImg() {
        return img;
    }
    
    public BufferedImage getOriginalImg(){
        return originalImg;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
        if (img != null)
            setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
    }
    
    public void setOriginalImg(BufferedImage img){
        this.originalImg = img;
        if (img != null)
            setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
    }
    
    public void paintComponent(Graphics g){
        if (this.img != null){
            super.setClip(new Rectangle(0,0,img.getWidth(),img.getHeight()));
        }
        else{
            super.setClip(new Rectangle(0,0,300,300));
        }
        super.paintComponent(g);
        if (img != null) 
            g.drawImage(img,0,0,this);
    }
    
    public BufferedImage getImg(boolean drawVector){
        if (drawVector){
            Graphics2D g2d = (Graphics2D) img.getGraphics();
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

            for (Shape s:vShape) {
                if (fill) 
                    g2d.fill(s);     
                g2d.draw(s);   
            }
            return img;
        }
        else {
            return img;
        }
    }
    
    /**
     * Changes the brightness of the image
     * @param bright factor to change
     */
    public void changeBrightness(float bright){
        BufferedImage imgSource = this.originalImg;
        if (imgSource != null){
            try{
                RescaleOp rop;
                // Its necesary if the image has alpha component 
                if ( imgSource.getColorModel().hasAlpha() ){
                    float scalaFactor[] = {1.0f,1.0f,1.0f,1.0f};
                    float disp[] = {bright,bright,bright,0.0f};
                    rop = new RescaleOp(scalaFactor,disp, null);                        
                }else{
                    rop = new RescaleOp(1.0f, bright, null);
                }
                BufferedImage imgdest = rop.filter(imgSource, null);
                this.setImg(imgdest);
                this.repaint();
            } catch(IllegalArgumentException e){
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
    
    /**
     * 
     * @param filter 
     */
    public void applyFilter(int filter){
        Kernel k;        
        switch (filter) {
            case 0: k = KernelProducer.createKernel(KernelProducer.TYPE_MEDIA_3x3);
                    break;
            case 1: k = KernelProducer.createKernel(KernelProducer.TYPE_BINOMIAL_3x3);
                    break;
            case 2: k = KernelProducer.createKernel(KernelProducer.TYPE_ENFOQUE_3x3);
                    break;
            case 3: k = KernelProducer.createKernel(KernelProducer.TYPE_RELIEVE_3x3);
                    break;
            case 4: k = KernelProducer.createKernel(KernelProducer.TYPE_LAPLACIANA_3x3);
                    break;
            default: k = null;
                    break;
        }

        ConvolveOp cop = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage imgSource = this.img;
        if (imgSource != null){
            try{
                BufferedImage imgdest = cop.filter(imgSource, null);
                this.setImg(imgdest);
                this.setOriginalImg(imgdest);
                this.repaint();
            } catch(IllegalArgumentException e){
                System.err.println(e.getLocalizedMessage());
            }
        }        
    }
    
    public void applyContrast(int type){
        BufferedImage imgSource = this.img;
        if(imgSource != null){
            try{
                LookupTable lt = LookupTableProducer.createLookupTable(type);
                LookupOp lop = new LookupOp(lt, null);
                // Origin and destination same image
                lop.filter(imgSource, imgSource);
                this.setOriginalImg(imgSource);
                this.repaint();
            } catch(Exception e){
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
    
    public void applySinus(){
        BufferedImage imgSource = this.img;
        if (imgSource != null){
            try{
                LookupTable lt = LookupTableProducerExtra.sinus(180.0/255.0);
                LookupOp lop = new LookupOp(lt, null);
                // Origin and destination same image
                lop.filter( imgSource, imgSource);
                this.setOriginalImg(imgSource);
                this.repaint();
            } catch(Exception e){
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
    
    public void rotate(int degree, boolean save){
        BufferedImage imgSource = this.originalImg;
        if (imgSource != null){
            try{
                double r = toRadians(degree);
                Point p = new Point(imgSource.getWidth()/2, imgSource.getHeight()/2);
                AffineTransform at = AffineTransform.getRotateInstance(r,p.x,p.y);
                AffineTransformOp atop;
                atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                BufferedImage imgDest = atop.filter(imgSource, null);
                this.setImg(imgDest);
                if (save)
                    this.setOriginalImg(imgDest);
                this.repaint();
            } catch(Exception e){
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
    
    public void scalate(float scale){
        BufferedImage imgSource = this.img;
        if (imgSource != null){
            try{
                AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
                AffineTransformOp atop;
                atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                BufferedImage imgDest = atop.filter(imgSource, null);
                this.setImg(imgDest);
                this.setOriginalImg(imgDest);
                this.repaint();
            } catch(Exception e){
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
    
    public void sepia(){
        BufferedImage imgSource = this.img;
        if (imgSource != null){
            try{
                SepiaOp so = new SepiaOp();
                // Origin and destination same image
                so.filter(imgSource, imgSource);
                this.setOriginalImg(imgSource);
                this.repaint();
            } catch(Exception e){
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
    
    public BufferedImage addImage(BufferedImage imgLeft, int type){
        BufferedImage imgRight = this.originalImg;
        if (imgRight != null && imgLeft != null){
            try{
                BinaryOp op;
                //Adds or substracts the other image
                if (type == ADD_IMAGE){
                    op = new BlendOp(imgLeft);
                }
                else {
                    op = new SubtractionOp(imgLeft);                    
                }
                BufferedImage imgdest = op.filter(imgRight, null);
                return imgdest;
            } catch (IllegalArgumentException e){
                System.err.println("Error: " + e.getLocalizedMessage());
            }
        }
        return null;
    }
    
    public static BufferedImage addImage(BufferedImage imgRight, BufferedImage imgLeft, int type, float alpha){
        if (imgRight != null && imgLeft != null){
            try{
                BinaryOp op;
                //Adds or substracts the other image
                switch (type) {
                    case ADD_IMAGE:
                        op = new BlendOp(imgLeft);
                        break;
                    case SUBS_IMAGE:
                        op = new SubtractionOp(imgLeft);
                        break;
                    default:
                        op = new BlendOp(imgLeft,alpha);
                        break;
                }
                BufferedImage imgdest = op.filter(imgRight, null);
                return imgdest;
            } catch (IllegalArgumentException e){
                System.err.println("Error: " + e.getLocalizedMessage());
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
