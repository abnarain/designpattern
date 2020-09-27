import java.util.List;
import java.util.function.Supplier;

enum OutputFormat2
{
    MARKDOWN, HTML
}
//list
//<ul> <li></li><ul>

//markdown:
// *foo
//*bar  ; processor knows how to use it

interface ListStrategy2
{
    default void start(StringBuilder sb) {} // as there is no start and end for markdown
    void addListItem(StringBuilder sb, String item);
    //    void end(StringBuilder sb);
    default void end(StringBuilder sb) {}

}


class MarkdownListStrategy2 implements  ListStrategy2
{
    //no start and end needed
    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item)
                .append(System.lineSeparator());


    }
}

class HtmlListStrategy2 implements ListStrategy2
{

    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("  <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("/<ul>").append(System.lineSeparator());

    }
}


class TextProcessor2 <LS extends ListStrategy2>
{
    private StringBuilder sb = new StringBuilder();
    private LS listStrategy; //=new LS() cannot be done due to type erasure property of Java

    public TextProcessor2(Supplier<? extends LS> ctor)
    {
        // wont allow anyone to change output format and hence
        listStrategy = ctor.get(); //constructor
    }

    public void appendList(List<String> items)
    {
        listStrategy.start(sb);
        for (String item : items)
        {
            listStrategy.addListItem(sb, item);
        }
        listStrategy.end(sb);
    }

    public void clear()
    {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}


public class StrategyStatic {
    public static void main(String[] args) {
        TextProcessor2<MarkdownListStrategy2> tp = new TextProcessor2<>(MarkdownListStrategy2::new);
        tp.appendList(List.of("alpha", "beta", "gamma"));
        System.out.println(tp);
        TextProcessor2<HtmlListStrategy2> tp2 = new TextProcessor2<>(HtmlListStrategy2::new);
        tp2.appendList(List.of("alpha", "beta", "gamma"));
        System.out.println(tp2);
    }
}
