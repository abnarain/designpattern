/*
Momento is a snapshot of internal state of bank account
 * */

class BankAccountTokenMomento
{


    private int balance; // you can make setter, getter private

    public int getBalance() {
        return balance;
    }

    public BankAccountTokenMomento(int balance) {
        this.balance = balance;
    }



}
class BankAccount
{
    private int balance;

    public BankAccount(int balance)
    {
        this.balance = balance;
    }

    //public void deposit(int amount)
    public BankAccountTokenMomento deposit(int amount) //this allows you to rollback the deposit operation
    {
        balance +=amount;
        return new BankAccountTokenMomento(balance);
    }

    //To use this API we need a method to restore the momento
    public void restore(BankAccountTokenMomento m)
    {
        balance = m.getBalance();
    }
    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}
public class MomentoPattern {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount(100);
        //you can have a setter instead of constructor if you want
        // a momento for initial set
        //
        BankAccountTokenMomento m1 = ba.deposit(50);
        BankAccountTokenMomento m2 = ba.deposit(25);
        System.out.println(ba);
        //restore to m1
        ba.restore(m1);
        System.out.println(ba);
        ba.restore(m2);
        System.out.println(ba);

    }
}
