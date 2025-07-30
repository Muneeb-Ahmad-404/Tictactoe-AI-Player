import java.util.HashMap;

public class AIPlayer  extends Player {
    private HashMap<String, int[]> situations;

    public AIPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public int[] getMove(Board board) {
        char[][] b = board.getBoard();
        if(situations.containsKey(b.toString())) {
            return situations.get(b.toString());
        }
        else{
            return aiMove(board, 0, 0, -5);
        }
    }

    private int[] aiMove(Board board, int row, int col, int score){
        if(reachedEnd(board, row, col) == 1){
            if(situations.containsKey(board.getBoard().toString())){
                if(score > situations.get(board.getBoard().toString())[2]){
                    situations.put(board.getBoard().toString(), new int[]{row, col, score});
                }
            }
            return new int[]{row, col, score};
        }
        else if(reachedEnd(board, row, col) == 0){
            if(situations.containsKey(board.getBoard().toString())){
                if(score > situations.get(board.getBoard().toString())[2]){
                    situations.put(board.getBoard().toString(), new int[]{row, col, score});
                }
            }
            return new int[]{row, col, score};
        }

        char[][] b = board.getBoard();
        for(int i = 0; i < board.getLength(); i++){
            for(int j = 0; j < board.getLength(); j++){
                if(b[i][j] == '-'){
                    b[i][j] = this.symbol;
                    humanMove(board);
                }
            }
        }

        return new int[]{0};
    }

    private int[] humanMove(Board board){
        char[][] b = board.getBoard();
        for(int i = 0; i < board.getLength(); i++){
            for(int j = 0; j < board.getLength(); j++){
                if(b[i][j] == '-'){
                    b[i][j] = this.symbol;
                    aiMove(new Board(b), i, j, -3);
                }
            }
        }
    }

    private int reachedEnd(Board board, int row, int col){
        return board.hasConcluded(row, col, this.symbol);
    }
}