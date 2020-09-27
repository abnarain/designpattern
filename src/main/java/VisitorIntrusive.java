abstract class Expression
{
    public abstract void print(StringBuilder sb);
}
//intrusive visitor to jump into classes and write
// single responsiblitly principle - create separate print class with only printing responsibility
// open-close principle - modifying existing class which is already tested
//1+2+3

class DoubleExpression extends Expression
{
    private double value;

    public DoubleExpression(int i) {
        this.value = i;
    }

    public void DoubleExpression(double v)
    {
        this.value=v;
    }


    @Override
    public void print(StringBuilder sb) {
        sb.append(value);
    }
}
class AdditionExpression extends Expression
{
    private Expression left, right;
    public AdditionExpression(Expression left, Expression right)
    {
        this.left = left;
        this.right= right;
    }

    @Override
    public void print(StringBuilder sb) {
        sb.append("(");
        left.print(sb);
        sb.append(")");
        right.print(sb);
    }
}
public class VisitorIntrusive {
    public static void main(String[] args) {

        AdditionExpression e = new AdditionExpression(
                new DoubleExpression(1), new AdditionExpression(
                        new DoubleExpression(2), new DoubleExpression(3)
        )
        );
        StringBuilder sb = new StringBuilder();
        e.print(sb);
        System.out.println(sb);
    }
}
