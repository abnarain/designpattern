import java.util.ArrayList;
import java.util.List;

class PropertyChangedEventArgs<T>
{
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangedEventArgs(T source,
                                    String propertyName,
                                    Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

interface Observer<T>
{
    void handle(PropertyChangedEventArgs<T> args);
}

class Observable<T>
{
    private List<Observer<T>> observers = new ArrayList<>();
    //list of observers

    public void subscribe(Observer<T> observer)
    {
        observers.add(observer);
    }
    //  whoever inherits from Observale - fire some method on change
    protected void propertyChanged(T source,
                                   String propertyChange,
                                   Object newValue)
    {
        for (Observer<T> o : observers)
        {
            o.handle(new PropertyChangedEventArgs<T>(
                    source, propertyChange, newValue
            ));
        }
    }
}


//class Person3 now becomes
class Person3 extends Observable<Person3>
{
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChanged(this, "age", age); //implementing observable pof person
    }

    private int age;

}

//implement observer of person interface
public class ObserverPatternFile implements Observer<Person3>
{
    public static void main(String[] args) {
        new ObserverPatternFile(); // as not want to do things in static context so
        //do this
    }

    public ObserverPatternFile() {
        Person3 p3 = new Person3();
        p3.subscribe(this); // this as I am the observer here
        for (int i=20;i<24;i++)
            p3.setAge(i);
    }

    @Override
    public void handle(PropertyChangedEventArgs<Person3> args) {
        System.out.println("Person's "
                + args.propertyName+" has changed to "+
                args.newValue);
    }
}
