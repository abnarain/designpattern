import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.lang.Integer;
class Node<T>
{
    public T value;
    public Node<T> left, right, parent;



    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
        left.parent= right.parent=this;
    }

    public Node(T i) {
        this.value =i;
    }
}

class InorderIterator<T> implements Iterator<T>
{
    private Node<T> current, root;
    private boolean yieldedStarted;
    // have to find leftmost element to start the inorder

    public InorderIterator(Node<T> root)
    {
        this.root  =current = root;
        while (current.left != null)
            current = current.left;

    }

    private boolean hasRightMostParent(Node<T> node)
    {
        if (node.parent ==null) return false;
        else {
            return (node == node.parent.left ||
                    hasRightMostParent(node.parent));
        }
    }

    @Override
    public boolean hasNext()
    {
        return current.left !=null
                || current.right !=null
                || hasRightMostParent(current);
    }

    @Override
    public T next() {
        if (!yieldedStarted)
        {
            yieldedStarted = true;
            return current.value;
        }
        if (current.right != null)
        {
            current = current.right;
            while (current.left != null)
                current = current.left;
            return current.value;
        }
        else
        {
            Node<T> p = current.parent;
            while (p !=null && current == p.right)
            {
                current = p;
                p = p.parent;
            }
            current = p;
            return current.value;

        }
    }
}

//Iterable is precisely the interface that will us to support the idea of
// sticking the high construct into a for loop
class BinaryTree<T> implements Iterable<T>
{
    private Node<T> root;
    public  BinaryTree(Node<T> roo)
    {
        this.root = roo;
    }

    @Override
    public Iterator<T> iterator() {
        return new InorderIterator<>(root);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for(T item: this) // it is basically calling iterator()
            action.accept(item); //consumer accept each of the item

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }


}
public class IterablePattern {
    public static void main(String[] args) {
         /*
         *          1
         *      /      \
         *     2        3
         * */

        //213
         Node<Integer> root = new Node<>(1,
                 new Node<>(2),
                 new Node<>(3));

         InorderIterator<Integer> it = new InorderIterator<>(root);

         while (it.hasNext())
         {
             System.out.println("fuck");
             System.out.print(""+ it.next() + ",");
         }
        System.out.println("---");


        BinaryTree<Integer> tree = new BinaryTree<>(root);
        for (int n : tree)
            System.out.println(" " + n + " ");
        System.out.println("done");
    }

}
