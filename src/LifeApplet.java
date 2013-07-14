package lifeprojectjava;

import lifeprojectjava.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class LifeApplet extends Applet implements Runnable
{
	LifeModel model;
	LifeGridView lifePanel;
	boolean isRun = false;
	JButton buttonRun;

	public void init()
	{
		model = new LifeModel(12, 12, 500); 
		lifePanel = new LifeGridView(model);

		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
        	panel.setBackground(Color.gray);
        
        
        	buttonRun = new JButton("Run"); 
        
        	buttonRun.addActionListener( new ActionListener()
            	{
                	public void actionPerformed( ActionEvent e)
    			{
    				
                    		if(isRun)
                    		{
                        		stop();
                    		}
                    		else
                    		{
                        		go();    
                    		}
                    		
                    
                    
    			}
    		}
        	); 
        
        	panel.add(buttonRun);
        
        	JButton buttonRand = new JButton("Random");
        	buttonRand.addActionListener( new ActionListener()
            	{
                	public void actionPerformed( ActionEvent e )
                	{
                    		model.setRandom();
        			lifePanel.repaint();
                	}
            	}
        	);
        	panel.add(buttonRand);
        
        	JButton buttonInit = new JButton("Clear");
        	buttonInit.addActionListener( new ActionListener()
            	{
                	public void actionPerformed( ActionEvent e )
                	{
                    		model.setClear();
                    		lifePanel.repaint();
                	}
            	}
        	);
        	panel.add(buttonInit);

		
		
		//Panel panel = new Panel();
		//panel.add(new Button("Start"));
		//panel.add(new Button("Stop"));
		add("Center", lifePanel );
		add("South",  panel );

	}

    public void go()
    {
        isRun = true;
        Thread t = new Thread(this);
        t.start();
        buttonRun.setText("Stop");
    }
    
    public void stop()
    {
        isRun = false;
        buttonRun.setText("Run");
    }
    
    public void setRandom()
    {
        model.setRandom();
        lifePanel.repaint();
    }
    
    public void run()
    {
        while(isRun)
        {
            System.out.println("Run loop");           
            try
            {
                model.stepLife();
                lifePanel.repaint();
                
                Thread.sleep(model.getDelay());
                
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}