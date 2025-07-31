import java.util.HashMap;
public class AIPlayer extends Player {
    private HashMap<String, int[]> map;  //int of the form row, col, score
    private final char humanSymbol;

    public AIPlayer(char symbol) {
        super('O');
        humanSymbol = 'X';
        map = new HashMap<String, int[]>();
    }

    public int[] getMove(Board board) {
        System.out.println("AI's Move");
        if (map.containsKey(board.toString())) {
            System.out.println("contains");
            return map.get(board.toString());
        } else {
            return minmaxMoves(board.clone(), symbol);
        }
    }

    private int[] minmaxMoves(Board board, char mark) {
        String key = board.toString();
        if(map.containsKey(key)){ return map.get(key);}

        //game end check
        int conclusion = board.currentStatus();

        if(conclusion != -1){
            if(conclusion == 1) return new int[]{-1, -1, 1};
            else if(conclusion == -3) return new int[]{-1, -1, -3};
            else if(conclusion == 0) return new int[]{-1, -1, 0};
        }


        int bestScore;
        int bestMoveRow = -1;
        int bestMoveCol = -1;

        if(mark == symbol){
            bestScore = Integer.MIN_VALUE;
            for(int i = 0; i < board.getLength(); i++) {
                for(int j = 0; j < board.getLength(); j++) {
                    if (board.getBoard()[i][j] == '-') {
                        Board newBoard =  board.clone();
                        newBoard.placeMove(i, j, mark);
                        int[] recursiveResult = minmaxMoves(newBoard, humanSymbol);
                        int score =  recursiveResult[2];
                        if (bestScore < score){
                            bestScore = score;
                            bestMoveRow = i;
                            bestMoveCol = j;
                        }
                    }
                }
            }
        }
        else{
            bestScore = Integer.MAX_VALUE;
            for(int i = 0; i < board.getLength(); i++) {
                for(int j = 0; j < board.getLength(); j++) {
                    if (board.getBoard()[i][j] == '-') {
                        Board newBoard =  board.clone();
                        newBoard.placeMove(i, j, mark);
                        int[] recursiveResult = minmaxMoves(newBoard, symbol);
                        int score =  recursiveResult[2];
                        if (bestScore > score){
                            bestScore = score;
                            bestMoveRow = i;
                            bestMoveCol = j;
                        }
                    }
                }
            }
        }
        int[] finalResult = new int[]{bestMoveRow, bestMoveCol, bestScore};
        if(!map.containsKey(key)){
            map.put(key, finalResult);
        }
        else if(map.containsKey(key) && map.get(key)[2]<bestScore){
            map.put(key, finalResult);
        }
        return map.get(key);
    }
}

//          9 places