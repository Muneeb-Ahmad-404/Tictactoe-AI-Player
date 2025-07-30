package com.example.tictactoeaiplayer;

public class Board{
    private char[][] board;
    private int movesMade;

    public Board(char[][] board){
        this.board = board;
        movesMade = 0;
    }

    public String makeMove(int row, int col, char playerSymbol){
        if (isValidMove(row, col)){
            board[row][col] = playerSymbol;
            movesMade++;
            printBoard();
            return hasConcluded(row, col, playerSymbol);
        }
        else{
            printBoard();
            return "Invalid move";
        }

    }

    public String hasConcluded(int row, int col, char playerSymbol){
        if (isDraw()) return "Its a Draw";
        else if (rowMatch(row, playerSymbol) || colMatch(col, playerSymbol) || diagonalMatch(playerSymbol)) {
            return playerSymbol + " won the match";
        }
        else return "pending";
    }


    private boolean isValidMove(int row, int col){
        if (row >= board.length || col >= board.length) return false;
        else if (row < 0 || col < 0) return false;
        if (board[row][col] == '-') return true;
        else return false;
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

    private void printBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}