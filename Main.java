
import static java.lang.System.*;
import java.io.*;

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

public class Main extends Application
{
   private Path path;
   private static Path tempPath;
   private Stage window;
   private Scene scene;
   private static Label scoreLabel;
   private int score;
   
   public static void main(String[] args) throws IOException
   {      
      launch(args);
   }
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      this.score = 0;
      
      this.window = primaryStage;
      
      this.path = new Path();
      this.path.pathGen();
      this.tempPath = this.path;
      out.println(this.path);
      
     this.scoreLabel = new Label();
     this.scoreLabel.setFont(Font.font("Verdana", 20));
     this.scoreLabel.setText("score: " + this.score);
     this.scoreLabel.setLayoutX(650);
     this.scoreLabel.setLayoutY(20);
     this.path.getLayout().getChildren().add(this.scoreLabel);
     
     this.scene = new Scene(path.getLayout(), 780, 560);
     this.window.setScene(scene);   
     this.window.setTitle("Memory Lane");
     this.window.show();
     this.path.pauseP(2000, true);
    }
    
    public void setColor(int d)
    {
      this.tempPath.setColor(d);
    }
    
    public void updateScore(boolean b)
    {
      if(b == true)
      {
         this.score++;
         this.scoreLabel.setText("score: " + this.score);
      }
      else
      {
         this.score = 0;
         this.scoreLabel.setText("score: " + this.score);
      }
    }
}
