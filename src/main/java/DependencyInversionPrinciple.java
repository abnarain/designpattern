//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import javafx.scene.Parent;
//import org.javatuples.*;
//
//enum Relationship
//{
//    PARENT,
//    CHILD,
//    SIBLING
//}
//
//class Person
//{
//    public String name ;
//    // dob, nationality etc
//
//    public Person (String name ){
//        this.name = name ;
//    }
//}
//
////this is the abstraction to be implemented on the lower level module
//interface  RelationshipBrowser
//{
//    //find all children of person
//    List< Person> findAllChildrendOf(String name);
//}
//
//
////class Relationships // this is a low level module as deals primarily with storage -single responsibility
//class Relationships implements RelationshipBrowser //now implementing the surface
//{
//    private List<Triplet<Person, Relationship, Person>> relations  = new ArrayList<>(); // thhis is private and needs a getter
//
//    public List<Triplet<Person, Relationship, Person>> getRelations() {
//        return relations;
//    }
//
//    public void addParentAndChild(Parent parent, Person child)
//    {
//        //relations.add(new Triplet<>(parent, Relationship.PARENT, child));
//        relations.add(new Triplet<>(child, Relationship.CHILD, child));
//    }
//
//    // the new part of interface implementation
//
//    @Override
//    public List<Person> findAllChildrendOf(String name) {
//        return relations.stream().filter(
//                x-> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT).map(
//                        Triplet::getValue2
//        ).collect(Collectors.toList());
//    }
//}
//*/
//
//class Research // high level as gives high levels constructs to query it
//{
//    /*
//    public Research(Relationships relationships)
//    {
//        //here hgih level module should not depend on low level module
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream().filter(x-> x,getValues0().name.equals("John") && x.getValue1() ==Relationship.PARENT)
//                .forEach(ch-> System.out.println(" John has a child called "+ ch.getValue2().name));
//    }
//    */
//    // now depend on abstraction, not on the lower level module
//    public Research (RelationshipBrowser browser)
//    {
//        List<Person> children = browser.findAllChildrendOf("John");
//        for (Person child : children)
//            System.out.println("John has child called"+ child);
//    }
//}
//public class DependencyInversionPrinciple
//{
//    /*
//    public static void main2(String[] args) {
//        Person parent = new Person("John");
//        Person child1 = new Person("Chris");
//        Person child2 = new Person("Matt");
//
//        Relationships relationships = new Relationships();
//        relationships.addParentAndChild(parent, child1);
//        relationships.addParentAndChild(parent, child2);
//
//    }
//    */
//
//}
