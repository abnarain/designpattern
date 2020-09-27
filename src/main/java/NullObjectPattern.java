import java.lang.reflect.Proxy;

interface Log
{
    void info(String msg);
    void warn(String msg);
}
class ConsoleLog implements Log{
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println("WARNING" + msg);

    }
}
class BankAccount2
{
    private Log log;
    private int balance;
    public BankAccount2(Log log)
    {
        this.log = log;
    }

    public void deposit(int amount)
    {
        balance += amount;
        log.info("Deposit" + amount); //hard dependency on Logging
    }


}

final class NUllLog implements Log
{
    @Override
    public void info(String msg) {

    }

    @Override
    public void warn(String msg) {

    }
}
public class NullObjectPattern
{
    //return type T
    @SuppressWarnings("unchecked") //remove this and IDE shows orange color
    public static <T> T noOp(Class <T> itf)
    {
        return (T) Proxy.newProxyInstance(
            itf.getClassLoader(),
                new Class<?>[] {itf},
                (proxy, method, args )->
                {
                    if(method.getReturnType().equals(Void.TYPE))
                        return null;
                    else
                        return method.getReturnType().getConstructor().newInstance();
                }
                );
    }

    public static void main(String[] args) {

        //ConsoleLog log = new ConsoleLog();
        //NUllLog log = new NUllLog();

        Log log = noOp(Log.class);

        //what if you dont want any logging?, you cant just pass NULL
        // if someone coded this defensively -
        // if (log != null)
        //        log.info('w');
        BankAccount2 acc = new BankAccount2(log);
        acc.deposit(10);
    }
}
