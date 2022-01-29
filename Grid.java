
import static java.lang.System.*;
import java.io.*;

//import java.awt.*;
//import java.awt.Frame.*;
//import java.awt.Graphics.*;

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

public class Grid extends Application //extends JDrawingFrame //780 x 560
{
   private final int BY, FRAMEW, FRAMEH;
   private final double GRIDSIZE;
   private int size, boxDi, startX, startY, strwt;
   private Node head, tail;
   
   private Group layout;
   
   /*private Stage window;
   private Scene scene;
   private Group layout;*/
   
   public Grid(int by)
   {
      this.BY = by;
      this.FRAMEW = 780;
      this.FRAMEH = 560;
      this.GRIDSIZE = 450;
      this.boxDi = (int)(GRIDSIZE/BY);
      this.startX = (int)((FRAMEW-GRIDSIZE)/2);
      this.startY = (int)(((FRAMEH-GRIDSIZE)/2) + GRIDSIZE - boxDi);
      this.strwt = 2;
      this.head = null;
      this.tail = null;
      this.size = 0; 
      
      //window = primaryStage;
      //scene = new Scene(layout, 780, 560);
      layout = new Group();
   }
   
   public void gridGen()
   {
      clearGrid();
      
      int x = this.startX;
      int y = this.startY;
      int c = 0;
      for(int i = 1; i <= (Math.pow(BY,2)); i++)
      {
         if(c < this.BY)
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
      makeGrid();
      breakSides();
   }
   
   private void addX(int i,int x, int y)
   {
      Node n = new Node(i,x,y);
      
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
      
      for(int i = 1; i <= (this.size-this.BY); i ++)
      {
         Node temp2 = temp;
         for(int j = 1; j <= this.BY; j ++)
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
      
      for(int i = 1; i < this.BY; i ++)
      {
         for(int j = 1; j <= this.BY; j ++)
         {
            temp = temp.next;
         }
         temp.prev.next = null;
         temp.prev = null;
      }
   }
   
   private void clearGrid()
   {
      this.head = null;
      this.tail = null;
      this.size = 0;
   }
   
   private void makeGrid()
   {
      Node temp = this.head;
      
      while(temp != null)
      {
         /*setColor(Color.RED);
         pen.fillRect(temp.boxX, temp.boxY, boxDi, boxDi);
         setColor(Color.WHITE);
         pen.drawRect(temp.boxX, temp.boxY, boxDi, boxDi);*/
         
         temp.btn.setStyle("-fx-background-color: #D22B2B; ");
         temp.btn.setPrefSize(boxDi, boxDi);
         temp.btn.setLayoutX(temp.boxX);
         temp.btn.setLayoutY(temp.boxY);
         layout.getChildren().add(temp.btn);
         temp = temp.next;
      }
   }
   
   public void drawGreen(Node temp)
   {
      /*setColor(Color.GREEN);
      pen.fillRect(temp.boxX, temp.boxY, boxDi, boxDi);
      setColor(Color.WHITE);
      pen.drawRect(temp.boxX, temp.boxY, boxDi, boxDi);*/
      
      layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: #50C878; ");
      temp.btn.setPrefSize(boxDi, boxDi);
      temp.btn.setLayoutX(temp.boxX);
      temp.btn.setLayoutY(temp.boxY);
      layout.getChildren().add(temp.btn);
      
   }
   
   public Node getHead()
   {
      return this.head;
   }
   
   public Node getTail()
   {
      return this.tail;
   }
   
   public int getBY()
   {
      return this.BY;
   }
   
   public int getLLSize()
   {
      return this.size;
   }
   
   public Group getLayout()
   {
      return this.layout;
   }
   
   /*public void display()
   {
      //showFrame();
      
     scene = new Scene(layout, 780, 560);
     window.setScene(scene);   
     window.setTitle("Memory Lane");
     window.show();
   }*/
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      int x = -1;
    }
   
   public String toString()
   {  
      String s = "";
      /*s = "by: " + this.BY + "\nframe width: " + this.FRAMEW + "\nframe height: " + this.FRAMEH + "\ngridsize: " + this.GRIDSIZE + 
                  "\nbox dimension: " + this.boxDi + "\nstart x: " + this.startX + "\nstart y: " + this.startY;*/
      
      Node temp = this.tail;
      for(int i = 1; i < BY; i ++)
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
         
         for(int i = 1; i < BY; i ++)
         {
            temp = temp.prev;
         }
         temp = temp.down;
      }
      
      return s;
   }
}
