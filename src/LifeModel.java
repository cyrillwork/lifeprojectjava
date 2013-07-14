/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeprojectjava;

//import java.util.Observable;
import java.util.Random;
import java.util.Arrays;
/**
 *
 * @author Admin
 */
public class LifeModel extends java.util.Observable implements java.io.Serializable
{
    private int N;
    private int M;
    private int Delay;
    private boolean arrayLife[][];
    private boolean arrayLifeInit[][];
    private int countSteps;
    private boolean isChange;
        
    public LifeModel()
    {
        super();
        System.out.println("Constructor LifeModel ");
        this.N = 20;
        this.M = 20;
        this.Delay = 900;
        countSteps = 0;
        arrayLife = new boolean[N][M];
        arrayLifeInit = new boolean[N][M];
        isChange = true;
        //arrayLife = new boolean[N][M];
        //System.out.println("1 array=" + arrayLife);
        //setRandom();
    };
    
    public LifeModel(int n, int m, int delay)
    {
        super();
        System.out.println("Constructor LifeModel (n, m, delay)");
        this.N = n;
        this.M = m;
        this.Delay = delay;
        countSteps = 0;
        arrayLife = new boolean[N][M];
        arrayLifeInit = new boolean[N][M];
        isChange = true;
        //arrayLife = new boolean[N][M];
        //System.out.println("1 array=" + arrayLife);
        //setRandom();
    };

    public void backToInit()
    {
        countSteps = 0;
        this.arrayLife = this.arrayLifeInit.clone();        
    }
    
    public void setRandom()
    {
        countSteps = 0;
        
        Random rand = new Random(); 
        for(int i = 0; i <N; i++)
        {
            for(int j = 0; j<M; j++)
            {
                arrayLife[i][j] = rand.nextBoolean();
            }
        }
    }
    
    public void stepLife()
    {
        boolean [][]temp1 = new boolean[N][M];
        
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                int count1 = 0;
                boolean b1 = arrayLife[i][j];
                boolean []temp2 = new boolean[8];

                    if ((i == 0) || (j == 0))
                    {
                        temp2[0] = false;
                    }
                    else
                    {
                        temp2[0] = arrayLife[i - 1][j - 1];
                    }

                    if (i == 0)
                    {
                        temp2[1] = false;
                    }
                    else
                    {
                        temp2[1] = arrayLife[i - 1][ j];
                    }

                    if ((i == 0) || (j == M - 1))
                    {
                        temp2[2] = false;
                    }
                    else
                    {
                        temp2[2] = arrayLife[i - 1][ j + 1];
                    }

                    if (j == M - 1)
                    {
                        temp2[3] = false;
                    }
                    else
                    {
                        temp2[3] = arrayLife[i][j + 1];
                    }

                    if ((i == N - 1) || (j == M - 1))
                    {
                        temp2[4] = false;
                    }
                    else
                    {
                        temp2[4] = arrayLife[i + 1][ j + 1];
                    }

                    if (i == N - 1)
                    {
                        temp2[5] = false;
                    }
                    else
                    {
                        temp2[5] = arrayLife[i + 1][ j];
                    }

                    if ((i == N - 1) || (j == 0))
                    {
                        temp2[6] = false;
                    }
                    else
                    {
                        temp2[6] = arrayLife[i + 1][ j - 1];
                    }

                    if ((i == 0) || (j == 0))
                    {
                        temp2[7] = false;
                    }
                    else
                    {
                        temp2[7] = arrayLife[i][j - 1];
                    }

                    for (int iii = 0; iii < 8; iii++)
                    {
                        if (temp2[iii])
                        {
                            count1++;
                        }
                    }
                    if (b1)
                    {
                        temp1[i][j] = false;
                        if((count1 == 2) || (count1 == 3))
                        {
                            temp1[i][j] = true;
                        }
                    }
                    else
                    {
                        temp1[i][j] = arrayLife[i][j];
                        if (count1 == 3)
                        {
                            temp1[i][j] = true;
                        }
                    }
                }
            }
            
            //System.out.println("current step " + this.countSteps);
            
            if(this.countSteps == 0)
            {
                this.arrayLifeInit = this.arrayLife.clone();
                isChange = true;
            }
            else
            {
                if(equals(this.arrayLife, temp1))
                {
                    System.out.println("!!!!! stop game!!!!!!!!");
                    isChange = false;
                }
                else
                {
                    System.out.println("!!!!! not equal!!!!!!!!");
                }
            }
            
            this.countSteps = this.countSteps + 1;
                
            
            arrayLife = temp1;
        }

    
    public boolean[][] getArray()
    {
        System.out.println("clone Life array=" + arrayLife);
        return arrayLife.clone();
    }
    public int getN()
    {
        return N;
    }
    public int getM()
    {
        return M;
    }
    public int getDelay()
    {
        return Delay;
    }
    
    public void setData(int n, int m, int delay)
    {
        this.N = n;
        this.M = m;
        this.Delay = delay;
        countSteps = 0;
        arrayLife = new boolean[N][M];
        arrayLifeInit = new boolean[N][M];        
    }
    
    public void changeCell(int i, int j)
    {
        arrayLife[i][j] = !arrayLife[i][j];
    }
    
    public void setClear()
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                countSteps = 0;
                arrayLife[i][j] = false;
                arrayLifeInit[i][j] = false;        
            }
        }
        countSteps = 0;
    }
    
    public boolean getChange()
    {
        return isChange;
    }
    
    public int getSteps()
    {
        return countSteps;
    }
    
    public boolean equals(boolean[][] array1, boolean[][] array2)
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if(array1[i][j] != array2[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
}
