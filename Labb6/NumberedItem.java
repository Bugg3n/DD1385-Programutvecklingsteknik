

public class NumberedItem<T> implements Comparable<NumberedItem<T>> {
    private T item;
    private int index;

    public NumberedItem(int index, T item) {
        this.item = item;
        if (index < 0)
            this.index = 0;
        else
            this.index = index;
    }

    public int compareTo(NumberedItem<T> numberedItem) {
        return this.index - numberedItem.index;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof NumberedItem<?>) {
            NumberedItem<?> numberedItem = (NumberedItem<?>) object;
            return this.index == numberedItem.index;
        }
        return false;
        
    }

    public String toString(){
        return "Index: " + index + " Value: " + item.toString();
    }
}
