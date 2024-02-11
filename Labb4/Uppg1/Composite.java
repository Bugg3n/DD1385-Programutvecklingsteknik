import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;

class Composite extends Component implements Iterable<Component>, Iterator<Component> {

    private List<Component> components;
    private static LinkedBlockingQueue<Component> queue = new LinkedBlockingQueue<>();
    private int next;
    private static String iteratorType = "BFS";

    public Composite(int weight, String name) {
        super(weight, name);
        components = new ArrayList<>();
        next = -1;
    }

    public int getWeight() {
        int sum = 0;
        for (Component c : components) {
            sum += c.getWeight();
        }
        return sum + this.weight;
    }
    
    public String toString() {
        String content = name + " (" + weight + ")";
        if (!components.isEmpty()) {
            content += " containing {";
            for (Component c : components) {
                content += c.toString() + ", ";
            }
            content = content.substring(0, content.length() - 2);
            content += "}";
        }
        return content;
    }

    public void add(Component component) {
        components.add(component);
    }

    public static void setIteratorType(String iteratorType) { // "DFS" or "BFS"
        Composite.iteratorType = iteratorType;
    }

    public boolean remove(Component component) {
        if (!components.remove(component)) {
            for (Component childComponent : components) {
                if(childComponent.remove(component)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public Iterator<Component> iterator() {
        resetIterator();
        queue.add(this);
        return this;
    }

    public void resetIterator() {
        next = -1;
        queue.clear();
        for (Component c : components) {
            c.resetIterator();
        }
    }

    @Override
    public boolean hasNext() {
        if (iteratorType.equals("BFS")) {
            return hasNextBFS();
        } else {
            return hasNextDFS();
        }
    }

    @Override
    public Component next() {
        if (iteratorType.equals("BFS")) {
            return nextBFS();
        } else {
            return nextDFS();
        }
    }

    private boolean hasNextBFS() {
        return !queue.isEmpty();
    }

    private Component nextBFS() {
        Component nextComponent = queue.poll();
        if (nextComponent == null) {
            throw new NoSuchElementException();
        }
        if (nextComponent instanceof Composite) {
            Composite nextComposite = (Composite) nextComponent;
            for (Component c : nextComposite.components) {
                queue.add(c);
            }
        }
        return nextComponent;
    }

    private boolean hasNextDFS() {
        if (next == -1) {
            return true;
        }
        if (next < components.size() - 1) {
            return true;
        }
        if (next == components.size() - 1) {
            return components.get(next).hasNext();
        }
        return false;
    }

    private Component nextDFS() {
        if (next == -1) {
            next++;
            return this;
        }
        if (next < components.size()) {
            Component nextComponent = components.get(next);
            if (nextComponent.hasNext()) {
                return nextComponent.next();
            } else {
                next++;
                return next();
            }
        }
        throw new NoSuchElementException();
    }
}
