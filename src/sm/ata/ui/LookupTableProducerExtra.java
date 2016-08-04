/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.ui;

import java.awt.image.ByteLookupTable;
import java.awt.image.LookupTable;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.sin;
import sm.image.LookupTableProducer;

/**
 *
 * @author Hasbr
 */
public class LookupTableProducerExtra extends LookupTableProducer {
    
    public static LookupTable sinus(double w){
        double K = 255.0;   //Normalize constant
        byte lt[] = new byte[256];
        lt[0] = 0;
        for (int l=1; l<256; l++){
            lt[l] = (byte) (K*abs(sin(w*(2*PI*l/360.0))));
        }
        ByteLookupTable slt = new ByteLookupTable(0,lt);
        return slt;
    }
}
