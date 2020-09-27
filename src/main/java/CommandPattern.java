import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

class BankAccount3
{
    private int balance;
    private int overdraftLimit = -500;
    public void deposit(int amount)
    {
        balance += amount;
        System.out.println("Deposited " + amount
        + ", balance is now " + balance);

    }


    //public void withdraw(int amount) : API to support succeeded idea
    public boolean withdraw(int amount)
    {
        if (balance - amount >= overdraftLimit)
        {
            balance -=amount;

            System.out.println("Withdrew " + amount
                    + ", balance is now " + balance);
            return true;
        }
        return false;
     }

    @Override
    public String toString() {
        return "BankAccount3{" +
                "balance=" + balance +
                '}';
    }
}

interface Command
{
    void call(); // to apply command
    void undo();

}

class BankAccount3Command implements Command
{
    private BankAccount3 account;
    private boolean succeeded;

    public enum Action
    {
        DEPOSIT, WITHDRAW
    }
    private Action action;
    private int amount;

    public BankAccount3Command(BankAccount3 account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }



    @Override
    public void call() {

        switch (action)
        {
            case DEPOSIT:
                succeeded=true;
                account.deposit(amount);
                break;
            case WITHDRAW:
                succeeded = account.withdraw(amount);
                break;
        }
    }
    //undo and call are opposite
    @Override
    public void undo() {
        if (!succeeded) return;
        switch (action)
        {
            case DEPOSIT:
                account.withdraw(amount);
                break;
            case WITHDRAW:
                account.deposit(amount);
                break;
        }
    }


}
public class CommandPattern {

    public static void main(String[] args) {

        BankAccount3 ba = new BankAccount3();
        System.out.println(ba);
        List<BankAccount3Command> commands = List.of(
           new BankAccount3Command(ba, BankAccount3Command.Action.DEPOSIT,100),
                new BankAccount3Command(ba, BankAccount3Command.Action.WITHDRAW,1000)
                );
        for(BankAccount3Command c: commands)
        {
            c.call();
            System.out.println(ba);
        }

        for (Command c: Lists.reverse(commands))
        {
            c.undo();
            System.out.println(ba);
        }
    }
}
