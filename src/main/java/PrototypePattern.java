import java.util.Arrays;

class Address implements Cloneable
{
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    @Override //changed from protected to public
    public Object clone() throws CloneNotSupportedException {
        //return super.clone();
        return new Address(streetName, houseNumber);
    }
}

class Person2 implements Cloneable
{
    public String [] names;
    public Address address;

    public Person2(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //return super.clone();
        // this is incorrect as names and address are references and you will be refering to the same object
        //return  new Person2(names, address);

        //array is having a default clone
        return new Person2(names.clone(), (Address) address.clone());
    }
}
public class PrototypePattern {
    public static void main(String[] args) {
        Person2 abhinav = new Person2(new String[]{"Abhinav", "Narain"}, new Address("Seattle", 123));
        // now make someone neighbor - make copy and change the bits

        Person2 jane = abhinav;//.clone() will be cool!
        jane.names[0] = "jane";
        jane.address.houseNumber=124; // but since this jane is a reference, you end up modifying abhinav
        //as sharing the same data which we modify

        System.out.println(abhinav);
        System.out.println(jane);
    }
}
