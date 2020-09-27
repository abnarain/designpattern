import java.io.*;
import java.util.function.DoubleToIntFunction;
/*
* If your requirements work - this singleton will not recreate the state (save 111 and rebuild)
* instead just recreate from constructor values
*  */
//enum after java 1.5
enum EnumUponBasedSingleton
{
    INSTANCE;
    EnumUponBasedSingleton() //private already, no way to make public constrcutor
    {
        value =42;
    }
    private  int value;

    public int getValue()
    {
        return value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
}

public class EnumBasedSingleton {

    static void saveToFile(EnumUponBasedSingleton s, String filename) throws IOException
    {
        try(FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(f)
        ){
            out.writeObject(s);
        }

    }

    static  EnumUponBasedSingleton readFromFile(String f) throws Exception
    {
        try(FileInputStream fIn = new FileInputStream(f);
        ObjectInputStream in = new ObjectInputStream(fIn);
        )
        {
            return (EnumUponBasedSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception
    {
        String filename= "myfile.bin";
        //EnumUponBasedSingleton s = EnumUponBasedSingleton.INSTANCE;
        //s.setValue(111);
        //saveToFile(s,filename);
        EnumUponBasedSingleton s2 = readFromFile(filename);
        System.out.println(s2.getValue());

    }
}
