import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("Press Relevant number to choose the option");
            System.out.println("1-Human vs Human");
            System.out.println("2-AI vs Human");
            System.out.println("3-Exit");
            System.out.print("Choice: ");
            choice = scan.nextInt();
            if (choice == 1) {
                System.out.println("Human vs Human");
                optionOne();
            }
            else if (choice == 2) {
                System.out.println("AI vs Human");
                optionTwo();
            }
            else if (choice == 3) {
                System.out.println("Game ended");
                break;
            }
        }
    }
    public static void optionOne() {
        Board board = getBoard();
        HumanPlayer player1 = new HumanPlayer('O');
        HumanPlayer player2 = new HumanPlayer('X');

        int conclusion = -1;
        while(conclusion == -1){
            do {
                conclusion = -1;
                int[] move = player1.getMove(board);
                conclusion = board.makeMove(move[0], move[1], player1.symbol);
            }
            while (conclusion == -2);
            if(hasEnded(conclusion)){
                break;
            }
            do {
                conclusion = -1;
                int[] move = player2.getMove(board);
                conclusion = board.makeMove(move[0], move[1], player2.symbol);
            }
            while (conclusion == -2);
            if(hasEnded(conclusion)){
                break;
            }
        }

    }

    public static void optionTwo() {
        Board board = getBoard();
        AIPlayer player1 = new  AIPlayer('O');
        HumanPlayer player2 = new HumanPlayer('X');

        int conclusion = -1;
        while(conclusion == -1){
            do {
                conclusion = -1;
                int[] move = player1.getMove(board);
                conclusion = board.makeMove(move[0], move[1], player1.symbol);
            }
            while (conclusion == -2);
            if(hasEnded(conclusion)){
                break;
            }
            do {
                conclusion = -1;
                int[] move = player2.getMove(board);
                conclusion = board.makeMove(move[0], move[1], player2.symbol);
            }
            while (conclusion == -2);
            if(hasEnded(conclusion)){
                break;
            }
        }
    }


    private static boolean hasEnded(int conclusion) {
        if (conclusion == 1){
            System.out.println("O won");
            return true;
        } else if (conclusion == 0) {
            System.out.println("Its a draw");
            return true;
        } else if (conclusion == -3) {
            System.out.println("X won");
        }
        return false;

    }

    private static Board getBoard(){
        char[][] b = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = '-';
            }
        }
        return new Board(b);
    }
}