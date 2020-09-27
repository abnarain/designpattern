import java.util.function.Supplier;

interface Shape3
{
    String info();

}

class Circle3 implements Shape3
{
    private float radius;

    public Circle3() {}
    public Circle3(float radius) {
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


class Square3 implements Shape3
{

    private float side;
    public Square3()
    {

    }

    public Square3(float side)
    {
        this.side = side;
    }


    @Override
    public String info() {
        return "A square has a side of " + side;
    }
}
//these static decorators
class ColoredShape3<T extends Shape3> implements Shape3{

    private Shape3 shape;
    private String color;

    //problem is the color

    public ColoredShape3(Supplier<? extends T> ctor, String color)
    {
        //cant do
        //shape = new T(); in java like c# as the type T gets erased
        shape = ctor.get();//constructing shape from the supplier(which gives a way to
        // create the shape )  that we are given
        this.color=color;

    }
    @Override
    public String info() {
        return shape.info() + " has the color "+ color;
    }
}


class TransparentShape3 <T extends Shape3>
    implements Shape3
{
    private Shape3 shape;
    private int transparency;

    public TransparentShape3(Supplier<? extends T> ctor, int transparency )
    {
        shape = ctor.get();

        this.transparency = transparency;
    }
    @Override
    public String info() {
        return shape.info()+ " has " + transparency + " % transparency ";
    }
}
public class StaticDecoratorPattern {

    public static void main(String[] args) {
        ColoredShape3<Square3> blueSquare = new ColoredShape3<>(
                () -> new Square3(20), "blue"
        );
        System.out.println(blueSquare.info());
        TransparentShape3<ColoredShape3<Circle3>> myCircle = new TransparentShape3<>(
                ()-> new ColoredShape3<>(
                        ()->new Circle3(5), "green"
                ),50
        );
        System.out.println(myCircle.info());

    }
}
