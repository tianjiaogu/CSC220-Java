package lab05;

import java.util.Arrays;

public class BinarySearchSet {
	/** represent the simple array that holds the list values */
	public double[] storage;
	/** holds the length of the storage array */
	private int capacity;
	/** holds the number of elements in the list */
	private int size;

	/** keep this TRUE for lab **/
	public boolean labFind = true;

	/** default constructor */
	public BinarySearchSet() {

		size = 0;
		capacity = 6;
		storage = new double[capacity];

	}

	
	public BinarySearchSet(double[] input) {
		// input = 5 4 6 2
		// storage = [2, 4, 5, 6, 0, 0]
		this();
		
		for(int i = 0; i < input.length ; i++) {
			insert(input[i]);
		}
		
	}

	public boolean isEmpty() {
		for (int i = 0; i < storage.length; i++) {
			if (storage[i] == 0)
				return true;
		}
		return false;
	}

	public int size() {
		for (int i = 0; i < storage.length; i++) {
			if (storage[i] != 0) {
				size++;
			}
		}
		return size;
	}

	public void grow() {
		
		double[] temp = new double[2 * capacity];
		for(int i = 0; i < storage.length; i++) {
			temp[i] = storage[i];
		}
		capacity = 2 * this.capacity;
		storage = temp;
	}

	public String toString() {

		String c1 = Double.toString(capacity);
		String c2 = Double.toString(size);
		//String[] array = new String[capacity];
		String s = "";
		for (int i = 0; i < storage.length; i++) {
			s += storage[i] + " "; 
		}
		s = "\n" + s + "capacity " + c1 + " size  " + c2;
		s += "\n";

		return s;
	}

	public void clear() {
		for (int i = 0; i < storage.length; i++) {
			storage[i] = 0;
		}
		size = 0;

	}

	public boolean insert(double newVal) {
		int index;
		if (size == capacity)
			grow();
		int newIndex = findIndex(newVal);
		if (newIndex >= 0)
			return false;
		else {

			index = -newIndex - 1;
		}
		// 1 2 5 6 0  size = 4
		//     ^ ^
		// insert 3 
		for (int i = size - 1; i >= index; i--) {
			storage[i + 1] = storage[i];
		}
		storage[index] = newVal;
		size++;
		return true;
	}

	public boolean remove(double element) {
		int index = findIndex(element);
		if (index < 0) {
			return false;
		} else
			storage[index] = 0;
		size = size - 1;
		return true;
	}

	private int sequentialFind(double target) {
		for (int i = 0; i < storage.length; i++) {
			if (target == storage[i])
				return i;
		}

		for (int j = 0; j < storage.length; j++) {
			if (target < storage[j]) {
				return -j - 1;
			}
		}
		return -size - 1;
	}

	private int binaryFind(double target) {
		int min = 0;
		int max = size - 1;
		while (min <= max) {
			int mid = (min + max) / 2;
			if (storage[mid] == target) {
				return mid;
			} else if (storage[mid] < target) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}

		}
		return -min - 1;

	}

	public int findIndex(double target) {
		if (labFind) {
			return sequentialFind(target);
		} else {
			return binaryFind(target);
		}
	}

	public boolean containsAll(double[] elements) {
		for (int i = 0; i < elements.length; i++) {
			if (this.binaryFind(elements[i]) < 0)
				return false;
		}
		return true;
	}

	public boolean contains(double element) {
		if (this.binaryFind(element) >= 0)
			return true;
		else
			return false;
	}

}
