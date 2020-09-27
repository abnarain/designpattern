
abstract class Game
{
    //chess or checker - all have the same elements
    protected final int numberOfPlayer;
    protected int currentPlayer;

    public Game(int n)
    {
        this.numberOfPlayer = n;
    }
    public void run() //skeleton algo
    {
        start();
        while (!haveWinner())
            takeTurn();;
        System.out.println("Player "+ getWinnningPlayer() + " wins");
    }

    protected abstract int getWinnningPlayer();
    protected abstract void takeTurn();
    protected abstract boolean haveWinner();
    protected abstract void start();

}

class Chess extends Game
{
    private int maxTurns = 10;
    private int turn =1;

    public Chess()
    {
        super(2);
    }

    @Override
    protected int getWinnningPlayer() {
        return 0;
    }

    @Override
    protected void takeTurn() {
        System.out.println(" Turn "+ (turn++)+ " taken by Player" + currentPlayer);
        currentPlayer = (currentPlayer +1 )%numberOfPlayer;
    }

    @Override
    protected boolean haveWinner() {
        return turn == maxTurns;
    }

    @Override
    protected void start() {
        System.out.println("Starting game of chess");
    }
}
public class TemplatePattern {
    public static void main(String[] args) {
        new Chess().run(); // run is skeleton algo - whatsteps to take . instead of
        // strategy.haveWinner(), strategy.start(), ..etc just do abstract
    }
}
