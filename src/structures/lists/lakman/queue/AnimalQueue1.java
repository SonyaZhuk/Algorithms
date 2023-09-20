package structures.lists.lakman.queue;

import java.util.LinkedList;

/**
 * Creates an Animal queue.
 * <p>
 * See Lakman p. 247
 */
public class AnimalQueue1 {
    private LinkedList<Dog> dogs = new LinkedList<>();
    private LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enqueue(Animal a) {
        a.setOrder(order);
        order++;

        if (a instanceof Dog) dogs.addLast((Dog) a);
        else if (a instanceof Cat) cats.addLast((Cat) a);
    }

    public Animal dequeueAny() {
        if (dogs.size() == 0) {
            return dequeueCats();
        } else if (cats.size() == 0) {
            return dequeueDogs();
        }

        Dog dog = dogs.peek();
        Cat cat = cats.peek();

        return (dog.isOlder(cat)) ? dequeueDogs() : dequeueCats();
    }
    public Dog dequeueDogs() {
        return dogs.poll();
    }
    public Cat dequeueCats() {
        return cats.poll();
    }

    public static void main(String[] args) {
    }
}
