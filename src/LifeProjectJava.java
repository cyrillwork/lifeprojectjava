/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeprojectjava;

/**
 *
 * @author Admin
 */
public class LifeProjectJava
{
    public static class Data extends java.util.Observable
    {
        public Data()
        {
            super();
            System.out.println("Constructor Data");
        }

        public Data(int n, int m)
        {
            super();
            System.out.println("Constructor Data n m");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static Data data1;
    
    public static void init()
    {
        data1 = new Data(10, 20);
        
        LifeModel l = new lifeprojectjava.LifeModel();
        
    }
    
    public static void main(String[] args)
    {
        //LifeModel m = new LifeModel(12, 12);
        //m.initLifeModel(11, 11);
        
        init();
        
        System.out.println("Start program");
        lifeprojectjava.MainJFrame frame = new lifeprojectjava.MainJFrame();
        frame.setVisible(true);
        System.out.println("End program");
        // TODO code application logic here
    }
}
