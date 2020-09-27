

class Recangle
{
    protected int width, height;
    public Recangle() {

    }

    public Recangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() { return width*height ; }

    @Override
    public String toString() {
        return "Recangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public boolean isSquare()
    {
        return width==height;
    }
}


class Square extends Recangle
{
    public Square () {

    }
    public Square(int size) {
        width= height= size;
    }
    //special overrides for setting the width and the height


    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);

    }

    @Override
    public void setWidth(int width) {
        super.setHeight(width);
        super.setWidth(width);
    }

}

class RectangleFactory
{
    public static Recangle newRectangle(int w, int h)
    {
        return new Recangle(w,h);
    }
    public static Recangle newSquare(int side)
    {
        return  new Recangle(side, side);
    }

}
public class LiskovSub {

    static void useIt(Recangle r)
    {
        int width = r.getWidth();
        r.setHeight(10);
        // area = width 10
        System.out.println("Expected area of " + (width*10) + ", got " + r.getArea());

    }

    public static void main(String[] args) {
        Recangle rc = new Recangle(2,3);
        useIt(rc);
        Recangle sq = new Square();
        sq.setWidth(5);
        useIt(sq);
    }

}
