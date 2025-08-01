public class Board2 implements Cloneable{
    private char[][] grid;
    private int movesMade;
    private final int InvalidErrorCode = 302;
    private final int win = 100;
    private final int loss = -100;
    private final int draw = 50;
    private final int pending = 0;


    public Board2(int size) {
        grid = new char[size][size];
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                grid[row][col] = '-';
            }
        }
        movesMade = 0;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public int getMovesMade() {
        return movesMade;
    }

    public void setMovesMade(int movesMade) {
        this.movesMade = movesMade;
    }
    public int getLength(){
        return grid.length;
    }

    public Board2 clone(){
        Board2 clone = new Board2(this.grid.length);
        for(int row = 0; row < this.grid.length; row++){
            for(int col = 0; col < this.grid.length; col++){
                clone.grid[row][col] = (char) this.grid[row][col];
            }
        }
        clone.movesMade = this.movesMade;
        return clone;
    }

    public int makeMove(int row, int col, char symbol){
        if(!isValid(row, col)){return InvalidErrorCode;}
        grid[row][col] = symbol;
        movesMade++;
        printBoard();
        return result(symbol);
    }

    public int result(char symbol){
        if(hasWon(symbol)){return win;}
        else if(isDraw()) return draw;
        else{return pending;}
    }

    public void printBoard(){
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid.length; col++){
                System.out.print(grid[row][col]+" ");
            }
            System.out.println();
        }
    }

    private boolean isValid(int row, int col){
        //out of range, negative and occupied-space check.
        if(row < 0 || col < 0){return false;}
        else if(row >= grid.length || col >= grid.length){return false;}
        else if (grid[row][col] != '-'){
            return false;}
        return true;
    }
    private boolean isDraw(){
        for(int row = 0; row < this.grid.length; row++){
            for(int col = 0; col < this.grid.length; col++){
                if (grid[row][col] == '-'){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean hasWon(char symbol){
        return rowWin(symbol) || colWin(symbol) || diagonalWin(symbol) || antiDiagonalWin(symbol);
    }

    private boolean hasLost(char symbol){
        char[] symbols = new char[]{'O', 'X'};
        for(char symbol1 : symbols){
            if(symbol != symbol1){
                if(hasWon(symbol1)){return true;}
            }
        }
        return false;
    }

    private boolean rowWin(char symbol){
        for (int row = 0; row < this.grid.length; row++){
            boolean win = true;
            for (int col = 0; col < this.grid.length; col++){
                if(grid[row][col] != symbol){win = false;}
            }
            if(win){return win;}
        }
        return false;
    }

    private boolean colWin(char symbol){
        for (int col = 0; col < this.grid.length; col++){
            boolean win = true;
            for (int row = 0; row < this.grid.length; row++){
                if(grid[row][col] != symbol){win = false;}
            }
            if(win){return win;}
        }
        return false;
    }

    private boolean diagonalWin(char symbol){
        boolean win = true;
        for(int row = 0; row < this.grid.length; row++){
            int col = row;
            if(grid[row][col] != symbol){return false;}
        }
        return win;
    }
    private boolean antiDiagonalWin(char symbol){
        boolean win = true;
        for(int col = grid.length-1; col >= 0; col--){
            int row = (grid.length-1) - col;
            if(grid[row][col] != symbol){return false;}
        }
        return true;
    }

}
