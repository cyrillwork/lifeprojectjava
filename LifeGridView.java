/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeprojectjava;

import javax.swing.*;
import java.util.*;
import java.awt.*;
/**
 *
 * @author Admin
 */
public class LifeGridView extends LifeView
{
    final static int BEGIN_X = 1;
    final static int BEGIN_Y = 1;
    final static int SIZE = 30;

    public LifeGridView(LifeModel model)
    {
        super(model);
        updateDisplay();
    }
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent(g);
        java.awt.Graphics2D graphics2D = (java.awt.Graphics2D)g;
        
        int N = getModel().getN();
        int M = getModel().getM();
        
        System.out.println("model=" + model);
        
        boolean [][] arrayMain = model.getArray();
        
        System.out.println("array=" + arrayMain);
        
        for(int i = 0; i <N; i++)
        {
            for(int j = 0; j<M; j++)
            {
                if(arrayMain[i][j])
                {
                    graphics2D.setColor(Color.BLUE);
                }
                else
                {
                    graphics2D.setColor(Color.WHITE);
                }
                
                graphics2D.fillRect
                (
                    BEGIN_X + i*SIZE + i, 
                    BEGIN_Y + j*SIZE + j, 
                    SIZE,  SIZE
                );
            }
        }

    }
    
    public void updateDisplay()
    {
        repaint();
    }
    
    public Dimension getPreferredSize()
    {        
        int N = getModel().getN();
        int M = getModel().getM();
        return new Dimension(       N*SIZE + N + 2*BEGIN_X + 8, 
                                    M*SIZE + M + BEGIN_Y + 70);
    }
    
}
