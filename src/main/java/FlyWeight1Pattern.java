import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.lang.Integer;
//massive memory waste with having so many users
class User
{
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}

class User2
{
    static List<String> strings = new ArrayList<>();
    private int [] names;
    public User2(String fullName)
    {
        Function<String ,Integer> getOnAdd  = (String s)-> {
            int idx = strings.indexOf(s);
            if (idx !=-1)
                return new Integer(idx);
            else {
                strings.add(s);
                return new Integer(strings.size()-1);
            }
    };
        names = Arrays.stream(fullName.split(" ")).mapToInt(s -> getOnAdd.apply(s)).toArray();

    }
}
public class FlyWeight1Pattern {
    public static void main(String[] args) {
        //User user = new User("John Smith");
        //User user2 = new User("Jane Smith");
        //common name smith - 5 bytes wasted
        //how to build flywt while preserving the API User(String fullname)
        //now should work
        User2 user = new User2("John Smith");
        User2 user2 = new User2("Jane Smith");

    }
}
