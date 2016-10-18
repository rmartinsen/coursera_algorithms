import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Object[] queue;
    private int N;
    
    public RandomizedQueue(){
        queue = new Object[1];
        N = 0;
    }
    
    public boolean isEmpty(){
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private void resize(int newSize) {
       Object[] newQueue = new Object[newSize];
       for(int i=0; i< N; i++) {
          newQueue[i] = queue[i];
       }
       queue = newQueue;
    }
    
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException ("Can't be null bruh");
        }
        if(queue.length == 0){
          resize(1);   
        } else if(N >= queue.length) {
            resize(2 * queue.length);   
        }
        queue[N++] = item;
    }
    
    public Item dequeue(){    
        if (isEmpty()) {
            throw new NoSuchElementException();   
        }
        
        int selected = StdRandom.uniform(N);
        Item returnVal = (Item) queue[selected];
        queue[selected] = queue[N - 1];
        
        N--;
        
        if( N <= (queue.length / 4)){
            resize(queue.length /2);
        }
        return returnVal;
    }
    
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();   
        }
        int selected = StdRandom.uniform(N);
        return (Item) queue[selected];
    }
    
   public Iterator<Item> iterator() {return new RQIterator();}
       
   private class RQIterator implements Iterator<Item>{
       private Object[] iterQueue;
       private int iterN;
       
       public RQIterator() {
           iterQueue = new Object[N];
           iterN = N;
           for(int i=0; i<N; i++) {
              iterQueue[i] = queue[i];   
           }
       }
       
           public boolean hasNext() {
               return iterN > 0;   
           }
           
           public Item next() {
               if (!hasNext()) {
                   throw new NoSuchElementException("There is no next");   
               }
               
               
               int selected = StdRandom.uniform(iterN);
               Item returnVal = (Item) iterQueue[selected];
               iterQueue[selected] = iterQueue[iterN - 1];
               
               iterN--; 
               
               return returnVal;
              
           }
           
           public void remove() {
               throw new UnsupportedOperationException("no removing");
           }
   
   }
    
    public static void main(String[] args) {

    }
}
        