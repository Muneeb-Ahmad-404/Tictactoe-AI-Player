public abstract class Player2 {
    public char symbol;
    public Player2(char symbol){this.symbol = symbol;}
    public abstract int[] getMove(Board2 board);
}