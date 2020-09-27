import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;

class Foo implements Serializable
{
    public int stuff;
    public String whatever;

    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }

}
public class CopyThroughSerializable {


    public static void main(String[] args) {
        Foo foo= new Foo(42, "life");
        //roudtrip serializes and then de serializes the object
        Foo foo2 = SerializationUtils.roundtrip(foo);

        foo2.whatever="xyz";
        System.out.println(foo);
        System.out.println(foo2);

    }
}
