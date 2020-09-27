import javax.print.Doc;

class Document {

}

interface Machine {
    void print (Document d);
    void fax(Document d);
    void scan(Document d);
}

class MultiFunctionPrinter implements Machine
{
    @Override
    public void print(Document d) {
    //
    }

    @Override
    public void fax(Document d) {
    //
    }

    @Override
    public void scan(Document d) {
        //
    }
}


interface  Printer
{
    void print(Document d);
}

interface Scanner
{
    void scan (Document d);
}

//YAGNI  - you aint going to need it

class JustPrinter implements Printer {
    @Override
    public void print(Document d) {

    }
}

//more meaningful logical class name
class Photocopier implements Printer, Scanner {
    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}


// you can also make an interface which extends multiple interfaces
//client can implement this interface contract to
// you can build/use a decorator

interface  MultiFunctionDevice extends Printer, Scanner {}

class MultiFunctionMachine implements MultiFunctionDevice
{

    private Printer printer;
    private Scanner scanner;


    public MultiFunctionMachine( Printer printer, Scanner scanner)
    {
        this.printer=printer;
        this.scanner = scanner;
    }
    //Do a decorator pattern
    @Override
    public void print(Document d) {
        //do a delegation here
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}


public class ISP {
}
