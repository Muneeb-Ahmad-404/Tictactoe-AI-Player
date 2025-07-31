import java.util.HashMap;
public class AIPlayer extends Player {
    private HashMap<String, int[]> map;  //int of the form row, col, score
    private char humanSymbol;

    public AIPlayer(char symbol) {
        super(symbol);
        if(symbol == 'O'){
            humanSymbol = 'X';
        }
        else{
            humanSymbol = 'O';
        }
        map = new HashMap<String, int[]>();
    }
    public int[] getMove(Board board) {
        if(map.containsKey(board.toString())) {
            System.out.println("contains");
            return map.get(board.toString());
        }
        else{
            aiMoves(board.clone());
            return map.get(board.toString());
        }
    }
    private void aiMoves(Board board) {     //gets the update board after every human move..
        char[][] b = board.getBoard();
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){   //check all empty places and try them with supportAI
                if(b[i][j] == '-'){
                    Board board2 = board.clone();
                    char[][] b2 = b.clone();
                    b2 [i][j] = symbol;   //try the move
                    board2.setBoard(b2);  //update the board with that move
                    System.out.println("Ai's move");
                    board2.printBoard();
                    int conclusion = board2.hasConcluded(i, j, symbol);      //move's conclusion for base_case.
                    System.out.println("conclusion: " + conclusion);
                    if (conclusion != -1){
                        if(map.containsKey(board.toString()) && map.get(board.toString())[2] < conclusion){
                            map.put(board.toString(), new int[]{i, j, conclusion});    //for base case if the score stored is smaller update it.
                            System.out.println("An end move by ai"+conclusion);
                        }
                        else{
                            map.put(board.toString(), new int[]{i, j, conclusion});//if the record does not exist.
                        }
                        return;
                    }
                    System.out.println("Not an end move");
                    humanMoves(board2.clone());      //ask for next human move.
                }
            }
        }
    }


    private void humanMoves(Board board){           //gets board after every ai_move.
        char[][] b = board.getBoard();
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){
                if(b[i][j] == '-'){
                    char[][] b2 = b.clone();
                    b2[i][j] = humanSymbol;      //update the '-' place
                    Board board2 = board.clone();
                    board2.setBoard(b);//update the board to send to AI for move.
                    System.out.println("Human move: ");
                    board2.printBoard();
                    int conclusion = board2.hasConcluded(i, j, humanSymbol);
                    System.out.println("conclusion: " + conclusion);
                    if (conclusion != -1){
                        if(conclusion == 1){
                            conclusion = -3;//if human wins its -3 for AI...
                        }
                        if(map.containsKey(board.toString()) && map.get(board.toString())[2] < conclusion){
                            map.put(board.toString(), new int[]{i, j, conclusion});
                        }//if the new result is a draw for AI rather than a loss update it.
                        else{
                            map.put(board.toString(), new int[]{i, j, conclusion});
                        }
                        System.out.println("An end move by human"+conclusion);
                        return;
                    }
                    aiMoves(board.clone());
                    i --;
                    j --;
                }
            }
        }
    }
}