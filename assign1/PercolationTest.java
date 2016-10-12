public class PercolationTest {
    
    Percolation perc;
    int N;
    
    private PercolationTest() {
        N = 4;
        perc = new Percolation(N);
        
    }
    
    private void testGrid() {
        assert perc.grid.length == 16;
        
        for(int i=0; i<16; i++) {
            assert perc.grid[i] == 0;
        }
    }
    
    private void testDims2grid() {
        assert(2 == perc.dims2Grid(1, 3));
        assert(10 == perc.dims2Grid(3, 3));
    }
      
    private void testValidCell() {
        
    }
    
    private void testOpen() {
        perc.open(1,1);
        assert(1 == perc.grid[0]);
        perc.open(2, 2);
        int gridNum = perc.dims2Grid(2, 2);
        assert(1 == perc.grid[gridNum]);
        perc.open(1,2);

        assert(!perc.uf.connected(7,13));
    }
    
    private void printGrid() {
        for(int i=0; i<perc.grid.length; i++){
            if(i % N == 0) {
                System.out.println();
            }
            System.out.print(perc.grid[i]);
        }
        System.out.println();
    }
    
    private void testIsOpen() {
        perc.open(1,1);
        perc.open(3,3);
        
        assert(perc.isOpen(1,1));
        assert(perc.isOpen(3,3));
        
        assert(!perc.isOpen(4,3)); 
    }
    
    private void testIsFull() {
        perc.open(1,2);
        perc.open(2,2);
        assert(perc.isFull(2, 2));
        assert(!perc.isFull(3, 2));
    }
    
    private void testPercolates() {
        printGrid();
        for(int i=1; i<=4; i++) {
            perc.open(i, 3);
        }
        printGrid();
        System.out.println(perc.dims2Grid(1,3));
        System.out.println(perc.dims2Grid(4,3));
        assert(perc.uf.connected(2, 14));
        assert(perc.percolates());
                
    }
    
    public static void main(String[] args) {
        PercolationTest pt = new PercolationTest();
//        pt.testGrid();
//        pt.testDims2grid();
//        pt.testOpen();
//        pt.testIsOpen();
//        pt.testIsFull();
        pt.testPercolates();
        System.out.println("BOOM. All passed sucka.");
    }
}