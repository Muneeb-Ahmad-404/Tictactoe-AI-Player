import java.lang.classfile.instruction.ReturnInstruction;
import java.util.HashMap;
public class AIPlayer2 extends Player2{
    private HashMap<String, int[]> map;
    private final int InvalidErrorCode = 302;
    private final int win = 100;
    private final int loss = -100;
    private final int draw = 50;
    private final int pending = 0;

    public AIPlayer2(char symbol) {
        super(symbol);
        map = new HashMap<>();
    }
    @Override
    public int[] getMove(Board2 board) {
        if (map.containsKey(board.toString())) return map.get(board.toString());
        return move(board, symbol);
    }

    private int[] move(Board2 board, char mark){
        char nextMark = mark;
        for(char newMark : new char[]{'O', 'X'}){
            if (newMark != mark){
                nextMark = newMark;
            }
        }
        int initial_check = result(nextMark, board);
        if(initial_check != pending){
            return new int[]{-1, -1, initial_check};
        }
        int bestScore = mark == symbol ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestRow = 0;
        int bestCol= 0;

        for(int row = 0; row < board.getLength(); row++) {
            for (int col = 0; col < board.getLength(); col++) {
                if (board.getGrid()[row][col] == '-') {
                    Board2 newBoard = board.clone();
                    newBoard.makeMove(row, col, mark);
                    int[] result = move(newBoard, nextMark);
                    if(symbol == mark){
                        if (result[2] > bestScore) {
                            bestScore = result[2];
                            bestRow = row;
                            bestCol = col;
                            if(bestRow == 100) break;
                        }
                    }
                    else{
                        if(result[2] < bestScore){
                            bestScore = result[2];
                            bestRow = row;
                            bestCol = col;
                            if(bestScore == -100) break;
                        }
                    }
                    }
                }
            }
        int[] finalResult = new int[]{bestRow, bestCol, bestScore};
        map.put(board.toString(), finalResult);
        return finalResult;
    }
    private int result(char mark, Board2 board){
        int con = board.result(mark);
        if(symbol == mark){
            return con;
        }
        else{
            if(con == win) return loss;
            else if (con == loss) {
                return win;
            }
        }
        return con;
    }
}
