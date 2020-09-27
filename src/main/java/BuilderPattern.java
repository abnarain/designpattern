//import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
class HtmlElement
{
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {

    }

    public HtmlElement (String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String toStringImpl(int indent)
    {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent*indentSize, " "));
        sb.append(String.format("%s<%s>%s",i,name, newLine));
        if (text !=null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " "))).append(text)
                    .append(newLine);
        }

        for(HtmlElement e: elements)
            sb.append(e.toStringImpl(indent+1));
        sb.append(String.format("%s<%s>%s", i , name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

class HtmlBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.root = new HtmlElement();
        root.name = rootName;
    }

    public HtmlBuilder addChild(String cName, String cText)
    //public  void addChild(String cName, String cText)
    {
        HtmlElement e = new HtmlElement(cName, cText);
        root.elements.add(e);// WHERE IS THIS ADD function working out?
        //nothing returned before, but now we return this object
        return this;
    }
    public void clear() //utility function
    {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

public class BuilderPattern {

    public static void mainOld(String[] args) {
        String hello = "hello";
        System.out.println("<p>" + hello + "</p>");
        String[] words = {"hello", "world"};
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (String word : words) {
            sb.append(String.format(" <li> %s </li>", word));
        }
        sb.append("</ul>\n");
        System.out.println(sb); //sb.toString() actually gives the final build up result
    }

    public static void main(String[] args) {
        HtmlBuilder builder = new HtmlBuilder("ul");
        /*
        builder.addChild("li", "hello");
        builder.addChild("li", "world");
        */

        builder.addChild("li", "hello").addChild("li","bar");

        System.out.println(builder);
    }
}