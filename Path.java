
import static java.lang.System.*;

public class Path
{
   private Grid grid;
   private final int MAX;
   private int moves;
   private boolean run, refresh, printToString;
   private String path;
   public Path()
   {
      this.grid = new Grid(8);
      this.MAX = (int)( ( ( Math.pow(this.grid.getBY(),2) ) ) / 3.0 );
      this.moves = 0;
      this.run = true;
      this.refresh = true;
      this.printToString = true;
      this.path = "";
   }
   
   public void pathGen()
   {  
      int n;
      Node temp;
      
      while(this.refresh == true)
      {
         this.grid.gridGen();
         if(printToString)
         {
            out.println(grid.toString());
            printToString = false;
         }
         path = "";
         n = (int)( (Math.random() * this.grid.getBY() ) + 1 ); 
         this.moves = 0;
         temp = this.grid.getHead();
         for(int i = 1; i < n; i++)
         {
            temp = temp.next;
         }
         path += temp.data + " ";
         this.moves++;
         while( (this.run == true) && (this.moves <= this.MAX) )
         {
            if( (this.moves == this.MAX) && ( temp.data <= (this.grid.getSize() - this.grid.getBY()) ) )
            {
               this.run = true;
               this.moves++;
            }
            else if( ( (temp.next == null) && (temp.prev == null) && (temp.up == null) && (temp.down== null) ) && ( temp.data <= (this.grid.getSize() - this.grid.getBY()) ) )
            {
               this.run = true;
               this.moves = this.MAX+1;
            }
            else if( temp.data > (this.grid.getSize() - this.grid.getBY()) )
            {
               this.run = false;
               this.refresh = false;
            }
            else
            {
               if(this.moves < this.MAX)
               {
                  temp = pickDirc(temp);
                  path += temp.data + " ";
                  this.moves++;
               }
            }
         }
      }
   }
   
   private Node pickDirc(Node temp)
   {
      int n  = (int)( (Math.random() * 4 ) + 1 );
      
      if( (n == 1) && (temp.next != null) )
      {
         if(temp.up != null)
         {
            temp.up.down = null;
            temp.up = null;
         }
         
         if(temp.prev != null)
         {
            temp.prev.next = null;
            temp.prev = null;
         }
            
         if(temp.down != null)
         {
            temp.down.up = null;
            temp.down = null;
         }
         
         temp = temp.next;
         temp.prev = null;
         return temp;
      }
      else if( (n == 2) && (temp.prev != null) )
      {
         if(temp.up != null)
         {
            temp.up.down = null;
            temp.up = null;
         }
         
         if(temp.next != null)
         {
            temp.next.prev = null;
            temp.next = null;
         }
         
         if(temp.down != null)
         {
            temp.down.up = null;
            temp.down = null;
         }
         
         temp = temp.prev;
         temp.next = null;
         return temp;
      }
      else if( (n == 3) && (temp.up != null) )
      {
         if(temp.prev != null)
         {
            temp.prev.next = null;
            temp.prev = null;
         }
         
         if(temp.down != null)
         {
            temp.down.up = null;
            temp.down = null;
         }
         
         if(temp.next != null)
         {
            temp.next.prev = null;
            temp.next = null;
         }
         
         temp = temp.up;
         temp.down = null;
         return temp;
      }
      else if( (n == 4) && (temp.down != null) )
      {
         if(temp.prev != null)
         {
            temp.prev.next = null;
            temp.prev = null;
         }
         
         if(temp.up != null)
         {
            temp.up.down = null;
            temp.up = null;
         }
         
         if(temp.next != null)
         {
            temp.next.prev = null;
            temp.next = null;
         }
         
         temp = temp.down;
         temp.up = null;
         return temp;
      }
      else
      {
         return pickDirc(temp); 
      }
   }
      
   public String toString()
   {  
      return path;
   }
}  