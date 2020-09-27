import com.google.common.collect.Iterables;

import java.nio.file.*;
import java.util.*;
import java.io.*;
import java.lang.Integer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

interface Database
{
    int getPopulation(String name);
}

//class SingletonDatabase //was before
class SingletonDatabase implements Database
{
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instancecount =0;
    public static int getCount ()
    {
        return instancecount;
    }

    private SingletonDatabase()
    {
        instancecount++;
        System.out.println("Initializing database");

        try {
            File f = new File(
                    SingletonDatabase.
                            class.
                            getProtectionDomain().
                            getCodeSource().getLocation().getPath()
            );//class.getprotection- finds out where exactly the whole thing is ruuning
            Path fullpath = Paths.get(f.getPath(), "capitals.txt");

            List<String> lines = Files.readAllLines(fullpath);

            Iterables.partition(lines, 2).forEach(
                    kv -> capitals.put(
                            kv.get(0).trim(),
                            Integer.parseInt(kv.get(1))
            )); //initalize the database

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase  INSTANCE = new SingletonDatabase();
    public static SingletonDatabase getInstance( )
    {
        return INSTANCE;
    }

    public int getPopulation(String name)
    {
        return capitals.get(name);
    }
}
//recordfinder
class SingletonRecordFinder
{
    public int getTotalPopulation(List<String> names)
    {
       int result =0;
       for(String name: names) {
           result +=SingletonDatabase.getInstance().getPopulation(name);
       }
        return result ; //looks good but PROBLEM with TESTING  i.e accessing live database

    }
}

class ConfigurableRecordFinder
{
    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names)
    {
        int result =0;
        /*
        * NOW allow recordFinder to construct from dummy database - predictable to be test
        * if Tokyo population change, test will fail
        * */
        for(String name: names) {
            result +=database.getPopulation(name);
        }
        return result ; //looks good but PROBLEM with TESTING  i.e accessing live database

    }
}

class DummyDatabase implements Database
{
    private Dictionary<String, Integer > data  = new Hashtable<>();
    public DummyDatabase()
    {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }
    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}
class TestsIssue {
    @Test // this is NOT unit test, probably a unit test
    public void singletonTotalPopulationTest() {
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Delhi", "Tokyo");

        int tp = rf.getTotalPopulation(names);
    }
    @Test
    public void dependentPopulationTest()
    {
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        assertEquals(4,rf.getTotalPopulation(
                List.of("alpha", "gamma"
                )));
    }

}
class SingletonIssues
{
    public static void main(String[] args) {

    }
}