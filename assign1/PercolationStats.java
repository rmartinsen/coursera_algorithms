import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats{
    
    private int[] results;
    private int N;
    
    public PercolationStats(int n, int trials) {
        if(n<=0 || trials<=0) {
            throw new java.lang.IllegalArgumentException();   
        }
        
        N = n;
        results = new int[trials];
        for(int i=0; i<trials; i++) {
              results[i] = runTrial();
        }
    }
    
    private int runTrial(){
       int count = 0;
       Percolation perc = new Percolation(N);
       while(!perc.percolates()) {
          int p = StdRandom.uniform(N) + 1;
          int q = StdRandom.uniform(N) + 1;
          if(!perc.isOpen(p, q)) {
              perc.open(p, q);
              count++;
          }
       }
       return(count);
    }
    
    public double mean(){
        return StdStats.mean(results);
    }
    
    public double stddev(){
        return StdStats.stddev(results);
    }
    
    public double confidenceLo(){
        return mean() - ((1.96 * stddev()) / results.length);
    }
    
    public double confidenceHi(){
        return mean() + ((1.96 * stddev()) / results.length);
    }
    
    private void printResults() {
        for(int i: results) {
          System.out.print(i);
          System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
    