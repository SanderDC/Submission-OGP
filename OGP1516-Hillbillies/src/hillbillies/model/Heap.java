package hillbillies.model;

import java.util.*;

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
	 * Add an item to this Heap.
	 * @param item
	 * 			The item to be added to this Heap.
	 */
	public void add(T item){
		this.values.add(item);
		this.sortUp(this.values.size()-1);
	}
	
	/**
	 * Sort up from a given position in the heap.
	 * @param k
	 * 			The position from which to sort up.
	 */
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
	 * Replace an item at the given position by the given item
	 * @param index
	 * 			The of the obejct to be replaced.
	 * @param item
	 * 			The new object to be inserted 
	 */
	public void insertAt(int index, T item){
		this.values.set(index, item);
		sortDown(index);
		sortUp(index);
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
	 * Sort downward from the given index
	 * @param k
	 * 			The index from which to sort down
	 */
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
	
	public int size(){
		return this.values.size();
	}
	
	public boolean contains(T item){
		return this.values.contains(item);
	}
	
	public int getIndex(T item){
		return this.values.indexOf(item);
	}

	/**
	 * Array storing all values in this Heap
	 */
	private List<T> values = new ArrayList<>();
}
