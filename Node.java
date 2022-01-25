
import static java.lang.System.*;
import java.awt.*;
//import java.awt.Graphics.*;
//import java.awt.Frame.*;

/*import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;*/

public class Node
{
   public static void main(String[] args)
   {
      Path path = new Path();
      path.pathGen();
      out.println(path);
      //path.showFrame();
   }
   
   public Node prev, next, up, down;
   public int data, boxX, boxY;
   
   public Node(int n, int x, int y)
   {
      this.prev = null;
      this.next = null;
      this.up = null;
      this.down = null;
      this.data = n;
      this.boxX = x;
      this.boxY = y;
   }
}
