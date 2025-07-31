import java.util.Scanner;

public class HumanPlayer extends Player {
    protected char symbol;

    public HumanPlayer(char symbol) {
        super(symbol);
        this.symbol = symbol;
    }

    @Override
    public int[] getMove(Board board) {
        System.out.println(symbol);
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the row position: ");
        int row = scan.nextInt();
        System.out.print("Enter the column position: ");
        int col = scan.nextInt();
        return new int[] {row-1, col-1};
    }
}
