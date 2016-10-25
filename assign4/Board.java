import java.util.Stack;

public class Board{
    private int[][] blocks;
    public Board(int[][] blocks){
        this.blocks = blocks;
    }
    
    public int dimension() {
        return blocks[0].length;
    }
    
    public int hamming() {
        int totes = 0;
        for(int i = 0; i < blocks.length; i++){
            for(int j = 0; j < blocks.length; j++) {
                int actualValue = this.blocks[i][j];
                int rightValue = i * dimension() + j + 1;
                if(actualValue != rightValue && actualValue > 0) {
                    totes++;   
                }
            }
        }
        return totes;
    }
    
    public int manhattan() {
        int totes = 0;
        for(int i=0; i < blocks.length; i++) {
            for(int j=0; j < blocks.length; j++) {
                int value = blocks[i][j];
                if (value > 0) {
                    int actualRow = i;
                    int actualCol = j;
                    int rightRow = (value - 1) / dimension();
                    int rightCol = (value - 1) % dimension();
//                System.out.println("value:" + value + " actR: " + actualRow + " actC " + actualCol + " rightRow " + rightRow + " rightCol " + rightCol);
                    totes += Math.abs(actualRow - rightRow);
                    totes += Math.abs(actualCol - rightCol);
                }
            }
        }
        return totes;
    }
    
    public boolean isGoal() {
        for(int i = 0; i < dimension(); i++){
            for(int j =0; j < dimension(); j++){
               int rightValue = i * dimension() + j + 1;
               int actualValue = blocks[i][j];
               if(rightValue != actualValue && actualValue > 0) {
                   return false;
               }
            }
        }
        return true;
    }
    
    public Board twin() {
        int[][] newBlocks = new int[dimension()][dimension()];
        
        for(int i=0; i < dimension(); i++) {
            for(int j=0; j < dimension(); j++) {
                 newBlocks[i][j] = blocks[i][j];
            }
        }
        
        if(newBlocks[0][0] != 0 && newBlocks[0][1] != 0){
            int temp = newBlocks[0][0];
            newBlocks[0][0] = newBlocks[0][1];
            newBlocks[0][1] = temp;
        } else {
            int temp = newBlocks[1][0];
            newBlocks[1][0] = newBlocks[1][1];
            newBlocks[1][1] = temp;
        }
        Board twinBoard = new Board(newBlocks);  
        
        return twinBoard;
    }
        
    
    public boolean equals(Object y) {
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) {
            return false;
        }
        for(int i = 0; i < this.dimension(); i++) {
            for(int j = 0; j < this.dimension(); j++) {
                if(this.blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public Iterable<Board> neighbors() {
        int zeroI = 0;
        int zeroJ = 0;
        for(int i = 0; i < dimension(); i++) {
            for(int j = 0; j < dimension(); j++) {
                if (blocks[i][j] == 0){
                    zeroI = i;
                    zeroJ = j;
                }
            }
        }
        
        Stack<Board> boards = new Stack<Board>();
        //Move 0 left
        if(zeroJ > 0) {
            Board leftBoard = swapBlocks(zeroI, zeroJ, zeroI, zeroJ - 1);
            boards.push(leftBoard);
        }
        //Move 0 right
        if(zeroJ < dimension()) {
            Board rightBoard = swapBlocks(zeroI, zeroJ, zeroI, zeroJ + 1);
            boards.push(rightBoard);
        }
        //Move 0 up
        if(zeroI > 0) {
            Board upBoard = swapBlocks(zeroI, zeroJ, zeroI - 1, zeroJ);
            boards.push(upBoard);
        }
        //Move 0 down
        if(zeroI < dimension()) {
            Board downBoard = swapBlocks(zeroI, zeroJ, zeroI + 1, zeroJ);
            boards.push(downBoard);
        }
        return boards;   
    }
    
    private Board swapBlocks(int i1, int j1, int i2, int j2) {
        int[][] newBlocks = new int[dimension()][dimension()];

        for(int i=0; i < dimension(); i++) {
            for(int j=0; j < dimension(); j++) {
                 newBlocks[i][j] = blocks[i][j];
            }
        }
//        System.out.println(new Board(newBlocks));
        int temp = newBlocks[i1][j1];
        newBlocks[i1][j1] = newBlocks[i2][j2];
        newBlocks[i2][j2] = temp;
//        System.out.println("i1: " + i1 + " j1:" + j1 +" i2:" + i2 + " j2:" + j2);
        
//        System.out.println(new Board(newBlocks));
        
        return new Board(newBlocks);
        
    }
    
    public String toString() {

        String returnString = Integer.toString(dimension());
        returnString += "\n";
        for(int i = 0; i < dimension(); i++) {
            for(int j=0; j < dimension(); j++) {
                returnString += blocks[i][j] + " ";
            }
            returnString += "\n";
        }
        return returnString;
    }
    
}