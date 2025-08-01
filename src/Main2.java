import java.util.Scanner;
public class Main2 {
    final static int InvalidErrorCode = 302;
    final static int win = 100;
    final static int loss = -100;
    final static int draw = 50;
    final static int pending = 0;
    public static void main(String[] args) {
        Scanner scan = new  Scanner(System.in);
        int input = 0;
        while(true){
            System.out.println("--------Press-------");
            System.out.println("1 For Two-Player Mode");
            System.out.println("2 For One-Player Mode");
            System.out.println("3 to Exit the game");
            System.out.print("Choice: ");
            input = scan.nextInt();
            if(input == 1){
                HumanPlayer2 player1 = new HumanPlayer2('O');
                HumanPlayer2 player2 = new HumanPlayer2('X');
                startGame(player1,player2);
            }
            else if(input == 2){
                System.out.println("1-'O' or 2-'X'");
                int choice = scan.nextInt();
                HumanPlayer2 player1;
                AIPlayer2 player2;
                if(choice == 1){
                    player1 = new HumanPlayer2('O');
                    player2 = new AIPlayer2('X');
                }
                else{
                    player1 = new HumanPlayer2('X');
                    player2 = new AIPlayer2('O');
                }
                startGame(player1,player2);
            }
            else if(input == 3){
                System.out.println("-------Peace-out-----");
                break;
            }
            else{
                System.out.println("Invalid choice");
            }
        }
    }
    public static void startGame(Player2 player1, Player2 player2){
        Board2 board = new Board2(3);
        while(true){
            int[] move1;
            int result;
            do{
                move1 = player1.getMove(board.clone());
                result = board.makeMove(move1[0], move1[1], player1.symbol);
            }
            while (result == InvalidErrorCode);
            System.out.println("Player 1");
            board.printBoard();
            if(conclude(result, player1, player2)) break;

            int[] move2;
            do{
                System.out.println("player 2 do");
                move2 = player2.getMove(board.clone());
                result = board.makeMove(move2[0], move2[1], player2.symbol);
                System.out.println(result);
                System.out.println(move2[0]);
                System.out.println(move2[1]);
            }
            while (result == InvalidErrorCode);
            System.out.println("Player 2");
            board.printBoard();
            if(conclude(result, player2, player1)) break;
        }
    }
    private static boolean conclude(int result, Player2 player1,  Player2 player2){
        if(result == win){
            System.out.println("Player with symbol "+player1.symbol+" win!");
            return true;
        }
        else if(result == loss){
            System.out.println("Player with symbol "+player2.symbol+" won!");
            return true;
        }
        else if(result == draw){
            System.out.println("its a draw");
            return true;
        }
        return false;
    }
}
