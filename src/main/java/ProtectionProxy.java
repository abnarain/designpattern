interface Drivable
{
    void drive();
}

class Car2 implements Drivable
{
    protected Driver driver;

    public Car2(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Car being driven");
    }
}

class Driver
{
    public int age;

    public Driver(int age) {
        this.age = age;
    }
}

class CarProxy extends Car2
{
    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if (driver.age> 16) {
            super.drive();
        } else {
            System.out.println("Driver too young");
        }


    }
}
public class ProtectionProxy {
    public static void main(String[] args) {
        //Car2 car = new Car2(new Driver(12));
        //car.drive();
        Car2 car = new CarProxy(new Driver(12));
        car.drive();


    }
}
