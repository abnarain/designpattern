////shape -> circle, square
////rendering -> vector, raster scans
//// 4 classes 2X2 made
//// stick the render right inside the shape
////
////
//
//
//import com.google.inject.AbstractModule;
//import com.google.inject.Guice;
//import com.google.inject.Inject;
//import com.google.inject.Injector;
//
//interface Renderer
//{
//    void renderCircle(float radius);
//}
//
//class VectorRenderer implements Renderer
//{
//    @Override
//    public void renderCircle(float radius) {
//        System.out.println("DRaw Vector Circle of " + radius);
//
//    }
//}
//
//class RasterRenderer implements Renderer
//{
//    @Override
//    public void renderCircle(float radius) {
//        System.out.println("DRaw Raster Circle of " + radius);
//    }
//}
//
//
//abstract class Shape4
//{
//    protected Renderer renderer;
//    public  Shape4(Renderer renderer)
//    {
//        this.renderer=renderer;
//    }
//    public abstract void draw();
//    public abstract void resize(float factor);
//
//
//}
//
//class Circle4 extends Shape4
//{
//    public float radius;
//
//    @Inject
//    public Circle4(Renderer renderer) {
//        super(renderer);
//    }
//
//    public Circle4(Renderer renderer, float radius) {
//        super(renderer);
//        this.radius = radius;
//    }
//
//    @Override
//    public void draw() {
//        renderer.renderCircle(radius);
//    }
//
//    @Override
//    public void resize(float factor) {
//        radius *=factor;
//    }
//}
//
//class ShapeModule extends AbstractModule
//{
//    @Override
//    protected void configure() {
//        //create new vector renderer for every Renderer class
//        bind(Renderer.class).to(VectorRenderer.class);
//    }
//}
//
//public class BridgePattern {
//    public static void main(String[] args) {
//        Injector injector = Guice.createInjector(new ShapeModule());
//        Circle4 instance = injector.getInstance(Circle4.class);
//        //instance.radius =3;
//        instance.draw();
//        //instance.resize(2);
//        //instance.draw();
//    }
//    /*
//    public static void withoutGoogle()
//    {
//        RasterRenderer raster = new RasterRenderer();
//        VectorRenderer vector =  new VectorRenderer();
//
//        Circle4 circle= new Circle4(vector, 5) ;
//        circle.draw();
//
//        circle.resize(2);
//        circle.draw();
//
//    }
//    */
//
//}

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

interface Renderer
{
    void renderCircle(float radius);
}

class VectorRenderer implements Renderer
{
    @Override
    public void renderCircle(float radius)
    {
        System.out.println("Drawing a circle of radius " + radius);
    }
}

class RasterRenderer implements Renderer
{
    @Override
    public void renderCircle(float radius)
    {
        System.out.println("Drawing pixels for a circle of radius " + radius);
    }
}

abstract class Shape4
{
    protected Renderer renderer;

    public Shape4(Renderer renderer)
    {
        this.renderer = renderer;
    }

    public abstract void draw();
    public abstract void resize(float factor);
}

class Circle4 extends Shape4
{
    public float radius;

    @Inject
    public Circle4(Renderer renderer)
    {
        super(renderer);
    }

    public Circle4(Renderer renderer, float radius)
    {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw()
    {
        renderer.renderCircle(radius);
    }

    @Override
    public void resize(float factor)
    {
        radius *= factor;
    }
}

class ShapeModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(Renderer.class).to(VectorRenderer.class);
    }
}

class BridgePattern
{
    public static void main(String[] args)
    {
//    RasterRenderer rasterRenderer = new RasterRenderer();
//    VectorRenderer vectorRenderer = new VectorRenderer();
//    Circle circle = new Circle(vectorRenderer, 5);
//    circle.draw();
//    circle.resize(2);
//    circle.draw();

        // todo: Google Guice
        //Injector injector = Guice.createInjector(new ShapeModule());
        //Circle4 instance = injector.getInstance(Circle4.class);
        //instance.radius = 3;
        //instance.draw();
        //instance.resize(2);
        //instance.draw();
    }
}
