/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeprojectjava;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 *
 * @author Admin
 */
public class LifeFrame extends JFrame implements Runnable
{
    private LifeModel model;
    LifeGridView view;
    private boolean isRun = false;
    protected JButton button1;
    private JLabel label1;
    
    public LifeFrame(LifeModel m)
    {
        super(/*"using model MVC"*/);
        this.model = m;
        System.out.println("Constructor LifeFrame");   
        initLifeFrame();
    }
    
    public void initLifeFrame()
    {
            
        //System.out.println("model = " +  model);
        //model.initLifeModel(N, M);
        
        view = new LifeGridView(model);
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().add(BorderLayout.CENTER, view);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        button1 = new JButton("Run"); 
        
        button1.addActionListener( new ActionListener()
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
        
        panel.add(button1);
        
        JButton buttonRand = new JButton("Random");
        buttonRand.addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    setRandom();
                }
            }
        );
        panel.add(buttonRand);
        
        JButton buttonInit = new JButton("Init");
        buttonInit.addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    model.backToInit();
                    view.repaint();
                }
            }
        );
        panel.add(buttonInit);

        JButton buttonSave = new JButton("Save");
        buttonSave.addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    stop();
                    
                    model.backToInit();
                    view.repaint();
                    
                    JFileChooser chooser = new JFileChooser();
                    //chooser.setFileFilter(new FileNameExtensionFilter("sss"));
                    int returnVal = chooser.showSaveDialog(null);
                    if(returnVal == JFileChooser.APPROVE_OPTION) 
                    {
                        System.out.println("You chose to save file: " +
                        chooser.getSelectedFile().getName());
                        try
                        {
                            String file = chooser.getCurrentDirectory().getPath()+ 
                                "\\" +chooser.getSelectedFile().getName();
                            System.out.println("" + file);
                                
                            
                            FileOutputStream sss = new FileOutputStream(file);
                            
                            ObjectOutputStream os = new ObjectOutputStream(sss);
                            os.writeObject(model);
                            os.close();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                        
                    }
                }
            }
        );
        panel.add(buttonSave);

        JButton buttonClear = new JButton("Clear");
        buttonClear.addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    stop();
                    model.setClear();
                    view.repaint();
                }
            }
        );
        panel.add(buttonClear);
        
        label1 = new JLabel("  step ");
        panel.add(label1);
        
        getContentPane().add(BorderLayout.SOUTH, panel);
        
        this.setSize(view.getPreferredSize());
        this.setResizable(false);
        
        WindowListener listener = new WindowAdapter() 
        {
            public void windowClosing(WindowEvent w) 
            {
                isRun = false;
            }
        };
        this.addWindowListener(listener);
    }
    
    public LifeModel getModel()
    {
        return model;
    }
    
    public void go()
    {
        isRun = true;
        Thread t = new Thread(this);
        t.start();
        button1.setText("Stop");
    }
    
    public void stop()
    {
        isRun = false;
        button1.setText("Run");
    }
    
    public void setRandom()
    {
        model.setRandom();
        view.repaint();
    }
    
    public void run()
    {
        
        while(isRun)
        {
            //System.out.println("Run loop");           
            try
            {
                model.stepLife();
                view.repaint();             
                label1.setText("  step " + model.getSteps());
                Thread.sleep(model.getDelay());
                if(model.getChange() == false)
                {
                    this.stop();
                }
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        
        /*
        if(model.getChange() == false)
        {
            JDialog d = new JDialog();
            d.setSize(100, 100);
            d.setVisible(true);
        }
        */ 
    }
}
