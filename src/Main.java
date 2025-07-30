import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[][] b = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = '-';
            }
        }

        Board board = new Board(b);

        String result = "pending";
        int turn = 0;
        while(result == "pending"){
            char playerSymbol = '-';
            if(turn % 2 == 0){
                System.out.println("Player 1");
                playerSymbol = 'O';
            }
            else{
                playerSymbol = 'X';
                System.out.println("Player 2");
            }

            System.out.print("Enter the row: ");
            int row = scan.nextInt();
            System.out.print("Enter the column: ");
            int col = scan.nextInt();
            result = board.makeMove(row - 1, col - 1, playerSymbol);
            System.out.println(turn);
            if (result == "Invalid move"){
                System.out.println(result);
                result = "pending";
                continue;
            }
            turn ++;
        }
        System.out.println(result);
    }
}