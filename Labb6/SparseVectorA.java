import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

public class SparseVectorA <E extends Comparable<E>> implements SparseVec<E> {
    private TreeMap<Integer, E> map;

    public SparseVectorA() {
        map = new TreeMap<>();
    }

    @Override
    public void add(E elem) {
        int key = 0;
        while (map.containsKey(key)) {
            key++;
        }
        map.put(key, elem);
    }
    

    @Override
    public void add(int pos, E elem) {
        if (pos < 0)
            pos = 0;
        map.put(pos, elem);
    }

    @Override
    public int indexOf(E elem) {
        int currentIndex = 0;
        while (!map.get(currentIndex).equals(elem)){
            if (map.ceilingEntry(currentIndex+1) == null)
                return -1;
            currentIndex = map.ceilingEntry(currentIndex+1).getKey();
        }
        return currentIndex;
    }

    @Override
    public void removeAt(int pos) {
        map.remove(pos);       
    }

    @Override
    public void removeElem(E elem) {
        map.remove(indexOf(elem));       
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public int minIndex() {
        if (map.size() == 0)
            return -1;
        return map.firstKey();
    }

    @Override
    public int maxIndex() {
        if (map.size() == 0)
            return -1;
        return map.lastKey();
    }

    @Override
    public E get(int pos) {
        return map.get(pos);
    }

    @Override
    public Object[] toArray() {
        if (map.size() > 0) {
            Object[] array = new Object[map.lastKey() + 1];
            for (Map.Entry<Integer, E> entry : map.entrySet()) {
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
        for (Map.Entry<Integer, E> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, E> entry : map.entrySet()) {
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
        vector.add('c');
        vector.add(5, 'e');
        vector.add('f');
        System.out.println(vector.toString());
        Object[] arr = vector.toArray();
        for (int i = 0; i <= vector.maxIndex(); i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println(vector.sortedValues().toString());
    }
}