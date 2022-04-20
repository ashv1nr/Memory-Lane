
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Node extends Application
{
   public int nodeNum, btnX, btnY;
   public String orgiColor;
   public Button btn;
   public Node left, right, up, down;
   private Main main;
   
   public Node(int nn, int x, int y, int boxDi, String oc)
   {
      this.main = new Main();
      this.nodeNum = nn;
      this.btnX = x;
      this.btnY = y;
      this.orgiColor = oc;
      this.btn = new Button();
      //this.btn.setFont(Font.font ("Impact", 18));
      //this.btn.setText("" + this.nodeNum);
      this.btn.setPrefSize(boxDi, boxDi);
      this.btn.setLayoutX(this.btnX);
      this.btn.setLayoutY(this.btnY);
      this.btn.setStyle("-fx-background-color: " + this.orgiColor + "; ");
      this.left = null;
      this.right = null;
      this.up = null;
      this.down = null;
   }
   
   public void setOrgiColor(String oc)
   {
      this.orgiColor = oc;
      this.btn.setStyle("-fx-background-color: " + this.orgiColor + "; ");
   }  
   
   public void unlockBtn()
   {
      this.btn.setOnAction(e -> this.main.getPath().setBtnColor(this.nodeNum));
   }
   
    //@Override
    public void start(Stage primaryStage) throws Exception
    {
      int x = -1;
    }   
}
