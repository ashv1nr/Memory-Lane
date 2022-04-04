
import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Collections;
import javafx.util.Duration;

import javafx.animation.PauseTransition;  
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.Stage;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Path extends Application //extends JDrawingFrame //780 x 560
{
   private Grid grid;
   private Main main;
   private final int MAX;
   private int moves, tempInd, n;
   private boolean run, refresh, printToString;
   private String sol;
   private ArrayList<Integer> solArr;
   //private int[] resArr;
   
   public Path()
   {
      this.grid = new Grid(7);
      this.main = new Main();
      this.MAX = (int)( ( ( Math.pow(this.grid.getBY(),2) ) ) / 4.0 );
      this.moves = 0;
      this.tempInd = 0;
      this.n = 0;
      this.run = true;
      this.refresh = true;
      this.printToString = true;
      this.sol = " ";
      this.solArr = new ArrayList<Integer>();
   }
   
   public void pathGen()
   {  
      int n;
      Node temp;
      drawPathBtn();
      
      while(this.refresh == true)
      {
         this.grid.gridGen();
         if(printToString)
         {
            out.println(grid.toString());
            printToString = false;
         }
         this.sol = " ";
         this.solArr.clear();
         n = (int)( (Math.random() * this.grid.getBY() ) + 1 ); 
         this.moves = 0;
         temp = this.grid.getHead();
         for(int i = 1; i < n; i++)
         {
            temp = temp.next;
         }
         this.sol += temp.data + " ";
         this.solArr.add(temp.data);
         this.moves++;
         while( (this.run == true) && (this.moves <= this.MAX) )
         {
            if( (this.moves == this.MAX) && ( temp.data <= (this.grid.getLLSize() - this.grid.getBY()) ) )
            {
               this.run = true;
               this.moves++;
            }
            else if( ( (temp.next == null) && (temp.prev == null) && (temp.up == null) && (temp.down== null) ) && ( temp.data <= (this.grid.getLLSize() - this.grid.getBY()) ) )
            {
               this.run = true;
               this.moves = this.MAX+1;
            }
            else if( temp.data > (this.grid.getLLSize() - this.grid.getBY()) )
            {
               this.run = false;
               this.refresh = false;
            }
            else
            {
               if(this.moves < this.MAX)
               {
                  temp = pickDirc(temp);
                  this.sol += temp.data + " ";
                  this.solArr.add(temp.data);
                  this.moves++;
               }
            }
         }
      }
      
      //this.resArr = new int[this.solArr.size()];
   }
   
   private Node pickDirc(Node temp)
   {
      int n  = (int)( (Math.random() * 4 ) + 1 );
      
      if( (n == 1) && (temp.next != null) )
      {
         if(temp.up != null)
         {
            temp.up.down = null;
            temp.up = null;
         }
         
         if(temp.prev != null)
         {
            temp.prev.next = null;
            temp.prev = null;
         }
            
         if(temp.down != null)
         {
            temp.down.up = null;
            temp.down = null;
         }
         
         temp = temp.next;
         temp.prev = null;
         return temp;
      }
      else if( (n == 2) && (temp.prev != null) )
      {
         if(temp.up != null)
         {
            temp.up.down = null;
            temp.up = null;
         }
         
         if(temp.next != null)
         {
            temp.next.prev = null;
            temp.next = null;
         }
         
         if(temp.down != null)
         {
            temp.down.up = null;
            temp.down = null;
         }
         
         temp = temp.prev;
         temp.next = null;
         return temp;
      }
      else if( (n == 3) && (temp.up != null) )
      {
         if(temp.prev != null)
         {
            temp.prev.next = null;
            temp.prev = null;
         }
         
         if(temp.down != null)
         {
            temp.down.up = null;
            temp.down = null;
         }
         
         if(temp.next != null)
         {
            temp.next.prev = null;
            temp.next = null;
         }
         
         temp = temp.up;
         temp.down = null;
         return temp;
      }
      else if( (n == 4) && (temp.down != null) )
      {
         if(temp.prev != null)
         {
            temp.prev.next = null;
            temp.prev = null;
         }
         
         if(temp.up != null)
         {
            temp.up.down = null;
            temp.up = null;
         }
         
         if(temp.next != null)
         {
            temp.next.prev = null;
            temp.next = null;
         }
         
         temp = temp.down;
         temp.up = null;
         return temp;
      }
      else
      {
         return pickDirc(temp); 
      }
   }
   
   public Group getLayout()
   {
      return this.grid.getLayout();
   }
   
   private void drawPathBtn()
   {
      Button pathBtn = new Button();
      pathBtn.setFont(Font.font ("Impact", 14));
      pathBtn.setText("See path");
      pathBtn.setPrefSize(75, 25);
      pathBtn.setLayoutX(25);
      pathBtn.setLayoutY(510);
      pathBtn.setOnAction(e -> flashPath());
      this.grid.getLayout().getChildren().add(pathBtn); 
   }
   
   public void flashPath()
   {
      
      Grid g = this.grid;
      ArrayList<Integer> sa = this.solArr;
      
      Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        if(tempInd == 0)
                        {
                           g.drawPath(sa.get(tempInd));
                           tempInd++;
                        }
                        else if(tempInd < sa.size() )
                        {
                           g.drawPath(sa.get(tempInd));
                           g.removePath(sa.get((tempInd-1)));
                           tempInd++;
                        }
                        else
                        {
                           g.removePath(sa.get((tempInd-1)));
                           tempInd++;
                        }
                    }
                };

                
                while (tempInd < (sa.size()) ) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                    }
                    Platform.runLater(updater);
                }
            }

        });
        
        thread.setDaemon(true);
        thread.start();
        tempInd = 0; 
   }
         
   int scInc = 0;
   public void setColor(int d)
   {
     if(d == this.solArr.get(scInc))
     {
        this.grid.drawGreen(d);
        scInc++;
        if(scInc == this.solArr.size())
        {
            //this.grid.updateScore(true);
            reset(true);
        }
     }
     else
     {
        //this.grid.updateScore(false);
        this.grid.drawRed(d);
        pauseP(1000);
     }
   }
   
   private void pauseP(int ms)
   {
        Task<Void> sleeper = new Task<Void>() 
        {
            @Override
            protected Void call() throws Exception
            {
               try 
               {
                  Thread.sleep(ms);
               } 
               catch (InterruptedException e) 
               {
               }
               return null;
            }
       };
       sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() 
       {
            @Override
            public void handle(WorkerStateEvent event) 
            {
                exit(0);
            }
        });
        new Thread(sleeper).start();
   }
   
   private void clearSolArr()
   {
      int s = this.solArr.size();
      for(int i = 0; i < s; i ++)
      {
         this.solArr.remove(0);
      }
   }
   
   private void clearGridBtns(ArrayList<Integer> ta)
   {
      int s = ta.size();
      for(int i = 0; i < s; i ++)
      {
         this.grid.removePath(ta.get(i));
      }
      
     /* Grid g = this.grid;
      
      Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        if(tempInd == 0)
                        {
                           //int x = -1;
                           tempInd++;
                        }
                        else
                        {
                           g.removePath(ta.get((tempInd-1)));
                           //tempInd++;
                        }
                    }
                };
                
                while (tempInd < (ta.size()) ) {
                    try {
                        
                        if(tempInd == 0)
                        {
                           Thread.sleep(1000);
                        }
                        else
                        {
                           Thread.sleep(0);
                        }
                    } catch (InterruptedException ex) {
                    }
                    Platform.runLater(updater);
                }
            }
        });
         
        thr.setDaemon(true);
        thr.start();
        tempInd = 0; */
   }
   
   private void reset(boolean b)
   {
      this.moves = 0;
      this.tempInd = 0;
      this.n = 0;
      this.run = true;
      this.refresh = true;
      this.printToString = true;
      this.sol = " ";
      scInc = 0;
      ArrayList<Integer> tempArr = this.solArr;
      clearSolArr();
      this.main.updateScore(b);
      pathGen();
      clearGridBtns(tempArr);
      out.println(toString());
   }
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      int x = -1;
    }
      
   public String toString()
   {  
      return this.sol;
   }
}
