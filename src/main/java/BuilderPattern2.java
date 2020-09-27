
/*
* one builder with name and another builder from it that fills employment info
*
*
*
*/
//
// class Person {
//    public String name;
//    public String position;
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", position='" + position + '\'' +
//                '}';
//    }
//}
//
//
///*
//class PersonBuilder
//{
//    protected Person person = new Person();
//
//    public PersonBuilder withName (String name)
//    {
//        person.name = name;
//        return this; //this fluent interface will be problematic when inheringing
//    }
//    public Person build ()
//    {
//        return person;
//    }
//}
//
//class EmployeeBuilder
//extends PersonBuilder {
//    public EmployeeBuilder worksAt(String position)
//    {
//        person.position = position;
//        return this; // this is a problem
//    }
//}
//*/
//
//
//class PersonBuilder<SELF extends PersonBuilder<SELF>>
//{
//    protected Person person = new Person();
//
//    public SELF withName (String name)
//    {
//        person.name = name;
//        //return (SELF) this; //this fluent interface will be problematic when inheringing
//        // some IDEs say not check cast
//        return self();
//    }
//    public Person build ()
//    {
//        return person;
//    }
//    protected SELF self()
//    {
//        return (SELF) this;
//    }
//}
//
//class EmployeeBuilder
//extends PersonBuilder<EmployeeBuilder> {
//    public EmployeeBuilder worksAt(String position)
//    {
//        person.position = position;
//        //return this; // this is a problem
//        return self(); // here propogating the idea of fluent interface
//    }
//
//    @Override
//    protected EmployeeBuilder self() {
//        return this;
//    }
//}
//public class BuilderPattern2 {
//    public static void main(String[] args) {
//        PersonBuilder pb =  new PersonBuilder();
//        // without fluent  building
//        // Person dmitri = pb.withName("Dmitri").build();
//
//        //ISSUE: worksAt is not available: Person dmitri = pb.withName("Dmitri").worksAt().build()
//        //ISSUE: worksAt is not available:
//        // even if you build EmployeeBuilder pb = new EmployeeBuilder as withName returns PersonBuilder, not EmployeeBuilder
//        // Person dmitri = pb.withName("Dmitri").worksAt().build()
//        //Precisely where Java GENERICS come into play
//        // Design PersonBuilder in such a way that it is friendlier to providing fluent api using Generic
//        Person dmitri = pb.withName("Dmitri").withName("Developer").build();
//        System.out.println(dmitri);
//    }
//}
