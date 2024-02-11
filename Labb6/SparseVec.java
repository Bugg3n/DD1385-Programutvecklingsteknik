import java.util.*;
public interface SparseVec<E extends Comparable<E>> {
    public void add(E elem);           // add at lowest index >= 0 and not it use                                              
    public void add(int pos, E elem);  // add elem at pos, if pos is occupied replace with elem, if pos<0 use index = 0        
    public int indexOf(E elem);        // find first (lowest index) occurence of elem, return its index,                       
                                       // if not found, return -1                                                              
    public void removeAt(int pos);     // if index pos is not used, nothing happens                                            
    public void removeElem(E elem);    // remove first occurence (lowest index) of elem                                        
    public int size();
    public int minIndex();             // lowest index in use, if vector is empty return -1                                    
    public int maxIndex();             // highest index in use, if vector is empty return -1                                   
    public String toString();          // one line per element with index and value, sorted by index
    public E get(int pos);             // return null if not available                                                         
    public Object[] toArray();         // return the SparseVector as a regular array with value null at unused indexes
    public List<E> sortedValues();     // return a List of the values of the Vector in sorted order        
}  
