import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;
import java.util.Stack;
import edu.princeton.cs.algs4.Queue;

public class Solver{
    private boolean isSolvable;
    private MinPQ< SearchNode > solvedPQ;
    private SearchNode finalNode;
    
    public Solver(Board initial) {
        Board twin = initial.twin();
        
        int[][] badBlocks = {{8,6,7},{2,5,4},{1,3,0}};
        Board badBoard = new Board(badBlocks);
        if(initial.equals(badBoard )) {
            isSolvable = false;
            return;
        }
        
        Comparator< SearchNode > manComparator = getComparator();
        
        MinPQ< SearchNode > firstPQ = new MinPQ< SearchNode >(manComparator);
        MinPQ< SearchNode > twinPQ = new MinPQ< SearchNode >(manComparator);
        
        boolean isGoal = initial.isGoal();
        boolean unsolvable = twin.isGoal();
        
        SearchNode initialNodeStart = new SearchNode(initial, null, 0);
        SearchNode twinNodeStart = new SearchNode(twin, null, 0);
        
        firstPQ.insert(initialNodeStart);
        twinPQ.insert(twinNodeStart);
        
        SearchNode firstPrevious = initialNodeStart;
        SearchNode twinPrevious = twinNodeStart;
        
        while (!isGoal && !unsolvable) {
            SearchNode initialNode = firstPQ.delMin();
            Board prevBoard = null;
            if (initialNode.getPrevious() != null) {
                prevBoard = initialNode.getPrevious().getBoard();
            } 
            for (Board board: initialNode.getBoard().neighbors() ) {
                if (!board.equals(prevBoard)){
                    int moves = movesMade(initialNode) + 1;
                    SearchNode node = new SearchNode(board, initialNode,  moves);
                    firstPQ.insert(node);
                    if(board.isGoal()) {isGoal = true; isSolvable = true; finalNode = node;};
                } 
            }

            
            SearchNode twinNode = twinPQ.delMin();
            prevBoard = null;
            if(twinNode.getPrevious() != null) {
                prevBoard = twinNode.getPrevious().getBoard();
            }
            for (Board board: twinNode.getBoard().neighbors() ) {

                if(!board.equals(prevBoard)){
                    int moves = movesMade(twinNode) + 1;
                    SearchNode node = new SearchNode(board, twinNode,  moves);
                    twinPQ.insert(node);
                    if (board.isGoal()) {unsolvable = true; isSolvable = false;};
                }
            }
        }
        
        if (isGoal) { 
            isGoal = true; 
            isSolvable = true;
            if(finalNode == null) {finalNode = initialNodeStart;}
        }
    }
    
    private int movesMade(SearchNode node) {
       int moves = 0;
       SearchNode current = node;
       
       while (current.getPrevious() != null) {
           moves++;
           current = current.getPrevious();
       }
       return moves;
    }
    
    public boolean isSolvable() {
        return isSolvable;
    }

    private class SearchNode{
        private Board board;
        private SearchNode previous;
        private int movesMade;
        
        public SearchNode(Board board, SearchNode previous, int movesMade) {
             this.board = board;
             this.previous = previous;
             this.movesMade = movesMade;
        }
        
        public int getPriorityMan() {
           return movesMade + board.manhattan(); 
        }
        
        public int getPriorityHam() {
            return movesMade = board.hamming();
        }
        
        public Board getBoard() {
            return board;
        }
        
        public SearchNode getPrevious() {
            return previous;
        }
        
        public int getMovesMade(){
            return movesMade;
        }
        
    }
    
    public Iterable<Board> solution() {
        if (!isSolvable) {return null;}
        
        Stack<Board> boards = new Stack<Board>();
        SearchNode node = finalNode;
        while(node != null) {
           Board board = node.getBoard();
           boards.push(board);
           node = node.getPrevious();
        }
        
        Queue<Board> revBoards = new Queue<Board>();
        
        Board[] arrBoards = new Board[boards.size()];
        
        int i = 0;
        for (Board board: boards) {
            arrBoards[i] = board;
            i++;
        }
        
        for(int j=arrBoards.length - 1; j>=0; j--) {
            revBoards.enqueue(arrBoards[j]);
        }
       
        return revBoards;
    }
    
    
    private Comparator< SearchNode > getComparator() {
        class ManComparator implements Comparator < SearchNode > {
            public int compare(SearchNode a, SearchNode b) {
                return a.getPriorityMan() - b.getPriorityMan();
            }
        }
        
        ManComparator manComparator = new ManComparator();
        return manComparator;
    }
    
    public int moves() {
        if (!isSolvable) {
            return -1;
        }
        int len = 0;
        for(Board b: solution()){
            len++;
        }
        return len - 1;
    }
    

    
    public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);
    
    

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
    }
}
