
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
   
   public static void main(String[] args) throws IOException
   {      
      launch(args);
   }
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      this.window = primaryStage;
      
      this.path = new Path();
      this.path.pathGen();
      this.tempPath = this.path;
      out.println(this.path);

     this.scene = new Scene(path.getLayout(), 780, 560);
     this.window.setScene(scene);   
     this.window.setTitle("Memory Lane");
     this.window.show();
    }
    
    public void setColor(int d)
    {
      this.tempPath.setColor(d);
    }
}
