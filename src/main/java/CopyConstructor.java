class Address2
{
    public String streetAddress, city, country;

    public Address2(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    //copy constuctor with deep copy
    //as we had another copy constructor
    public Address2(Address2 other )
    {   //calls the above constructor with other args
        this(other.streetAddress, other.city,other.country);
    }

    @Override
    public String toString() {
        return "Address2{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }



}

class Employee
{
    public String name;
    public Address2 address;

    public Employee(String name, Address2 address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other)
    {
        name = other.name;
        address = new Address2(other.address);
    }
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }


}
public class CopyConstructor {
    public static void main(String[] args) {
        Employee john = new Employee("john",
                new Address2("123 london", "London", "UK"));
        //Employee chris = john;
        Employee chris = new Employee(john);
        chris.name = "Chris";
        System.out.println(john);
        System.out.println(chris);

    }
}
