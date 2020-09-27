import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, YUGE
}


interface Specification<T>
{ //criteria
    boolean isSatisified(T item);
}

interface Filter<T>
{
//spec is generally, can be color etc
    Stream<T> filter(List<T> items, Specification<T> spec);
}


class ColorSpecification implements Specification<Product>
{
    private Color color;

    public ColorSpecification(Color color ){
        this.color= color;
    }

    @Override
    public boolean isSatisified(Product item) {
        return item.color ==color;
    }

}


class SizeSpecification implements Specification<Product>
{
    private Size size;

    public SizeSpecification(Size size){
        this.size= size;
    }

    @Override
    public boolean isSatisified(Product item) {
        return item.size ==size;
    }

}

class BetterFilter implements Filter<Product>
{
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
         return items.stream().filter(p->spec.isSatisified(p));
    }
}


class AndSpecification<T> implements Specification<T>
{

    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second= second;
    }

    @Override
    public boolean isSatisified(T item) {
        return first.isSatisified(item) && second.isSatisified(item);
    }

}
public class ProductOCL {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);
        ProductFilter pf  = new ProductFilter();
        System.out.println("Green products (old)");
        pf.filterByColor(products, Color.GREEN).forEach(p-> System.out.println("-" +p.name +"is green"));

        BetterFilter bf = new BetterFilter();
        System.out.println("Green products (new)");
        bf.filter(products, new ColorSpecification(Color.GREEN)).forEach(p-> System.out.println("-" +p.name +"is green"));

        System.out.println("Large blue items:");
        bf.filter(products, new AndSpecification<>(
                new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE)))
                .forEach(p-> System.out.println("-" +p.name +"is large and blue")) ;


    }
}

