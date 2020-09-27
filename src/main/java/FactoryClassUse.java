
class Point {
    private double x, y;

    private  Point(double x, double y) { //public will make client allow
        this.x = x;
        this.y = y;
    }


    //class PointFactory
    public  static  class Factory
    {

        public static Point newCartesianPoint(double x, double y)
        {
            return new Point(x,y);
        }

        public static Point newPolarPoint(double rho, double theta)
        {
            return new Point(rho*Math.cos(theta), rho*Math.sin(theta));
        }

    }
}


public class FactoryClassUse {
    public static void main(String[] args) {
        //PointFactory.newCartesianPoint(2,3);
        //but also new Point(4,5); so move the PointFactory inside the Point class
        Point point = Point.Factory.newCartesianPoint(2,3);//is the new way to access
    }
}
