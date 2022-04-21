
import static java.lang.System.*;
import java.io.*;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Group;
/*import javafx.scene.media.*;
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView;*/
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application
{
   private static int score, sceneW, sceneH, refreshPauseLength, scoreLabelFtSize, scoreLabelX, scoreLabelY;
   private static String scoLabFT, windowTitle;
   private static Label scoreLabel;
   private static Scene scene;
   private static Stage window;
   private static Intro intro;
   private static Path path;
   
   public static void main(String[] args) throws IOException
   {      
      launch(args);
   }
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      ///////////////////////////////////makeshift constructor
      score = 0;
      sceneW = 780; //JDrawingFrame 780 x 560
      sceneH = 560;
      refreshPauseLength = 1500;
      
      scoreLabelFtSize = 20;
      scoreLabelX = 650;
      scoreLabelY = 20;
      scoLabFT = "Verdana";
      scoreLabel = new Label();
         
      windowTitle = "Memory Lane";
      window = primaryStage;
      
      intro = new Intro(); 
      path = new Path();
      scene = new Scene(path.getLayout(), sceneW, sceneH);
      ///////////////////////////////////
      
      setUpScoreLabel();
      intro.setUpIntroUI();
      window.setScene(intro.getIntroScene());
      window.setTitle(windowTitle);
      window.show();

     /*Media media = new Media("memoryLane.mp3");
     MediaPlayer mediaPlayer = new MediaPlayer(media);
     mediaPlayer.setAutoPlay(true);*/
    }
    
    public void playGame()
    {
      path.pathGen();
      //out.println(path);
      
      scene.setRoot(path.getLayout());
      window.setScene(scene);
     
      path.pauseP(refreshPauseLength, true);
    }
    
    public void endGame()
    {
      exit(0);
    }
    
    private void setUpScoreLabel()
    {
      scoreLabel.setFont(Font.font(scoLabFT, scoreLabelFtSize));
      scoreLabel.setText("score: " + score);
      scoreLabel.setLayoutX(scoreLabelX);
      scoreLabel.setLayoutY(scoreLabelY);
      path.getLayout().getChildren().add(scoreLabel);
    }
    
    public void updateScoreLabel(boolean bool)
    {
      if(bool == true)
      {
         score++;
         scoreLabel.setText("score: " + score);
      }
      else
      {
         score = 0;
         scoreLabel.setText("score: " + score);
      }
    }
    
    public static int getSceneW()
    {
      return sceneW;
    }
    
    public static int getSceneH()
    {
      return sceneH;
    }
    
    public static int getRefreshPauseLength()
    {
      return refreshPauseLength;
    }
    
    public static Path getPath()
    {
      return path;
    }
    
    public static void setIntroScene()
    {
      window.setScene(intro.getIntroScene());
    }
}
