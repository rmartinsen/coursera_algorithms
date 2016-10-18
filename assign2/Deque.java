import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

   private Node first;
   private Node last;
   private int dequeLength;
    
   public Deque() {
       dequeLength = 0;
   }
   
   private class Node {
      public Node next;
      public Node prev;
      public Object item;
      
      public Node(Object item) {
         this.item = item;    
      }
      
      public void setNext(Node next) {
             this.next = next;   
      }
      
      public void setPrev(Node prev) {
          this.prev = prev;   
      }
   }
   
   public boolean isEmpty() {
       return first == null;
   }
   
   public int size() {
       return dequeLength;
   }
   
   public void addFirst(Item item) {
       if (item == null) {
           throw new NullPointerException("Can't be null");   
       }
       
       Node node = new Node(item);
       if (dequeLength == 0){
          last = node;
       } else {
          first.prev = node;
          node.next = first;
       }
       first = node;
       dequeLength++;
           
   }
   
   public void addLast(Item item) {
       if (item == null) {
           throw new NullPointerException("Can't be null");   
       }
       
       Node node = new Node(item);
       if (dequeLength == 0) {
          first = node;   
       } else {
          last.setNext(node);   
          node.setPrev(last);
       }
       last = node;
       dequeLength++;
   }
   
   public Item removeFirst() {
       if (dequeLength == 0) {
           throw new NoSuchElementException("No item to remove");   
       }
       
       dequeLength--;
       
       Node returnNode = first;
       if(dequeLength > 0) {
           Node newFirst = first.next;
           first = null;
           first = newFirst;
           first.setPrev(null);
       } else {
           first = null;
           last = null;
       }

       return (Item) returnNode.item;
   }
   
   public Item removeLast() {
       if (dequeLength == 0) {
           throw new NoSuchElementException("No item to remove");   
       }
       
       dequeLength--;
       
       Node returnNode = last;
       if(dequeLength > 0) {
           Node newLast = last.prev;
           last = null;
           newLast.setNext(null);
           last = newLast;
       } else {
           first = null;
           last = null;
       }
       
       return (Item) returnNode.item;
   }
   
   public Iterator<Item> iterator() {return new DequeIterator();}
       
   private class DequeIterator implements Iterator<Item>{
       private Node current;
       
       public DequeIterator() {
           if (first != null) {
               current = first;
           } 
       }
       
           public boolean hasNext() {
               return current != null;   
           }
           
           public Item next() {
               
               if (current == null) {
                   throw new NoSuchElementException("There is no next");   
               }
               Item returnItem = (Item) current.item;
               
               current = current.next;
               return returnItem;
           }
           
           public void remove() {
               throw new UnsupportedOperationException("no removing");
           }
   
   }
       

   
   
   public static void main(String[] args) {
       Deque<String> d = new Deque<String>();

       d.addFirst("a");
       d.removeFirst();
       d.addLast("c");
       d.addFirst("b");       
       d.addLast("d");
       d.addLast("e");
       
        d.addLast("1");
          d.addFirst("2");
          d.removeFirst() ; 
          d.removeFirst() ;
       
       for(Object item: d){
          System.out.println( item);   
       }

       
       
   }
}
