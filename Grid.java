
import static java.lang.System.*;
import java.util.ArrayList;

import javafx.application.Application;
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

public class Grid extends Application //JDrawingFrame 780 x 560
{
   private final int FRAMEW, FRAMEH;
   private final double GRIDSIZE;
   private int by, size, boxDi, startX, startY;
   private String color1, color2;
   private Node head, tail;
   private Group layout;
   private ArrayList<Node> nodes;
   
   public Grid(int b)
   {
      this.by = b;
      this.FRAMEW = 780;
      this.FRAMEH = 560;
      this.GRIDSIZE = 450;
      this.boxDi = (int)(GRIDSIZE/by);
      this.startX = (int)((FRAMEW-GRIDSIZE)/2);
      this.startY = (int)(((FRAMEH-GRIDSIZE)/2) + GRIDSIZE - boxDi);
      this.color1 = "#A7C7E7";
      this.color2 = "#6F8FAF";
      this.head = null;
      this.tail = null;
      this.size = 0; 
      this.layout = new Group();
      this.nodes = new ArrayList<Node>();
   }
   
   public void gridGen(int b)
   {
      reset(b);
      
      int x = this.startX;
      int y = this.startY;
      int c = 0;
      for(int i = 1; i <= (Math.pow(by,2)); i++)
      {
         if(c < this.by)
         {
            addX(i,x,y);
            x += this.boxDi;
            c++;
         }
         else
         {
            x = this.startX;
            y -= this.boxDi;
            c = 1;
            addX(i,x,y);
            x += this.boxDi;
         }
      }  
      
      addY();
      if(this.by%2 == 0)
      {
         setNodeColors();
      }
      makeGrid();
      breakSides();
   }
   
   private void addX(int d, int x, int y)
   {
      String oc = "";
      if(this.by%2 != 0)
      {
         if(d%2 != 0)
         {
            oc = this.color1;
         }
         else
         {
            oc = this.color2;
         }
      }
      else
      {
         oc = "#B2BEB5";
      }
      
      Node n = new Node(d, x, y, oc);
      this.nodes.add(n);
      
      if(this.tail == null)
      {
         this.head = n;
         this.tail = n;
      }
      else
      {
        n.prev = this.tail;
        this.tail.next = n;
        this.tail = n; 
      }
      
      this.size++;
   }
   
  private void addY()
   {
      Node temp = this.head;
      
      for(int i = 1; i <= (this.size-this.by); i ++)
      {
         Node temp2 = temp;
         for(int j = 1; j <= this.by; j ++)
         {
            temp2 = temp2.next;
         }
         temp2.down = temp;
         temp.up = temp2;
         
         temp = temp.next;
      }
   }
   
   private void breakSides()
   {
      Node temp = this.head;
      
      for(int i = 1; i < this.by; i ++)
      {
         for(int j = 1; j <= this.by; j ++)
         {
            temp = temp.next;
         }
         temp.prev.next = null;
         temp.prev = null;
      }
   }
   
   private void setNodeColors()
   {
      Node temp = this.head;
      boolean f = true;
      
      for(int i = 1; i <= this.by; i++)
      {
         for(int j = 1; j <= this.by; j++)
         {
            if(f == true)
            {
               temp.setOgCol(this.color1);
            }
            else
            {
               temp.setOgCol(this.color2);
            }
            if(temp.up != null)
            { 
               temp = temp.up;
               f = (!f);
            }
         }
         while(temp.down != null)
         {
            temp = temp.down;
         }
         temp = temp.next;
      }
   }
   
   private void reset(int b)
   {
      this.by = b;
      this.boxDi = (int)(GRIDSIZE/by);
      this.startX = (int)((FRAMEW-GRIDSIZE)/2);
      this.startY = (int)(((FRAMEH-GRIDSIZE)/2) + GRIDSIZE - this.boxDi);
      this.head = null;
      this.tail = null;
      this.size = 0; 
      for(Node temp: this.nodes)
      {
         this.layout.getChildren().remove(temp.btn);
      }
      int s = this.nodes.size();
      for(int i = 0; i < s; i ++)
      {
         this.nodes.remove(0);
      }
   }
   
   private void makeGrid()
   {
      Node temp = this.head;
      
      while(temp != null)
      {  
         temp.btn.setFont(Font.font ("Impact", 18));
         //temp.btn.setText("" + temp.data);
         temp.btn.setPrefSize(boxDi, boxDi);
         temp.btn.setLayoutX(temp.boxX);
         temp.btn.setLayoutY(temp.boxY);
         this.layout.getChildren().add(temp.btn);
         temp = temp.next;
      }
   }
   
   public void drawGreen(int i)
   {
      Node temp = nodes.get(i-1);
      
      this.layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: #50C878; ");
      temp.btn.setFont(Font.font ("Impact", 18));
      //temp.btn.setText("" + temp.data);
      temp.btn.setPrefSize(boxDi, boxDi);
      temp.btn.setLayoutX(temp.boxX);
      temp.btn.setLayoutY(temp.boxY);
      this.layout.getChildren().add(temp.btn); 
   }
   
   public void drawRed(int i)
   {
      Node temp = nodes.get(i-1);
      
      this.layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: #D22B2B; ");
      temp.btn.setFont(Font.font ("Impact", 18));
      //temp.btn.setText("" + temp.data);
      temp.btn.setPrefSize(boxDi, boxDi);
      temp.btn.setLayoutX(temp.boxX);
      temp.btn.setLayoutY(temp.boxY);
      this.layout.getChildren().add(temp.btn); 
   }
   
   public void drawPath(int i)
   {
      Node temp = nodes.get(i-1);
      
      this.layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: #FDDA0D; ");
      temp.btn.setFont(Font.font ("Impact", 18));
      //temp.btn.setText("" + temp.data);
      temp.btn.setPrefSize(boxDi, boxDi);
      temp.btn.setLayoutX(temp.boxX);
      temp.btn.setLayoutY(temp.boxY);
      this.layout.getChildren().add(temp.btn); 
   }
   
   public void removePath(int i)
   {
      Node temp = nodes.get(i-1);
      
      this.layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: " + temp.ogCol + "; ");
      temp.btn.setFont(Font.font ("Impact", 18));
      //temp.btn.setText("" + temp.data);
      temp.btn.setPrefSize(boxDi, boxDi);
      temp.btn.setLayoutX(temp.boxX);
      temp.btn.setLayoutY(temp.boxY);
      this.layout.getChildren().add(temp.btn); 
   }
   
   public void clearGridBtns(ArrayList<Integer> ta)
   {
      int s = ta.size();
      for(int i = 0; i < s; i ++)
      {
         removePath(ta.get(i));
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
   
   public Node getHead()
   {
      return this.head;
   }
   
   public Node getTail()
   {
      return this.tail;
   }
   
   public int getBy()
   {
      return this.by;
   }
   
   public int getLLSize()
   {
      return this.size;
   }
   
   public Group getLayout()
   {
      return this.layout;
   }
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      int x = -1;
    }
   
   public String toString()
   {  
      String s = "";
      
      Node temp = this.tail;
      for(int i = 1; i < by; i ++)
      {
         temp = temp.prev;
      }
      
      while(temp != null)
      {
         while(temp.next != null)
         {
            s += temp.data + " ";
            temp = temp.next;
         }
         s += temp.data + "\n";
         
         for(int i = 1; i < by; i ++)
         {
            temp = temp.prev;
         }
         temp = temp.down;
      }
      
      return s;
   }
}
