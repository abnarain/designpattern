import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
interface Element
{
    int eval();
}

class Integer2 implements Element
{
    private int value;

    public Integer2(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element
{
    public enum Type
    {
        ADDITION,
        SUBTRACTION
    }

    public Type type;
    public Element left, right; // can be indefinite tree. element within element or integer

    @Override
    public int eval() {
        switch (type)
        {
            case ADDITION:
                return left.eval() + right.eval();

            case SUBTRACTION:
                return left.eval() - right.eval();

            default:
                return 0;
        }
    }
}




class Token
{
    public enum Type
    {
        INTEGER,
        PLUS,
        MINUS,
        LPAREN,
        RPAREN
    }

    public Type type;
    public String text;



    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + '`' ;

        //"type=" + type +
    }
}

public class Interpreter1Pattern {

    static List<Token> lex(String input)
    {
        ArrayList<Token> result = new ArrayList<>();
        for(int i=0;i<input.length();++i)
        {
            switch (input.charAt(i))
            {
                case '+':
                    result.add (new Token(Token.Type.PLUS,"+"));
                    break;
                case '-':
                    result.add (new Token(Token.Type.MINUS,"-"));
                    break;
                case '(':
                    result.add (new Token(Token.Type.LPAREN,"("));
                    break;
                case ')':
                    result.add (new Token(Token.Type.RPAREN,")"));
                    break;
                default:
                    StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for (int j=i+1; j<input.length();j++)
                    {
                        if (Character.isDigit(input.charAt(j)))
                        {
                            sb.append(input.charAt(j));
                            ++i;
                        } else
                        {
                            result.add(new Token(
                                    Token.Type.INTEGER, sb.toString()
                            ));
                            break;
                        }
                    }
                    break;
            }
        }
        return result;
    }

    static Element parse(List<Token> tokens)
    {
        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false;
        for(int i=0; i< tokens.size();i++)
        {
            Token token = tokens.get(i);
            switch (token.type)
            {

                case INTEGER:
                    Integer2 integer= new Integer2(java.lang.Integer.parseInt(token.text));
                    if (!haveLHS)
                    {
                        result.left = integer;
                        haveLHS = true;
                    } else {
                        result.right = integer;
                    }
                    break;
                case PLUS:
                    result.type= BinaryOperation.Type.ADDITION;
                    break;
                case MINUS:
                    result.type= BinaryOperation.Type.SUBTRACTION;
                    break;
                case LPAREN:
                    int j =0 ; // location of rparenthesis // no nesting here
                    for(; j<tokens.size();++j)
                        if (tokens.get(j).type == Token.Type.RPAREN)
                            break;
                    List<Token> subexpression = tokens.stream().skip(i+1).limit(j-i-1).collect(Collectors.toList());
                    Element element = parse(subexpression);
                    if (!haveLHS)
                    {
                        result.left= element;
                        haveLHS= true;
                    } else {
                        result.right= element;
                    }
                    i = j;
                    break;

                //case RPAREN:
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "(12+4)-(123+1)"; //change(split) this to tokens to evaluate
        List <Token> tokens = lex(input);

        System.out.println(tokens.stream()
        .map(t->t.toString())
        .collect(Collectors.joining("\t")));
        Element parsed = parse(tokens);
        System.out.println( input +" =" + parsed.eval());
    }
}
