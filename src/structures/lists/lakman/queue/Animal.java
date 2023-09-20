package structures.lists.lakman.queue;

public abstract class Animal {
    private int order;
    protected String name;
    public Animal(String name) {
        this.name = name;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isOlder(Animal animal) {
        return this.order < animal.getOrder();
    }
}
