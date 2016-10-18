import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
      int k = Integer.parseInt(args[0]);
      
      RandomizedQueue<String> rq = new RandomizedQueue<String>();
      
      String[] strs = StdIn.readAllStrings();
      
      for(String str: strs) {
          rq.enqueue(str);    
      }
      
      for(int i=0; i<k; i++) {
         System.out.println(rq.dequeue());
           
      }
      
    }
}