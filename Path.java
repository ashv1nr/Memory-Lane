
import static java.lang.System.*;

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

public class Path extends Application //extends JDrawingFrame //780 x 560
{
   private Grid grid;
   private final int MAX;
   private int moves;
   private boolean run, refresh, printToString;
   private String path;
   public Path()
   {
      this.grid = new Grid(8);
      this.MAX = (int)( ( ( Math.pow(this.grid.getBY(),2) ) ) / 3.0 );
      this.moves = 0;
      this.run = true;
      this.refresh = true;
      this.printToString = true;
      this.path = "";
   }
   
   public void pathGen()
   {  
      int n;
      Node temp;
      
      while(this.refresh == true)
      {
         this.grid.gridGen();
         if(printToString)
         {
            out.println(grid.toString());
            printToString = false;
         }
         path = "";
         n = (int)( (Math.random() * this.grid.getBY() ) + 1 ); 
         this.moves = 0;
         temp = this.grid.getHead();
         for(int i = 1; i < n; i++)
         {
            temp = temp.next;
         }
         path += temp.data + " ";
         grid.drawGreen(temp);
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
                  path += temp.data + " ";
                  grid.drawGreen(temp);
                  this.moves++;
               }
            }
         }
      }
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
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      int x = -1;
    }
      
   public String toString()
   {  
      //grid.display();
      return path;
   }
}  
