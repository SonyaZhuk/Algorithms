package algo.lakman.hard.tower;

public class Human implements Comparable<Human> {
    private int height;
    private int weight;

    public Human(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int compareTo(Human second) {
        return (this.height != second.height) ?
                ((Integer) this.height).compareTo(second.height) :
                ((Integer) this.weight).compareTo(second.weight);
    }

    public boolean isBefore(Human other) {
        return height < other.height && weight < other.weight;
    }
}
