
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Grid extends Application
{
   private int by, sceneW, sceneH, boxDi, startX, startY;
   private double gridSize;
   private String btnColor1, btnColor2;
   private Group layout;
   private Node head, tail;
   private ArrayList<Node> nodeList;
   
   public Grid(int b, int sw, int sh)
   {
      this.by = b;
      this.sceneW = sw;
      this.sceneH = sh;
      this.gridSize = 450;
      this.boxDi = (int)(this.gridSize/this.by);
      this.startX = (int)((this.sceneW-this.gridSize)/2);
      this.startY = (int)(((this.sceneH-this.gridSize)/2) + this.gridSize - this.boxDi);
      this.btnColor1 = "#A7C7E7";
      this.btnColor2 = "#6F8FAF";
      this.layout = new Group();
      this.head = null;
      this.tail = null; 
      this.nodeList = new ArrayList<Node>();
   }
   
   public void gridGen(int b)
   {
      reset(b);
      
      int x = this.startX;
      int y = this.startY;
      int c = 0;
      for(int i = 1; i <= (Math.pow(this.by,2)); i++)
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
      addBtnsToLayout();
      breakSideLinks();
   }
   
   private void addX(int nn, int x, int y)
   {
      String oc = "";
      if(this.by%2 != 0)
      {
         if(nn%2 != 0)
         {
            oc = this.btnColor1;
         }
         else
         {
            oc = this.btnColor2;
         }
      }
      else
      {
         oc = "#B2BEB5";
      }
      
      Node n = new Node(nn, x, y, this.boxDi, oc);
      this.nodeList.add(n);
      
      if(this.tail == null)
      {
         this.head = n;
         this.tail = n;
      }
      else
      {
        n.left = this.tail;
        this.tail.right = n;
        this.tail = n; 
      }
   }
   
  private void addY()
   {
      Node temp = this.head;
      
      for(int i = 1; i <= (this.nodeList.size()-this.by); i ++)
      {
         Node temp2 = temp;
         for(int j = 1; j <= this.by; j ++)
         {
            temp2 = temp2.right;
         }
         temp2.down = temp;
         temp.up = temp2;
         
         temp = temp.right;
      }
   }
   
   private void setNodeColors()
   {
      Node temp = this.head;
      boolean bool = true;
      
      for(int i = 1; i <= this.by; i++)
      {
         for(int j = 1; j <= this.by; j++)
         {
            if(bool == true)
            {
               temp.setOrgiColor(this.btnColor1);
            }
            else
            {
               temp.setOrgiColor(this.btnColor2);
            }
            if(temp.up != null)
            { 
               temp = temp.up;
               bool = (!bool);
            }
         }
         while(temp.down != null)
         {
            temp = temp.down;
         }
         temp = temp.right;
      }
   }
   
   private void addBtnsToLayout()
   {
      Node temp = this.head;
      
      while(temp != null)
      {  
         this.layout.getChildren().add(temp.btn);
         temp = temp.right;
      }
   }
   
   private void breakSideLinks()
   {
      Node temp = this.head;
      
      for(int i = 1; i < this.by; i ++)
      {
         for(int j = 1; j <= this.by; j ++)
         {
            temp = temp.right;
         }
         temp.left.right = null;
         temp.left = null;
      }
   }
   
   private void reset(int b)
   {
      this.by = b;
      this.boxDi = (int)(this.gridSize/by);
      this.startX = (int)((this.sceneW-this.gridSize)/2);
      this.startY = (int)(((this.sceneH-this.gridSize)/2) + this.gridSize - this.boxDi);
      this.head = null;
      this.tail = null;
      for(Node temp: this.nodeList)
      {
         this.layout.getChildren().remove(temp.btn);
      }
      int s = this.nodeList.size();
      for(int i = 0; i < s; i ++)
      {
         this.nodeList.remove(0);
      }
   }
   
   public void drawGreenBtn(int i)
   {
      Node temp = nodeList.get(i-1);
      
      this.layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: #50C878; ");
      this.layout.getChildren().add(temp.btn); 
   }
   
   public void drawRedBtn(int i)
   {
      Node temp = nodeList.get(i-1);
      
      this.layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: #D22B2B; ");
      this.layout.getChildren().add(temp.btn); 
   }
   
   public void drawPathBtn(int i)
   {
      Node temp = nodeList.get(i-1);
      
      this.layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: #FDDA0D; ");
      this.layout.getChildren().add(temp.btn); 
   }
   
   public void setOgColorBtn(int i)
   {
      Node temp = nodeList.get(i-1);
      
      this.layout.getChildren().remove(temp.btn);
      temp.btn.setStyle("-fx-background-color: " + temp.orgiColor + "; ");
      this.layout.getChildren().add(temp.btn); 
   }
   
   public void setOgColorBtns(ArrayList<Integer> tempArr)
   {
      int s = tempArr.size();
      for(int i = 0; i < s; i ++)
      {
         setOgColorBtn(tempArr.get(i));
      }
   }
   
   public void unlockBtns()
   {
      for(int i = 0; i < this.nodeList.size(); i++)
      {
         this.nodeList.get(i).unlockBtn();
      }
   }
   
   public int getBy()
   {
      return this.by;
   }
   
   public int getLLSize()
   {
      return this.nodeList.size();
   }
   
   public Group getLayout()
   {
      return this.layout;
   }
   
   public Node getHead()
   {
      return this.head;
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
         temp = temp.left;
      }
      
      while(temp != null)
      {
         while(temp.right != null)
         {
            s += temp.nodeNum + " ";
            temp = temp.right;
         }
         s += temp.nodeNum + "\n";
         
         for(int i = 1; i < by; i ++)
         {
            temp = temp.left;
         }
         temp = temp.down;
      }
      
      return s;
   }
}
