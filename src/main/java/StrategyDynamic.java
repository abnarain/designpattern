import java.util.List;

enum OutputFormat
{
    MARKDOWN, HTML
}
//list
//<ul> <li></li><ul>

//markdown:
// *foo
//*bar  ; processor knows how to use it

interface ListStrategy
{
    default void start(StringBuilder sb) {} // as there is no start and end for markdown
    void addListItem(StringBuilder sb, String item);
//    void end(StringBuilder sb);
    default void end(StringBuilder sb) {}

}


class MarkdownListStrategy implements  ListStrategy
{
    //no start and end needed
    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item)
                .append(System.lineSeparator());


    }
}

class HtmlListStrategy implements ListStrategy
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


class TextProcessor
{
    private StringBuilder sb = new StringBuilder();
    private ListStrategy listStrategy;

    public TextProcessor(OutputFormat outputFormat)
    {
        setOutputFormat( outputFormat);
    }
    public void setOutputFormat( OutputFormat outputFormat)
    {
        switch (outputFormat)
        {
            case HTML:
                listStrategy = new HtmlListStrategy();
                break;
            case MARKDOWN:
                listStrategy = new MarkdownListStrategy();
                break;
        }
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


public class StrategyDynamic {
    public static void main(String[] args) {
        TextProcessor tp = new TextProcessor(OutputFormat.MARKDOWN);
        tp.appendList(List.of("Abhinav ", " narain", " computer scientist"));

        tp.clear();
        tp.setOutputFormat(OutputFormat.HTML);
        tp.appendList(List.of(" inheritance", "encapsulation", "polymorphism"));


    }
}
