/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import static java.lang.Math.min;
import sm.image.BufferedImageOpAdapter;
import sm.image.BufferedImagePixelIterator;

/**
 *
 * @author Hasbr
 */
public class SepiaOp extends BufferedImageOpAdapter {
    
    public SepiaOp(){}
    
    public BufferedImage filter(BufferedImage src, BufferedImage dest){
        if (src == null){
            throw new NullPointerException("src image is null");
        }
        if (dest == null){
            dest = createCompatibleDestImage(src, null);
        }
        
        WritableRaster destRaster = dest.getRaster(); 
        
        int R,G,B;
        int sepiaR, sepiaG, sepiaB;
        BufferedImagePixelIterator.PixelData pixel;
        for (BufferedImagePixelIterator it = new BufferedImagePixelIterator(src); it.hasNext();){
            pixel = it.next();
            
            R = pixel.sample[0];
            G = pixel.sample[1];
            B = pixel.sample[2];
            
            sepiaR = min(255,(int) (0.393*R + 0.769*G + 0.189*B));
            sepiaG = min(255,(int) (0.349*R + 0.686*G + 0.168*B));
            sepiaB = min(255,(int) (0.272*R + 0.534*G + 0.131*B));
            
            pixel.sample[0] = sepiaR;
            pixel.sample[1] = sepiaG;
            pixel.sample[2] = sepiaB;
            destRaster.setPixel(pixel.col, pixel.row, pixel.sample); 
        }
        return dest;
    }
}
