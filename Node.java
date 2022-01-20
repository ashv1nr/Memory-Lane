
import static java.lang.System.*;

public class Node
{
   public static void main(String[] args)
   {
      Path path = new Path();
      path.pathGen();
      out.println(path);
   }
   
   public Node prev, next, up, down;
   public int data;
   
   public Node(int n)
   {
      this.prev = null;
      this.next = null;
      this.up = null;
      this.down = null;
      this.data = n;
   }
}
