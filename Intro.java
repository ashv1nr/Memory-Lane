
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Intro extends Application
{
   private static int explaLabFtSize, explaLabX, explaLabY, btnW, btnH, btnFTSize, playBtnX, quitBtnX, btnY;
   private static double btnLengOfWidtPerct;
   private static String explaLabFT, explaLabText, btnFT, playBtnText, quitBtnText;
   private static Label explaLabel;
   private static Button playBtn, quitBtn;
   private static Group introLayout;
   private static Scene introScene;
   private static Main main;
   
   public Intro()
   {
      main = new Main();
      explaLabFtSize = 25;
      explaLabX = (int)((1/10.0) * main.getSceneW());
      explaLabY = (int)((1/10.0) * main.getSceneH());;
      explaLabFT = "Verdana";
      explaLabText = "Memorize the flashing yellow buttons and" + "\n" + "click them in order to advance to the next level." + "\n" + "Click the \"PLAY!\" button to advance" + "\n" + "or the \"QUIT\" button to end the program." ;
      explaLabel = new Label();
      btnW = 150;
      btnLengOfWidtPerct = .8;
      btnH = (int)(btnLengOfWidtPerct*btnW);
      playBtnX = (int)((1/4.0) * main.getSceneW());
      quitBtnX = (int)(((3/4.0)* main.getSceneW()) - btnW);
      btnY = (int)(((3/4.0)* main.getSceneH()) - btnH);
      btnFTSize = 20;
      btnFT = "Impact";
      playBtnText = "PLAY!";
      quitBtnText = "QUIT";
      playBtn = new Button();
      quitBtn = new Button();
      introLayout = new Group();
      introScene = new Scene(introLayout, main.getSceneW(), main.getSceneH());
   }
   
   public void setUpIntroUI()
   {
      explaLabel.setFont(Font.font(explaLabFT, explaLabFtSize));
      explaLabel.setText(explaLabText);
      explaLabel.setLayoutX(explaLabX);
      explaLabel.setLayoutY(explaLabY);
      
      playBtn.setFont(Font.font (btnFT, btnFTSize));
      playBtn.setText(playBtnText);
      playBtn.setPrefSize(btnW, btnH);
      playBtn.setLayoutX(playBtnX);
      playBtn.setLayoutY(btnY);
      playBtn.setOnAction(e -> main.playGame());
      
      quitBtn.setFont(Font.font (btnFT, btnFTSize));
      quitBtn.setText(quitBtnText);
      quitBtn.setPrefSize(btnW, btnH);
      quitBtn.setLayoutX(quitBtnX);
      quitBtn.setLayoutY(btnY);
      quitBtn.setOnAction(e -> main.endGame());
      
      introLayout.getChildren().addAll(explaLabel, playBtn, quitBtn);
      introScene.setRoot(introLayout);
   }
   
   public Scene getIntroScene()
   {
      return introScene;
   }
   
    //@Override
   public void start(Stage primaryStage) throws Exception
   {
     int x = -1;
   }
}