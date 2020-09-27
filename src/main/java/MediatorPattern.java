import java.util.ArrayList;
import java.util.List;

class Person6
{
    public String name;
    public ChatRoom room; // reference to chatroom. person can be bootedout of chatroom
    private List<String> chatLog = new ArrayList<>();

    public Person6(String name)
    {
        this.name = name; //more important
    }

    public void receive(String sender, String message)
    {
        String s = sender +": '"+ message + "'";
        System.out.println("["+name+" 's chat session]" +s );
        chatLog.add(s);
    }

    public void say(String message)
    {
        room.broadcast(name, message);

    }

    public void privateMessage(String who, String message)
    {
        room.message(name, who, message);
    }
}
// this is the MEDIATOR
class ChatRoom
{
    private List<Person6> people = new ArrayList<>();

    public void join(Person6 p)
    {
        String joinMsg = p.name + " has joined the room";
        broadcast("room", joinMsg);

        p.room = this;
        people.add(p);
    }

    public void broadcast(String source, String message)
    {
        for(Person6 person: people)
        {
            if (!person.name.equals(source))
                person.receive(source, message);
        }
    }

    public void message(String src, String destination, String msg)
    {
        people.stream().filter(p -> p.name.equals(destination))
                .findFirst().ifPresent(p -> p.receive(src, msg));
    }

}
public class MediatorPattern    {
    public static void main(String[] args) {
        ChatRoom room = new ChatRoom();
        Person6 john = new Person6("John");
        Person6 jane = new Person6("Jane");
        room.join(john);
        room.join(jane);

        john.say("hi room");
        //no one to broadcast the message
        jane.say("oh hey john");


        Person6 simon = new Person6("Simon");
        room.join(simon);


        simon.say("hi everyone");
        jane.privateMessage("Simon", "glad you could join");

    }
}
