import java.util.*;
import java.util.function.Consumer;
import java.lang.Integer;
class Point2
{
    public int x, y;

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2 point2 = (Point2) o;
        return x == point2.x &&
                y == point2.y;
    }

    @Override
    public int hashCode() {
        int result =x;
        result = 31*result +y;
        return result;
    }

}

class Line
{
    public Point2 start, end;

    public Line(Point2 start, Point2 end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        if (start != null? !start.equals(line.start): line.start != null) return false;
        return end !=null ? end.equals(line.end) : line.end==null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() :0;
        result = 31 + result + (end !=null ? end.hashCode() :0);
        return result;
    }
}

class VectorObject extends ArrayList<Line>
{
    public VectorObject() {
    }
}

class VectorRectangle extends VectorObject
{
    public VectorRectangle(int x, int y, int width, int height) {

        add(new Line(new Point2(x,y), new Point2(x+width,y) ));
        add(new Line(new Point2(x+width,y), new Point2(x+width, y+height)));
        add(new Line(new Point2(x,y), new Point2( x, y+height) ));
        add(new Line(new Point2(x,y+height), new Point2(x+width,y+height)));

    }
}
class  LineToPointAdapter implements Iterable<Point2> //extends ArrayList<Point2>
{
    private static int count =0; //count of no. of points it has produced

    private static Map<Integer, List<Point2>> cache= new HashMap<>();

    private int hash;
    public LineToPointAdapter(Line line)
    {
        hash = line.hashCode();
        if (cache.get(hash) != null) return;

        System.out.println(
                String.format("%d: Generating points for line [%d,%d]-[%d,%d] (no caching)"
                , count++, line.start.x, line.start.y, line.end.x, line.end.y));

        ArrayList<Point2> points= new ArrayList<>();

        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);
        int top = Math.min(line.start.y,line.end.y);
        int bottom = Math.max(line.start.y,line.end.y);
        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx==0) {
            for (int y = top; y <= bottom; ++y) {
                points.add(new Point2(left, y));
            }
        }
        else if (dy ==0)
        {
            for (int x = left; x<=right; ++x)
            {
                points.add(new Point2(x, top));
            }
        }
        cache.put(hash, points);
    }

    @Override
    public Iterator<Point2> iterator() {
        return cache.get(hash).iterator();
    }

    @Override
    public void forEach(Consumer<? super Point2> action) {
        cache.get(hash).forEach(action);
    }

    @Override
    public Spliterator<Point2> spliterator() {
        return cache.get(hash).spliterator();
    }
}
public class AdapterPattern {
    private final static List<VectorObject> vectorObjects =
            new ArrayList<>(Arrays.asList(
                    new VectorRectangle(1,1,10,10),
                    new VectorRectangle(3,3,6,6)
            ));
    // say this is the only API to use
    // but no way to convert vector to point! so build an adapter to convert line to point2
    public static void drawPoint (Point2 p)
    {
        System.out.println(".");
    }

    private static void draw()
    {
        for (VectorObject vo : vectorObjects)
        {
            for (Line line : vo)
            {
                LineToPointAdapter adapter = new LineToPointAdapter(line);
                adapter.forEach(AdapterPattern::drawPoint);

            }
        }
    }

    public static void main(String[] args) {
        //create bunch of vector objects
        draw();
        draw();
        //calling this we are doing double the work as temp. above can be reused -draw();
        // so should implement hascode(), equality() on Line
    }
}
