/*
* event broker which uses reactive extensions
* a football player scoring goals and coach trying to congratulate him as they score
* reactive event broker
* Observable is react extenstion class that allows to create an observable component that we
* can subsequently subscribe
* */
// more sophisticated - Reactive events broker
import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.ArrayList;
import java.util.List;

class EventBroker extends Observable<Integer>
{
    //list of every subscriber to the events generated by the event broker
    private List<Observer<? super Integer>> observers = new ArrayList<>();

    //when someone tries to subscribe
    @Override
    protected void subscribeActual(Observer<? super Integer> observer) {
        observers.add(observer);
    }
    //how can we subscribe event and publish event inside boker
    //info to propogate about event - integer - how many goals player scored

    public void publish(int n)
    {
        for(Observer<? super Integer> o  : observers)
        {
            //karo openo.onNext(n);// onNext(n);//how you put something on reactive extension pipeline
        }
    }
}

class FootballPlayer
{
    private int goalsScored =0;
    private EventBroker broker;
    public String name;


    public FootballPlayer(EventBroker broker, String name) {
        this.broker = broker;
        this.name = name;
    }

    public void score()
    {
        broker.publish(++goalsScored);
    }
}


class FootballCoach
{
    public FootballCoach(EventBroker broker)
    {
        broker.subscribe(i -> {
            System.out.println("Hey you scored " + i + " goals!! ");
        });
    }
}
public class Mediator2Pattern {
    public static void main(String[] args) {
        EventBroker eventBroker = new EventBroker();
        FootballPlayer  player = new FootballPlayer(eventBroker, "Abhinav");
        FootballCoach  coach= new FootballCoach(eventBroker);

        player.score();
        player.score();
        player.score();

    }
}