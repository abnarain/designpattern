import java.io.*;

//class BasicSingleton
class BasicSingleton implements Serializable
{
    private BasicSingleton()
    {

    }
    private static final BasicSingleton INSTANCE = new BasicSingleton();
    public static BasicSingleton getInstance()
    {
        return INSTANCE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value =0;

    protected Object readResolve()
    { // Giving jvm context that whenever Serialization happens - it has to happen in the context of making
      //  the instance, as opposed to making the object
        return INSTANCE;
    }
}
public class SingletonFirst {

    static void saveToFile(BasicSingleton singleton, String filename) throws Exception
    {
        System.out.println("in savefile");
        try(FileOutputStream foo = new FileOutputStream(filename);
        ObjectOutputStream outp = new ObjectOutputStream(foo))
        {
            System.out.println("ging to write file object");
            outp.writeObject(singleton);

        }
    }

    static BasicSingleton readFromFile(String filename)
            throws Exception
    {

        try(FileInputStream fIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fIn))
            {
                return (BasicSingleton)in.readObject();
            }
    }

    public static void main(String[] args) throws Exception {
        //BasicSingleton x = new BasicSingleton();
        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(444);
        //System.out.println(singleton.getValue());

        String filename ="singleton.bin";
        saveToFile(singleton,filename);

        singleton.setValue(222);

        BasicSingleton s2 = readFromFile(filename);
        System.out.println(singleton == s2);
        System.out.println(singleton.getValue());
        System.out.println(s2.getValue());

    }
}
