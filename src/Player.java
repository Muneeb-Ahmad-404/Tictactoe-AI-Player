
public abstract class Player {
    protected char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol(Board board){
        return symbol;
    }

    public abstract int[] getMove(Board board);

    public char getSymbol() {
        return symbol;
    }
}
