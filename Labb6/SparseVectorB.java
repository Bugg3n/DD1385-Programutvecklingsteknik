import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

public class SparseVectorB <E extends Comparable<E>> extends TreeMap<Integer, E> implements SparseVec<E> {

    @Override
    public void add(E elem) {
        int key = 0;
        while (this.containsKey(key)) {
            key++;
        }
        this.put(key, elem);
    }
    

    @Override
    public void add(int pos, E elem) {
        if (pos < 0)
            pos = 0;
        this.put(pos, elem);
    }

    @Override
    public int indexOf(E elem) {
        int currentIndex = 0;
        while (!this.get(currentIndex).equals(elem)){
            if (this.ceilingEntry(currentIndex+1) == null)
                return -1;
            currentIndex = this.ceilingEntry(currentIndex+1).getKey();
        }
        return currentIndex;
    }

    @Override
    public void removeAt(int pos) {
        this.remove(pos);       
    }

    @Override
    public void removeElem(E elem) {
        this.remove(indexOf(elem));       
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public int minIndex() {
        if (this.size() == 0)
            return -1;
        return this.firstKey();
    }

    @Override
    public int maxIndex() {
        if (this.size() == 0)
            return -1;
        return this.lastKey();
    }

    @Override
    public E get(int pos) {
        return super.get(pos);
    }

    @Override
    public Object[] toArray() {
        if (super.size() > 0) {
            Object[] array = new Object[this.lastKey() + 1];
            for (Map.Entry<Integer, E> entry : this.entrySet()) {
                array[entry.getKey()] = entry.getValue();
            }
            return array;
        } else {
            return new Object[0];
        }
    }

    @Override
    public List<E> sortedValues() {
        List<E> list = new ArrayList<>();
        for (Map.Entry<Integer, E> entry : this.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, E> entry : this.entrySet()) {
            result.append(entry.getKey());
            result.append(' ');
            result.append(entry.getValue().toString());
            result.append('\n');
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public static void main(String[] args) {
        SparseVectorA<Character> vector = new SparseVectorA<>();
        vector.add(0, 'a');
        vector.add(1, 'b');
        vector.add(2, 'c');
        vector.add(4, 'e');
        vector.add(10, 'f');
        System.out.println(vector.toString());
        Object[] arr = vector.toArray();
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println(vector.sortedValues().toString());
    }
}