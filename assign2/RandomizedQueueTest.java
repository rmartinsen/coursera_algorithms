

public class RandomizedQueueTest {
   
    public static void main(String[] args) {
        RandomizedQueue rq = new RandomizedQueue();
        
        rq.enqueue("a");
        rq.enqueue("a");
        
        if(rq.size() != 2) {
             System.out.println("size should be 2");
        }
        
        String randval = (String) rq.dequeue();
        System.out.println(randval);
        if(randval != "a") {
            System.out.println("dequeued value should be 'a'");
        }
      
        if(rq.size() != 1) {
             System.out.println("size should be 1");
        }
        
        rq.dequeue();
        if (!rq.isEmpty()){
            System.out.println("isEmpty should be true");
        }
        
        rq.enqueue("d");
        
        if(rq.dequeue() != "d") {
            System.out.println("should be d");
        }
        
        rq.enqueue("e");
        rq.enqueue("f");
        
        for(Object item: rq){
          System.out.println( item);   
        }
    }
    
    
}