import java.awt.*;
import java.util.Collection;

interface Shape
{
    String info();

}

class Circle implements Shape
{
    private float radius;

    public Circle() {}
    public Circle(float radius) {
        this.radius = radius;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }

    void resize(float factor)
    {
        radius *=factor;
    }
}


class Square2 implements Shape
{

    private float side;
    public Square2()
    {

    }

    public Square2(float side)
    {
        this.side = side;
    }


    @Override
    public String info() {
        return "A square has a side of " + side;
    }
}


class ColoredShape implements Shape
{
    private Shape shape;
    private String color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color "+ color;
    }
}


class TransparentShape implements Shape
{

    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info()+ " has " + transparency + " % transparency ";
    }
}


public class DynamicDecoratorCompositionPattern {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColoredShape blueSquare = new ColoredShape(new Square2(20), "blue");
        System.out.println(blueSquare.info());
        //green half transparent circle of radius 5

        TransparentShape myCircle = new TransparentShape(
                new ColoredShape( new Circle(5),"green"),
                50);
        System.out.println(myCircle.info());



    }

}
