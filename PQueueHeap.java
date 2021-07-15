import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PQueueHeap<E> extends AbstractQueue<E> {
	private int size;
	private static final int INITIAL_CAPACITY = 11;
	private E[] store;
	
	@SuppressWarnings("unchecked")
	public PQueueHeap() {
		store = (E[]) new Object[INITIAL_CAPACITY];
	}

	@Override
	public boolean offer (E e) {
		if(e == null)
			throw new NullPointerException();
		if(size == store.length-1)
			store = Arrays.copyOf(store, store.length*2);
		this.size++; //start adding from index = 1.
		store[size] = e;
		heapUp(); //sorts array after inserting O(n) time.
		return true;
	}
	
	@Override
	public boolean add(E e) {
		if(e == null)
			throw new NullPointerException();
		
		return offer(e);
	}

	@Override
	/**
	 * Remeber its a min heap, so smallest element is stored at the top.
	 */
	public E poll() {
		if(size == 0)
			return null;
		E item = store[1];
//		System.out.println("removing item: " + item);
//		System.out.println("ITEM at last index BEFORE removal " + store[size]);
		swap(1, size--); //after swapping first and last item. remove last item by decreasing size.
//		System.out.println("ITEM at first index AFTER removal " + store[1]);
//		System.out.println("ITEM at last index AFTER removal " + store[size]);
		heapDown();
//		System.out.println("ITEM at first index AFTER sorting " + store[1]);
//		System.out.println("ITEM at last index AFTER sorting " + store[size]);
		return item;
	}

	@Override
	public E peek() {
		return size == 0 ? null : this.store[1];
	}


	@Override
	public int size() {
		return size;
	}
	
	@Override
	public E element() {
		if(size == 0)
			throw new NoSuchElementException();	
		return peek(); //return element at head
	}
	
	@Override
	public String toString() {
		String buffer = "[";
		if(size != 0) {
			buffer += "null, ";
			for(int i = 1; i < size; i++) 
				buffer += store[i] + ", ";
			buffer += store[size]; //add last element top string
		} else {
			buffer += "null";
		}
		buffer += "]";
		return buffer;
	}
	
	@Override
	public E remove() {
		if (size == 0)
			throw new NoSuchElementException();
		return poll();
	}
	
	@SuppressWarnings("unchecked")
	private void heapUp() //bubble up operation
	{	
		int current = size;
		//makes sure the newly inserted element has the smallest value.
		//traverse from middle position of array to see if element is less than every other parent element
		//compareTo returns less than zero if right element is less than left.
		while(parent(current) > 0 && ((Comparable<? super E>) store[parent(current)]).compareTo((E) store[current]) > 0) //cast element to comparable and call compareTo method.
		{	
			//if greater than parents element, then swap.
			swap(current, parent(current));
			current = parent(current); //index takes parent value.
		}
	}
	
	@SuppressWarnings("unchecked")
	private void heapDown()//sinking down operation
	{
		int parent = 1;
		while(2*parent <= size && store[2*parent] != null) //cast element to comparable and call compareTo method.
		{
			int leftChild = parent*2;
			int rightChild = (parent*2)+1;
			
			//find the lesser child
			int smallerInd = leftChild;
			
			if((leftChild < size) && ((Comparable<? super E>) store[leftChild]).compareTo((E) store[rightChild]) > 0)
				smallerInd = rightChild;
			
			if(((Comparable<? super E>) store[parent]).compareTo((E) store[smallerInd]) <= 0)
				break;
			
			swap(parent, smallerInd);
			parent = smallerInd;
		}
	}
	
    private void swap(int curr, int dest)
    {
        E tmp = store[curr];
        store[curr] = store[dest];
        store[dest] = tmp;
    }
	
	// Returns position of parent
    private int parent(int pos) { 
    	return pos / 2; 
    }
    

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
