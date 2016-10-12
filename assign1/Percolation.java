import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;

public class Percolation{
    
    private int[] grid;
    private int N;
    private WeightedQuickUnionUF uf;
    private int virtualTop;
    private int virtualBottom;
    
    public Percolation(int n){
        if(n<=0) {
           throw new java.lang.IllegalArgumentException("gotta be positive, bro");   
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        N = n;
        grid = new int[n * n];
        
        virtualTop = n * n;
        virtualBottom = n * n + 1;
        
        for(int i=0; i<grid.length; i++) {
            grid[i] = 0;
        }

    }
  
    private int dims2Grid(int i, int j){
          return ((i - 1) * N) + j - 1;
    }

    private boolean isInvalidInputCell(int i, int j) {
        return i < 1 || j < 1 || i > N || j > N ;
    }
    
    public void open(int i, int j) {
        if (isInvalidInputCell(i, j)) {
            throw new java.lang.IndexOutOfBoundsException("That one doesn't work");   
        };
        int gridNum = dims2Grid(i, j);
        grid[gridNum] = 1;
        
        if(i > 0) { tryToUnion(i, j, i-1, j); };
        if(i < (N +1)) { tryToUnion(i, j, i+1, j); };
        if(j > 0) { tryToUnion(i, j, i, j-1); };
        if(j < (N + 1)) { tryToUnion(i, j, i, j+1); };
        
        if(i==1) { uf.union(gridNum, virtualTop); }
        if(i==N) { uf.union(gridNum, virtualBottom); }
    }
    
    private void tryToUnion(int p1, int q1, int p2, int q2) {
        if ( p2 < 1 || q2 < 1 || p2 > N || q2 > N ) { 
        } else {
            if(isOpen(p2, q2)) {
                int point1 = dims2Grid(p1, q1);
                int point2 = dims2Grid(p2, q2);
                uf.union(point1, point2);
            }
            
        }
    }
    
    public boolean isOpen(int i, int j) {
        if(isInvalidInputCell(i, j)) {
            throw new java.lang.IndexOutOfBoundsException("out of bounds");
        }
        if(!isInvalidInputCell(i, j)){
            int gridNum = dims2Grid(i, j);
            return grid[gridNum] == 1;
        } else {
             return false;
        }
    }
    
    public boolean isFull(int i, int j) {
        if(isInvalidInputCell(i, j)) {
            throw new java.lang.IndexOutOfBoundsException("out of bounds");
        }
        int point = dims2Grid(i, j);
        if (grid[point] == 0) { return false; }
        return uf.connected(point, virtualTop);
    }
    
    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }
    
    public static void main(String[] args) {
        Percolation perc = new Percolation(10);
        System.out.println(perc.isFull(6,11));
    }

}