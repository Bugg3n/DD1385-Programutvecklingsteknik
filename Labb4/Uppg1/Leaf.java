import java.util.Iterator;
import java.util.NoSuchElementException;

class Leaf extends Component implements Iterator<Component> {
    private boolean visited;

    public Leaf(int weight, String name){
        super(weight, name);
        visited = false;
    } 

    public int getWeight(){
        return this.weight;
    }

    public String toString(){
        return this.name + " (" + this.weight + ")";
    }

    public boolean remove(Component component){
        return false;
    }

    @Override   
    public boolean hasNext() {
        return !visited;
    }

    @Override
    public Component next() {
        if (!visited) {
            visited = true;
            return this;
        }
        throw new NoSuchElementException();
    }

    public void resetIterator() {
        visited = false;
    }
}