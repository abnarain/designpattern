import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.lang.Integer;
class Event<TArgs>
{
    //private - so immutable and one piece of API - try with resources and close()
    private Integer count = 0;

    private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler) //momento is to be called subscription momento design pattern
    {
        Integer i  = count;
        handlers.put(count++, handler);

        return new Subscription(this,i) ; //cached value
        //this: is event itself and the index wrapped in Subscription
    }

    public void fire(TArgs args)
    {
        for(Consumer<TArgs> handler : handlers.values())
        {
            handler.accept(args); // get each of the consumer
        }
    }
    public class Subscription implements AutoCloseable
    {
        private Event<TArgs> event;
        private int id; //key to Map "handlers"

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() throws Exception {
            event.handlers.remove(id);
        }
    }
}

//notification object that we will fire from Event
class PropertyChangedEventArgs2
{
    public Object source;
    public String property;

    public PropertyChangedEventArgs2(Object source, String property) {
        this.source = source;
        this.property = property;
    }


}

class Person4
{
    public Event<PropertyChangedEventArgs2> propertyChanged = new Event<>();
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age==age) return;

        boolean oldCanVote = getCanVote();

        this.age = age;
        propertyChanged.fire(new PropertyChangedEventArgs2(this, "age"));

        if (oldCanVote != getCanVote())
        {
            propertyChanged.fire(new PropertyChangedEventArgs2(this, "age"));

        }
    }

    public boolean getCanVote()
    {
        return age >=18;
    }
}
public class BetterObserverPattern {


    public static void main(String[] args) throws Exception {
        Person4 person = new Person4();
        Event<PropertyChangedEventArgs2>.Subscription sub =
                person.propertyChanged.addHandler(x-> {
                System.out.println("Person's Dank "+x.property + "has changed ");
                        }
                );
        person.setAge(17);
        person.setAge(18);
        sub.close();
        person.setAge(19);

    }
}
