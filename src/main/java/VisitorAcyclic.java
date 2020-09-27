/*
* new Expression causes a new accept causes to implement a new Visit()
* in java, with acyclic interface - you end up making one, you end up implementing
* many/different interfaces
* Instead of implementing a visit() for every single element (expression in this case) in your
* heirarchy, you end up not concerning yourself with heirarchies anymore.
* so no more this tight requirement of implementing your tight heirarchy
* */

interface Visitor
{
    //marker interface without any members
    // only reason we have this is so that we can pass this as an argument
    //Idea: implement different visitor for each  type of expression and for base abstract class
    // we are doing interface segregation - segregating things into different interfaces
}
interface ExpressionVisitor4 extends Visitor
{
    void visit(Expression4 exp);
}

interface DoubleExpressionVisitor4 extends Visitor
{
    void visit(DoubleExpression4 obj);
}

interface AdditionExpressionVisitor4 extends Visitor
{
    void visit(AdditionExpression4 obj);
}

abstract class Expression4
{
    // the above visit makes expression class have an accept method
    public void accept(Visitor visitor)
    {
        //only interested in expression visitor as other visitors don't know how to visit an expression
        if (visitor instanceof ExpressionVisitor4)
        {
            ((ExpressionVisitor4) visitor).visit(this);
        }
    }

}

class AdditionExpression4 extends Expression4
{
    public Expression4 left, right;
    public AdditionExpression4(Expression4 left, Expression4 right)
    {
        this.left = left;
        this.right= right;
    }
    @Override
    public void accept(Visitor visitor)
    {
        //only interested in expression visitor as other visitors don't know how to visit an expression
        if (visitor instanceof AdditionExpressionVisitor4)
        {
            ((AdditionExpressionVisitor4) visitor).visit(this);
        }
    }
}

class DoubleExpression4 extends Expression4
{
    public double value;

    public DoubleExpression4(int i) {
        this.value = i;
    }

    public void DoubleExpression4(double v)
    {
        this.value=v;
    }

    @Override
    public void accept(Visitor visitor)
    {
        //only interested in expression visitor as other visitors don't know how to visit an expression
        if (visitor instanceof DoubleExpressionVisitor4)
        {
            ((DoubleExpressionVisitor4) visitor).visit(this);
        }
    }
}

//implement specific interfaces
class ExpressionPrinter4 implements DoubleExpressionVisitor4 , AdditionExpressionVisitor4
{
    StringBuilder sb = new StringBuilder();
    //if you comment out DoubleExpressionVisitor4, @override you will not need the double expression class
    //and hence no cyclic dependency. you can just have this visit() and code will still run
    @Override
    public void visit(DoubleExpression4 e) {
        sb.append(e.value);
    }

    @Override
    public void visit(AdditionExpression4 e) {
        sb.append("(");
        //do recursive calls here too
        e.left.accept(this);
        sb.append("+");
        e.right.accept( this);
        sb.append(")");

    }

}

public class VisitorAcyclic {
    public static void main(String[] args) {
        AdditionExpression3 e = new AdditionExpression3(
                new DoubleExpression3(1), new AdditionExpression3(
                new DoubleExpression3(2), new DoubleExpression3(3)
        )
        );
        ExpressionPrinter3 ep = new ExpressionPrinter3();
        ep.visit(e);
        System.out.println(ep);
    }
}
