/*enum CoordinateSystem
{
    CARTESIAN,
    POLAR
}
*/
//class Point {
//    private double x, y;
//    /*
//    Write documentation
//    @a :
//    @b : etc to be written
//    * */
//    /*
//    private Point(double a, double b, CoordinateSystem cs) {
//        switch (cs)
//        {
//            case CARTESIAN:
//                this.x = a;
//                this.y = b;
//                break;
//            case POLAR:
//                this.x = a*Math.sin(b);
//                this.y = a*Math.sin(b);
//                break;
//        }
//
//    }
//
//
//    public Point(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    simply not allowed to have another constructor with same declaration fof variable types
//    public Point(double rho, double theta) {
//        x = rho*Math.cos(theta);
//        y = rho*Math.sin(theta);
//    }
//    swift, obj C does allow it on names of var but not Java
//    */
//
//    private Point(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public static Point newCartesianPoint(double x, double y)
//    {
//        return  new Point(x,y);
//    }
//
//    public static Point newPolarPoint(double rho, double theta)
//    {
//        return new Point(rho*Math.cos(theta), rho*Math.sin(theta));
//    }
//}
//public class FactoryPattern {
//    public static void main(String[] args) {
//        Point point = Point.newPolarPoint(2,3);
//
//    }
//}
