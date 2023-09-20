package structures.lists.lakman.queue;
import java.util.LinkedList;

/**
 * Creates an Animal queue.
 * <p>
 * See Lakman p. 247
 */
public class AnimalQueue {
    private LinkedList<Animal> animals;
    private int order;

    public AnimalQueue() {
        animals = new LinkedList<>();
        order = 0;
    }

    public void enqueue(Animal a) {
        a.setOrder(++order);
        animals.addLast(a);
    }

    public Animal dequeueAny() {
        if (animals.isEmpty()) throw new IndexOutOfBoundsException();
        return animals.poll();
    }

    public Dog dequeueDogs() {
        for (Animal animal: animals) {
            if (animal instanceof Dog) {
                animals.remove(animal);
                return (Dog) animal;
            }
        }
        throw new UnsupportedOperationException();
    }

    public Cat dequeueCats() {
        for (Animal animal: animals) {
            if (animal instanceof Cat) {
                animals.remove(animal);
                return (Cat) animal;
            }
        }
        throw new UnsupportedOperationException();
    }
}