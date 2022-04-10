
import static java.lang.System.*;

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
   private Main main;
   public Node prev, next, up, down;
   public int data, boxX, boxY;
   public String ogCol;
   public Button btn;
   
   public Node(int d, int x, int y, String oc)
   {
      this.main = new Main();
      this.prev = null;
      this.next = null;
      this.up = null;
      this.down = null;
      this.data = d;
      this.boxX = x;
      this.boxY = y;
      this.ogCol = oc;
      this.btn = new Button();
      this.btn.setStyle("-fx-background-color: " + this.ogCol + "; ");
      this.btn.setOnAction(e -> this.main.setColor(this.data));
   }
   
   public void setOgCol(String oc)
   {
      this.ogCol = oc;
      this.btn.setStyle("-fx-background-color: " + this.ogCol + "; ");
   }  
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      int x = -1;
    }   
}
