import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.lang.Integer;
interface Human
{
    void walk();
    void talk();

}

class Person5 implements Human
{
    @Override
    public void walk() {
        System.out.println("I am walking ");
    }

    @Override
    public void talk() {
        System.out.println("I am talking");
    }
}

class LoggingHandler implements InvocationHandler
{
    private final Object target; //this is for what the object is made

    private Map<String , Integer> calls = new HashMap<>();

    public LoggingHandler(Object target)
    {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        calls.merge(name, 1, Integer::sum);
        //suppose someone calls to string on a decorated object-
        // exactly where want to output the no. of calls that have been made
        //to different methods
        if (name.contains("toString"))
        {
            return calls.toString();
        }
        return method.invoke(target, args);
    }
}
public class DynamicProxyPattern {
    //utility method to have for constructing a dynamic
    //proxy with logging on any kind object it doesnt have to be
    // a person and can be virtually anything
    /*
    target : object for which the logging is required
    itf : specify as a class of T- interface we want to receive on the output
    as we want to get particular interface and a dynamic proxy for it.
    You cannot just take the underlying class and get that as the end result
    as that wont work BUT
    can get interface so you get the dynamic proxy which conforms to itf
    * */
    //suppress unchecked cast warning as IDE complains about T casting
    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itf)
    {
        return (T)Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[]{itf},
                new LoggingHandler(target)
        );
    }
    public static void main(String[] args) {
    Person5 person = new Person5();
    Human logged = withLogging(person, Human.class);
    logged.talk();
    logged.walk();
        logged.walk();
    System.out.println(logged );
    }
}
