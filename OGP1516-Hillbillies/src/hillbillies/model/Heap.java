package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

/**
 * A Heap class to store objects used for optimization of the A-Star algorithm.
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 * @param <T>
 * 			The type of object to store in this Heap.
 */
public class Heap<T extends Comparable<T>> {
	
	/**
	 * Initialize a new empty Heap
	 */
	public Heap(){
		
	}
	
	/**
	 * Return the amount of elements in this Heap.
	 */
	public int size(){
		return this.values.size();
	}
	
	/**
	 * Return the location of a given element in the Heap
	 * @param item
	 * 			The item to be located.
	 * @return The index of the given element in this Heap
	 * 			| result == this.values.indexOf(item)
	 * @throws	NoSuchElementException
	 * 			The given element is not in this Heap
	 * 			| !this.contains(item)
	 */
	public int getIndex(T item) throws NoSuchElementException{
		if (!this.contains(item))
			throw new NoSuchElementException();
		return this.values.indexOf(item);
	}
	
	/**
	 * Check whether this Heap contains the given element
	 * @param item
	 * 			The item to check for presence in this Heap.
	 * @return	true if and only if the given element is in this Heap.
	 * 			| result == this.values.contains(item) 
	 */
	public boolean contains(T item){
		return this.values.contains(item);
	}
	
	/**
	 * Check whether this Heap satisfies the Heap conditions.
	 * @return true if and only if both children of every element are not smaller than
	 * 			the element itself.
	 * 			| result == (for each element in this.values:
	 * 			|				(this.values.get(2*this.getIndex(element) + 1).compareTo(element) >= 0) &&
	 * 			|				(this.values.get(2*this.getIndex(element) + 2).compareTo(element) >= 0))
	 */
	public boolean isHeap(){
		int k = 0; int l = 1; int r;
		while (l < this.values.size()){
			r = l + 1;
			if(r < this.values.size()){
				if (this.values.get(r).compareTo(this.values.get(k)) < 0)
					return false;
			}
			if (this.values.get(l).compareTo(this.values.get(k)) < 0)
				return false;
			k += 1;
			l = 2*k + 1;
		}
		return true;
	}
	
	/**
	 * Return the item at the top of the tree.
	 */
	public T pop(){
		T item = this.values.get(0);
		T temp = this.values.get(this.values.size()-1);
		this.values.set(0, temp);
		this.values.remove(this.values.size()-1);
		this.sortDown(0);
		return item;
	}
	
	/**
	 * Add an item to this Heap.
	 * @param item
	 * 			The item to be added to this Heap.
	 * @post	The give Item has been added to this Heap
	 * 			| new.contains(item)
	 * @post	This heap satisfies the heap conditions
	 * 			| new.isHeap()
	 */
	public void add(T item){
		this.values.add(item);
		this.sortUp(this.values.size()-1);
	}
	
	/**
	 * Replace an item at the given position by the given item
	 * @param index
	 * 			The index of the element to be replaced.
	 * @param item
	 * 			The new element to be inserted
	 * @post	The amount of items is unchanged.
	 * 			| new.size() == this.size()
	 * @post	The given item is in the heap
	 * 			| new.contains(item)
	 * @post	This Heap satisfies the heap conditions
	 * 			| new.isHeap()
	 */
	public void replace(int index, T item){
		this.values.set(index, item);
		sortDown(index);
		sortUp(index);
	}
	
	/**
	 * Sort up from a given position in the heap.
	 * @param k
	 * 			The position from which to sort up.
	 * @post	This Heap satisfies the heap conditions
	 * 			| new.isHeap()
	 */
	@Raw
	private void sortUp(int k) {
		while (k > 0){
			int p = (k-1)/2;
			T item = this.values.get(k);
			T parent = this.values.get(p);
			if (item.compareTo(parent) < 0){
				this.values.set(p, item);
				this.values.set(k, parent);
				k = p;
			} else
				return;
		}
	}
	
	/**
	 * Sort downward from the given index
	 * @param k
	 * 			The index from which to sort down
	 * @post	This Heap satisfies the heap conditions
	 * 			| new.isHeap()
	 */
	@Raw
	private void sortDown(int k) {
		int l = 2*k+1; int r; int min;
		while (l < this.values.size()){
			min = l; r = l + 1;
			if (r < this.values.size()){
				if (this.values.get(r).compareTo(this.values.get(l)) < 0)
					min = r;
			}
			if (this.values.get(k).compareTo(this.values.get(min)) > 0){
				T temp = this.values.get(k);
				this.values.set(k, this.values.get(min));
				this.values.set(min, temp);
				k = min; l = 2*k + 1;
			} else
				return;
		}
	}

	/**
	 * Array storing all values in this Heap
	 */
	private List<T> values = new ArrayList<>();
}
