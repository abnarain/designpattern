import java.io.File;
import java.io.IOException;

class StaticBlockSingleton
{
    public StaticBlockSingleton() throws IOException
    {
        System.out.println("Singleton is initializing...");
        File.createTempFile(".",".");
    }
    private static StaticBlockSingleton instance;
    static //kind of static constructor
    {
        try
        {
            instance = new StaticBlockSingleton();
        }
        catch (Exception e)
        {
            System.err.println("failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance()
    {
        return instance;
    }
}
public class StaticBlockSingletonCase {
}
