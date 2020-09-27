import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.lang.Integer;
class SimpleCreature
{
    private int strength, agility, intelligence;

    public int max()
    {
        return  Math.max(strength, Math.max(agility, intelligence));
    }

    public int sum()
    {
        return strength+intelligence+agility;
    }

    public double average()
    {
        return sum()/3.0;
    }
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}


class Creature2 implements Iterable<Integer>
{
    private int [] stats = new int[3];
    private  final int  strength = 0;
    private  final int  agility = 1;
    private  final int  Intelligence =2;
    public int getStrength()
    {
        return stats[strength];
    }

    public void setStrength(int v)
    {
        stats[strength] =v;
    }
    public int getAgility()
    {
        return stats[agility];
    }

    public void setAgility(int v)
    {
        stats[agility] =v;
    }
    public int getIntelligence()
    {
        return stats[Intelligence];
    }

    public void setIntelligence(int v)
    {
        stats[Intelligence] =v;
    }

    public int sum()
    {
        return IntStream.of(stats).sum();
    }
    public int max()
    {
        return IntStream.of(stats).max().getAsInt();
    }

    public double average()
    {
        return IntStream.of(stats).average().getAsDouble();
    }
    @Override
    public void forEach(Consumer<? super Integer> action) {
        for (int x: stats)
            action.accept(x);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(stats).spliterator();

    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }
}
public class IteratorPattern {
    public static void main(String[] args) {
        Creature2 creature = new Creature2();
        creature.setAgility(12);
        creature.setStrength(13);
        creature.setIntelligence(14);
        System.out.println(
                "max stat "+ creature.max() +
                " , total stat" + creature.sum() +
                        " , aveg stat " + creature.average()
        );
    }
}
