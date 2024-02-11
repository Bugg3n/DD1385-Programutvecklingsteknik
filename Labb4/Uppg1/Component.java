public abstract class Component {

    protected String name;
    protected int weight;

    protected Component(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getWeight();

    public abstract String toString();

    public abstract boolean remove(Component component);

    public abstract boolean hasNext();

    public abstract Component next();

    public abstract void resetIterator();

}