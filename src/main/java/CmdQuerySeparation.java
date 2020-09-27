import com.sun.org.apache.xpath.internal.Arg;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.lang.Integer;
/*
Not about a single pattern
CQS

perform a query and query will provide us with
attack, defense value for a creature
*/
class Event2<Args>
{
    private int index =0;
    private Map<Integer, Consumer<Args>>
    handlers = new HashMap<>();
    public int subscribe(Consumer<Args> handler)
    {
        int i =index;
        handlers.put(index++, handler);
        return i;
    }
    public void unsubscribe(int key)
    {
        handlers.remove(key);
    }

    public void fire(Args args)
    {
        for(Consumer<Args> handler: handlers.values())
        {
            handler.accept(args);
        }
    }
    {

    }

}

class Query
{
    public String creatureName;
    enum Argument
    {
        ATTACK, DEFENSE
    }
    public  Argument argument;
    public int result;

    public Query(String creatureName,
                 Argument argument, int result)
    {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

//mediator
class Game2
{
    public Event2<Query> queries = new Event2<>();
}

class Creature5
{
    private Game2 game;
    public String name;
    public int baseAttack, baseDefense;

    public Creature5(Game2 game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack()
    {
        Query q = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    int getDefense()
    {
        Query q = new Query(name, Query.Argument.DEFENSE, baseDefense);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "game=" + game +
                ", name='" + name + '\'' +
                ", Attack=" + getAttack() +
                ", Defense=" + getDefense() +
                '}';
    }
}

class CreatureModifier2
{
    //modifier reference mediator - game

    protected Game2 game;
    protected Creature5 creature;

    public CreatureModifier2(Game2 game, Creature5 creature) {
        this.game = game;
        this.creature = creature;
    }

}

class IncreasedDefenseModifier2 extends CreatureModifier2
{
    public IncreasedDefenseModifier2(Game2 g, Creature5 creature) {

        super(g, creature);

        game.queries.subscribe(q -> {
            if (q.creatureName.equals(creature.name) && q.argument == Query.Argument.DEFENSE)
            {
                q.result +=3;
            }
        });
    }
}
class DoubleAttackModifier2 extends CreatureModifier2
    implements AutoCloseable
{
    private final int token;
    public DoubleAttackModifier2(Game2 game, Creature5 creature) {
        super(game, creature);
        //subscriber's lambda takes in a supplier
        token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(creature.name) && q.argument == Query.Argument.ATTACK)
            {
                q.result *=2;
            }
        });
    }

    @Override
    public void close() //throws Exception
    {
        game.queries.unsubscribe(token);
    }
}
public class CmdQuerySeparation {

    public static void main(String[] args) {
        //how to use eventbroker, first create it - new game
        Game2 game = new Game2();
        Creature5 goblin = new Creature5(game, "Strong Goblin", 2, 2);
        System.out.println(goblin);
        // pile on the modifiers in whichever order

        IncreasedDefenseModifier2 mod1 = new IncreasedDefenseModifier2(game, goblin);

        try(DoubleAttackModifier2 mod2 = new DoubleAttackModifier2(game, goblin)) {
            System.out.println(goblin);
        }
        System.out.println(goblin);
    }
}
