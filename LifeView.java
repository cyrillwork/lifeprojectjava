/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeprojectjava;

import java.awt.Color;
import java.util.*;
import java.awt.*;

import javax.swing.JPanel;
/**
 *
 * @author Admin
 */
public abstract class LifeView extends JPanel implements Observer 
{
    protected LifeModel model;
    
    public LifeView(LifeModel observableModel)
        throws NullPointerException
    {
        if(observableModel == null)
        {
            throw new NullPointerException(); 
        }
        model = observableModel;
        model.addObserver(this);
        
        setBackground(Color.gray);
        this.setBorder(null);
        setBorder(new javax.swing.border.MatteBorder(1, 1, 1, 1, Color.black));
    }
    
    public LifeModel getModel()
    {
        return model;
    }
    
    protected abstract void updateDisplay();
    
    @Override
    //get update from model
    public void update(Observable observable, Object object)
    {
        updateDisplay();
    }
        
}
