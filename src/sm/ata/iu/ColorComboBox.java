/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ata.iu;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Hasbr
 * Inspired in the solve of http://stackoverflow.com/questions/3958647/displaying-images-in-jcombobox
 */
public class ColorComboBox extends JFrame {
    
    private JComboBox comboBox;
    
    /**
     * Creates new form ColorComboBox
     * It takes the differents images of colors in the directory colors and creates
     * the comboBox.
     */
    public ColorComboBox() {
        //initComponents();
         Object[] items = {
            new ImageIcon("black.gif"),
            new ImageIcon("white.gif"),
            new ImageIcon("blue.gif"),
            new ImageIcon("green.gif"),
            new ImageIcon("yellow.gif"),
            new ImageIcon("red.gif")
        };
        comboBox = new JComboBox( items );
        getContentPane().add( comboBox, BorderLayout.NORTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args)
    {
        JFrame frame = new ColorComboBox();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}