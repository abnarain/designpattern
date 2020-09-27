class Person {
    //address
    public String streetAddress, postcode, city;

    //employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String  toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

class PersonBuilder
{
    protected Person person = new Person();
    // protected so that you dont have access to the object but you can be given the object
    // once you indicate you are done building

    public PersonJobBuilder works()
    {
        return new PersonJobBuilder(person);
    }

    public PersonAddressBuilder lives()
    {
        return new PersonAddressBuilder(person); // pass the reference of the object you are building
    }
    public Person build()
    {
        return person;
    }
}

class PersonAddressBuilder extends PersonBuilder //important to extends - critical for faceted builder
{
    public PersonAddressBuilder (Person person)
    {
        this.person = person; // just reassign person
    }


    public PersonAddressBuilder at(String streetAddr)
    {
        person.streetAddress = streetAddr;
        return this;
    }
    public PersonAddressBuilder withPostCode(String postCode)
    {
        person.postcode = postCode;
        return this;
    }
    public PersonAddressBuilder in(String city)
    {
        person.city = city;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilder {
    public  PersonJobBuilder(Person person)
    {
        this.person = person;

    }

    public PersonJobBuilder at(String companyName)
    {
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position)
    {
        person.position= position;
        return this;
    }

    public PersonJobBuilder earning(int income)
    {
        person.annualIncome=income;
        return this;
    }




}
public class FacetedBuilder {

    public static void main(String[] args) {
        PersonBuilder pb= new PersonBuilder();
        Person person = pb.lives().at("123 london road").in("london").withPostCode("SW12").
                works().at("citadel securities").asA("Software Engineer").earning(300).build();
        System.out.println(person);
    }

}
