 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
//so not just instantiate the circle, square but also the root
//concrete class, not abstract and has a constructor
// so that you can instantiate it as a group of object

 class GraphicObject
{
    protected String name = "Group";

    public GraphicObject()
    {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    private  void print(StringBuilder stringBuilder, int depth)
    { // pretty print
        stringBuilder.append(String.join("",
                Collections.nCopies(depth, "*")))
                .append(depth>0 ? " ": "")
                .append((color ==null) || color.isEmpty() ? "": color + " ")
                .append(getName())
                .append(System.lineSeparator());
        for (GraphicObject child : children)
            child.print(stringBuilder, depth +1);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
}

class Circle6 extends GraphicObject
{
    public Circle6(String color)
    {
        name = "Circle";
        this.color=color;
    }
}

 class Square6 extends GraphicObject
 {
     public Square6(String color)
     {
         name = "Square";
         this.color=color;
     }
 }




 public class CompositePattern {

     public static void main(String[] args) {
         GraphicObject drawing = new GraphicObject();
         drawing.setName("Abhinav's Drawing");
         drawing.children.add(new Square6("Red"));
         drawing.children.add(new Circle6("Yellow"));

         GraphicObject group = new GraphicObject();
         group.children.add(new Circle6("Blue"));
         group.children.add(new Square6("Blue"));
         drawing.children.add(group);

         System.out.println(drawing);


     }

}
