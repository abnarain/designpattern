interface ExpressionVisitor //need to be implemented by every visitor
{
    void visit(DoubleExpression3 e);
    void visit(AdditionExpression3 e);
}
abstract class Expression3
{
    public abstract void accept(ExpressionVisitor visitor); //
}

//1+2+3 . want to evaluate this
class ExpressionCalculator implements ExpressionVisitor
{
    public double result ;
    @Override
    public void visit(DoubleExpression3 e) {
        result = e.value;
    }

    @Override
    public void visit(AdditionExpression3 e) {
        e.left.accept(this);
        double a = result;
        e.right.accept(this);
        double b = result;
        result  = a +b;
    }
}

class DoubleExpression3 extends Expression3
{
    public double value;

    public DoubleExpression3(int i) {
        this.value = i;
    }

    public void DoubleExpression3(double v)
    {
        this.value=v;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this); //same in every subclass
    }
}

class AdditionExpression3 extends Expression3
{
    public Expression3 left, right;
    public AdditionExpression3(Expression3 left, Expression3 right)
    {
        this.left = left;
        this.right= right;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

class ExpressionPrinter3 implements  ExpressionVisitor
{
    /*now since all these parts of interface, no way they can be skipped
    */
    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(DoubleExpression3 e) {
        sb.append(e.value);
    }

    @Override
    public void visit(AdditionExpression3 e) {
        sb.append("(");
        //do recursive calls here too
        e.left.accept(this);
        sb.append("+");
        e.right.accept( this);
        sb.append(")");

    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

public class VisitorDoubleDispatchClassic {
    public static void main(String[] args) {
        AdditionExpression3 e = new AdditionExpression3(
                new DoubleExpression3(1), new AdditionExpression3(
                new DoubleExpression3(2), new DoubleExpression3(3)
        )
        );
        ExpressionPrinter3 ep = new ExpressionPrinter3();
        ep.visit(e);
        System.out.println(ep);
        ExpressionCalculator ec = new ExpressionCalculator();
        ec.visit(e);
        System.out.println(ep + " = " + ec.result );
    }
}
