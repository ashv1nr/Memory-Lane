
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

public class Main extends Application
{
   private Stage window;
   private Scene scene;
   //public Group layout;
   
   public static void main(String[] args) throws IOException
   {      
      launch(args);
   }
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      window = primaryStage;
      
      Path path = new Path();
      path.pathGen();

     scene = new Scene(path.getLayout(), 780, 560);
     window.setScene(scene);   
     window.setTitle("Memory Lane");
     window.show();
      
     out.println(path);
    }
}