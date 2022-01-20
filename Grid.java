
import static java.lang.System.*;

public class Grid
{
   private final int BY;
   private int size;
   private Node head, tail;
   
   public Grid(int by)
   {
      this.BY = by;
      this.head = null;
      this.tail = null;
      this.size = 0;
   }
   
   public void gridGen()
   {
      clearGrid();
      for(int i = 1; i <= (Math.pow(BY,2)); i++)
      {
         addX(i);
      } 
      addY();
      breakSides();
   }
   
   private void addX(int x)
   {
      Node n = new Node(x);
      
      if(this.tail == null)
      {
         this.head = n;
         this.tail = n;
      }
      else
      {
        n.prev = this.tail;
        this.tail.next = n;
        this.tail = n; 
      }
      
      this.size++;
   }
   
  private void addY()
   {
      Node temp = this.head;
      
      for(int i = 1; i <= (this.size-this.BY); i ++)
      {
         Node temp2 = temp;
         for(int j = 1; j <= this.BY; j ++)
         {
            temp2 = temp2.next;
         }
         temp2.down = temp;
         temp.up = temp2;
         
         temp = temp.next;
      }
   }
   
   private void breakSides()
   {
      Node temp = this.head;
      
      for(int i = 1; i < this.BY; i ++)
      {
         for(int j = 1; j <= this.BY; j ++)
         {
            temp = temp.next;
         }
         temp.prev.next = null;
         temp.prev = null;
      }
   }
   
   public Node getHead()
   {
      return this.head;
   }
   
   public Node getTail()
   {
      return this.tail;
   }
   
   public int getBY()
   {
      return this.BY;
   }
   
   public int getSize()
   {
      return this.size;
   }
   
   private void clearGrid()
   {
      this.head = null;
      this.tail = null;
      this.size = 0;
   }
   
   public String toString()
   {  
      String s = "";
      
      Node temp = this.tail;
      for(int i = 1; i < BY; i ++)
      {
         temp = temp.prev;
      }
      
      while(temp != null)
      {
         while(temp.next != null)
         {
            s += temp.data + " ";
            temp = temp.next;
         }
         s += temp.data + "\n";
         
         for(int i = 1; i < BY; i ++)
         {
            temp = temp.prev;
         }
         temp = temp.down;
      }
      
      return s;
   }
}