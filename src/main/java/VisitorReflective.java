abstract class Expression2
{

}

//1+2+3

class DoubleExpression2 extends Expression2
{
    public double value;

    public DoubleExpression2(int i) {
        this.value = i;
    }

    public void DoubleExpression2(double v)
    {
        this.value=v;
    }

}

// being a reflective visitor, needs access to private elements
class ExpressionPrinter
{
    /*cannot have overloads like - not viable
    public static void print(DoubleExpression2 de, StringBuilder sb);
    public static void print(AdditionExpression2 ae, StringBuilder sb);
    so generalize
    */

    public static void print(Expression2 e , StringBuilder sb)
    {
        if (e.getClass() == DoubleExpression2.class) //using reflection to find out if there are instances of specific type
        {
            sb.append(((DoubleExpression2)e).value) ;
        } else if (e.getClass() == AdditionExpression2.class)
        {
            AdditionExpression2 ae = (AdditionExpression2) e;
            sb.append("(");
            print(ae.left, sb);
            sb.append("+");
            print(ae.right, sb);
            sb.append(")");
        }
    }

}
class AdditionExpression2 extends Expression2
{
    public Expression2 left, right;
    public AdditionExpression2(Expression2 left, Expression2 right)
    {
        this.left = left;
        this.right= right;
    }

}
public class VisitorReflective {
    public static void main(String[] args) {
        AdditionExpression2 e = new AdditionExpression2(
                new DoubleExpression2(1), new AdditionExpression2(
                new DoubleExpression2(2), new DoubleExpression2(3)
        )
        );
        // Problems:slow as reflection/instanceOf - ie checking type
        // missing verification that every single element in hierarchy has been implemented?
        // else if branch can be missed, so how do you force it? doing one time intrusion
        StringBuilder sb = new StringBuilder();
        ExpressionPrinter ep = new ExpressionPrinter();
        ExpressionPrinter.print(e, sb);
        System.out.println(sb);
    }
}
