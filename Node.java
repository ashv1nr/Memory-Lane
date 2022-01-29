
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

public class Node extends Application
{
   /*public static void main(String[] args)
   {
      Path path = new Path();
      path.pathGen();
      out.println(path);
   }*/
   
   public Node prev, next, up, down;
   public int data, boxX, boxY;
   public Button btn;
   
   public Node(int n, int x, int y)
   {
      this.prev = null;
      this.next = null;
      this.up = null;
      this.down = null;
      this.data = n;
      this.boxX = x;
      this.boxY = y;
      
      btn = new Button();
   }
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      int x = -1;
    }
}
