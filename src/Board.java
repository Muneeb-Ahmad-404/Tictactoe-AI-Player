import java.util.HashMap;

public class Board implements Cloneable{
    private char[][] board;
    private int movesMade;

    public Board(char[][] board){
        this.board = board;
        movesMade = 0;
    }

    public Board(char[][] board, int movesMade){
        this.board = board;
        this.movesMade = movesMade;
    }

    public int makeMove(int row, int col, char playerSymbol){
        if (isValidMove(row, col)){
            board[row][col] = playerSymbol;
            movesMade++;
            printBoard();
            return hasConcluded(row, col, playerSymbol);
        }
        else{
            if(isDraw()) return 0;
            System.out.println("Invalid move");
            return -2;
        }

    }

    public int hasConcluded(int row, int col, char playerSymbol){
        if (rowMatch(row, playerSymbol) || colMatch(col, playerSymbol) || diagonalMatch(playerSymbol)) return 1;
        else if (isDraw()) {
            return 0;
        }
        else return -1;
    }

    public char[][] getBoard(){
        return board;
    }

    public int getMovesMade(){return movesMade;}
    public int getLength(){
        return board.length;
    }

    private boolean isValidMove(int row, int col){
        if (row >= board.length || col >= board.length) return false;
        else if (row < 0 || col < 0) return false;
        else if (board[row][col] != '-') return false;
        else return true;
    }


    private boolean isDraw(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == '-'){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rowMatch(int row, char playerSymbol){
        for(int i = 0; i < board.length; i++){
            if(board[row][i] == playerSymbol){
                continue;
            }
            else return false;
        }
        return true;
    }

    private boolean colMatch(int col, char playerSymbol){
        for(int i = 0; i < board.length; i++){
            if(board[i][col] == playerSymbol){
                continue;
            }
            else return false;
        }
        return true;
    }

    private boolean diagonalMatch(char playerSymbol){
        return diagonal(playerSymbol) || antiDiagonal(playerSymbol);
    }

    private boolean antiDiagonal(char playerSymbol){
        for(int col = board.length - 1; col >= 0; col--){
            int row = board.length - 1 - col;
            if(board[row][col] != playerSymbol){return false;}
        }
        return true;
    }
    private boolean diagonal(char playerSymbol){
        for(int i = 0; i < board.length; i++){
            if(board[i][i] != playerSymbol) return false;
        }
        return true;
    }

    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Board clone(){
        char[][] b = new char[board.length][board.length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                b[i][j] = board[i][j];
            }
        }
        Board newBoard = new Board(b, movesMade);
        return newBoard;
    }

    public int currentStatus(){
        HashMap<Character, Integer> symbols = new HashMap<>();
        symbols.put('X', -3);
        symbols.put('O', 1);
        for(char symbol : symbols.keySet()){
            for(int i = 0; i < board.length; i++){
                if(rowMatch(i, symbol)) return symbols.get(symbol);
                if(colMatch(i, symbol)) return symbols.get(symbol);
            }
            if(diagonal(symbol)) return symbols.get(symbol);
            else if (antiDiagonal(symbol)) return symbols.get(symbol);
            else if (isDraw())return 0;
        }
        return -1;
    }


    public void placeMove(int row, int col, char playerSymbol){
        board[row][col] = playerSymbol;
    }
}