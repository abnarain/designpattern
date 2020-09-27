import java.util.HashMap;

enum Subsystem
{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer2
{
    private static int instanceCount =0;
    private Printer2 (){
        instanceCount++;
        System.out.println("A total of "+ instanceCount + " instances created so far");
    }

    public static Printer2 get(Subsystem ss) //singleton for that subsystem
    {
        if(instances.containsKey(ss))
        {
            return instances.get(ss);
        }
        Printer2 instance = new Printer2();
        instances.put(ss, instance);
        return instance;
    }

    private static HashMap<Subsystem, Printer2> instances =new HashMap<>();


}
public class MultitonPattern {
    public static void main(String[] args) {
        Printer2 main = Printer2.get(Subsystem.PRIMARY);
        Printer2 aux = Printer2.get(Subsystem.AUXILIARY);
        Printer2 aux2 = Printer2.get(Subsystem.AUXILIARY); //returns from hash, no constructor needed

    }
}
