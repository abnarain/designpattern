class Creature3
{
    public String name ;
    public int attack, defense;

    public Creature3(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature3{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }


}

class CreatureModifier
{
    protected Creature3 creature;
    protected CreatureModifier next;

    public CreatureModifier(Creature3 cr)
    {
        this.creature = cr;
    }

    public void add(CreatureModifier cm)
    {
        if (next != null)
        {
            next.add(cm);
        }
        else next = cm;
    }

    public void handle()
    {
        if (next!=null) next.handle();
    }
}

class DoubleAttackModifier extends CreatureModifier
{
    public DoubleAttackModifier(Creature3 cr) {
        super(cr);
    }

    @Override
    public void handle() {
        System.out.println("Doubling "+ creature.name+ " 's attack" );
        creature.attack *=2;
        super.handle();//why? for chain handling
    }

}

class IncreaseDefenseModifier extends CreatureModifier
{
    public IncreaseDefenseModifier(Creature3 cr) {
        super(cr);
    }

    @Override
    public void handle() {
        System.out.println("Increasing "+ creature.name + " 's defense ");
        creature.defense +=3;
        super.handle();
    }

}

class  NoBonusModifier extends CreatureModifier
{
    public NoBonusModifier(Creature3 cr) {
        super(cr);
    }

    @Override
    public void handle() {
        //nothing done
        System.out.println("No bonuses for you");
        //not calling super.handle so not going to call next super.handl()
    }
}
public class ChainOfResponsibility {
    public static void main(String[] args) {
        Creature3 goblin = new Creature3("Goblin", 2, 2);
        System.out.println(goblin);

        CreatureModifier root = new CreatureModifier(goblin);
        System.out.println("Let's double goblin's attack");

        root.add(new NoBonusModifier(goblin));
        root.add(new DoubleAttackModifier(goblin));

        System.out.println("Let's increase goblin's defense");
        root.add(new IncreaseDefenseModifier(goblin));
        root.handle(); //traverses each modifier - entire chain of respo

        System.out.println(goblin);

    }
}
