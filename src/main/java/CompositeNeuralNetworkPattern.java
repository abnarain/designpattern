import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

interface SomeNeurons extends Iterable<Neuron>
{
    default void connectTo(SomeNeurons other)
    {// this will make it work for both neuron and neuron layers
        if (this == other) return;
        for (Neuron from : this)
            for (Neuron to: other)
            {
                from.out.add(to);
                to.in.add(from);
            }
    }
}
class Neuron implements SomeNeurons
{
    public ArrayList<Neuron> in, out;

    // how can we have single neuron masquarade itself as a collection of neuron? to implement Iterable?
    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

    @Override
    public void forEach(Consumer<? super Neuron> action) {
        action.accept(this);
    }

    @Override
    public Spliterator<Neuron> spliterator() {
        return Collections.singleton(this).spliterator();
    }

    //    public void connectTo(Neuron other)
//    {
//        out.add(other);
//        other.in.add(this);
//    }


}

//you want to connect neurons o another layer of neurons
class NeuronLayer extends ArrayList<Neuron>
    implements SomeNeurons
{
// arrayList<> already implements iterable() so we dont have to

}

public class CompositeNeuralNetworkPattern {
    public static void main(String[] args) {
        Neuron neuron = new Neuron();
        Neuron neuron2 = new Neuron();

        NeuronLayer layer = new NeuronLayer();
        NeuronLayer layer2 = new NeuronLayer();

        /*
        want something like this:
        neuron.connectTo(neuron2);

        These 3 cases not connected wont write 3 different connectTo
        Instead make an interface that is general to connect things to one another

        neuron.connectTo(layer);
        layer.connectTo(neuron);
        layer.connectTo(layer2)

        */
        neuron.connectTo(neuron2);
        neuron.connectTo(layer);
        layer.connectTo(neuron);
        layer.connectTo(layer2);

    }
}
