import java.util.Scanner;
public class HumanPlayer2 extends Player2 {
    public HumanPlayer2(char symbol){
        super(symbol);
    }

    @Override
    public int[] getMove(Board2 board) {
        Scanner input = new Scanner(System.in);
        System.out.print("Row: ");
        int row = input.nextInt();
        System.out.print("Column: ");
        int col = input.nextInt();
        return new int[]{row-1,col-1};
    }
}
