import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/*
* Does only one job
* */
public class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count =0;
    public void addEntry(String text) {
        entries.add("" + (++count) + ":" + text);
    }

    public void removeEntry(int idx) {
        entries.remove(idx);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(),entries);
    }

    /*
    voilation of Single Resp Principle
    public void save( String filename) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
        }
    }

    public void load (String filename) {}
    public void (URL uri) {}
    */

    public static void main(String[] args) throws FileNotFoundException {
        Journal j = new Journal();
        System.out.println("Abhianv running");
        j.addEntry("I cried today");
        j.addEntry("I ate a bug");
        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "journal.txt";
        p.saveToFile(j, filename, true);
    }
}

class Persistence
{
    public void saveToFile(Journal journal, String filename, boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(filename).exists()) {
            try(PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
        }
    }
    /*
    public Journal load (String filename) {}
    public Journal (URL uri) {}
    */
}


