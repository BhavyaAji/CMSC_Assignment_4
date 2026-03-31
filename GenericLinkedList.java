import java.util.*;

/**
 * Generic Linked list class, implements Iterable
 * 
 * @param <T>
 */
public class GenericLinkedList<T> implements Iterable<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	/**
	 * Node class set up
	 * 
	 * @param <T>
	 */
	private class Node<T> {
		T data;
		Node<T> next;

		Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/**
	 * default constructor
	 */
	public GenericLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Generic Iterator implements list iterator
	 */
	private class GenericIterator implements ListIterator<T> {

		private Node<T> current;
		private Node<T> lastR;

		/**
		 * constructor (Default)
		 */
		public GenericIterator() {

			// points to the next node that next() will return
			current = head;
			lastR = null;
		}

		/**
		 * checks for next
		 */
		public boolean hasNext() {
			return (current != null) ? true : false;
		}

		/**
		 * returns next
		 */
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();

			lastR = current;
			current = current.next;
			return lastR.data;
		}

		/**
		 * checks for previous
		 */
		public boolean hasPrevious() {
			return (current != head && head != null) ? true : false;
		}

		/**
		 * returns previous
		 */
		public T previous() {

			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}

			Node<T> temp = head;
			Node<T> prev = null;

			while (temp != current) {
				prev = temp;
				temp = temp.next;
			}

			current = prev;
			lastR = current;

			return current.data;
		}

		/**
		 * removes last returned, which is decided by next
		 */
		public void remove() {

			if (lastR == null)
				throw new IllegalStateException();

			GenericLinkedList.this.remove(lastR.data);
			lastR = null;
		}

		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		public void set(T e) {
			throw new UnsupportedOperationException();
		}

		public void add(T e) {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * checks if linked list is empty, returns based on size
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (size < 1) ? true : false;
	}

	/**
	 * returns size
	 * 
	 * @return int
	 */
	public int size() {
		return size;
	}

	/**
	 * returns data at front of the list, head
	 * 
	 * @return generic
	 */
	public T getFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.data;
	}

	/**
	 * returns data at end of the list, tail
	 * 
	 * @return generic
	 */
	public T getLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	/**
	 * searches based on data if the list contains it
	 * 
	 * @param element
	 * @return
	 */
	public boolean contains(T element) {
		Node<T> temp = head;
		while (temp != null) {
			if (temp.data.equals(element)) {
				return true;
			}
			temp = temp.next;
		}

		return false;
	}

	/**
	 * searches based on index if the list contains ot
	 * 
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp.data;
	}

	/**
	 * removes head, sets head.next equal to head
	 * 
	 * @return
	 */
	public T removeFirst() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T tempData = head.data;
		head = head.next;
		size--;
		if (size == 0) {
			tail = null;
		}
		return tempData;
	}

	/**
	 * removes tail, sets the object before tail equal to tail
	 * 
	 * @return
	 */
	public T removeLast() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		if (size == 1) {
			return removeFirst();
		}
		Node<T> temp = head;
		while (temp.next != tail) {
			temp = temp.next;
		}
		T data = tail.data;
		tail = temp;
		tail.next = null;
		size--;

		return data;
	}

	/**
	 * removes item based on index
	 * 
	 * @param index
	 * @return
	 */
	public T remove(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return removeFirst();
		}
		if (index == size - 1) {
			return removeLast();
		}
		Node<T> temp = head;

		for (int i = 0; i < index - 1; i++) {
			temp = temp.next;
		}
		T data = temp.next.data;
		temp.next = temp.next.next;
		size--;

		return data;
	}

	/**
	 * removes item based on element
	 * 
	 * @param element
	 * @return
	 */
	public boolean remove(T element) {

		if (isEmpty()) {
			return false;
		}
		if (head.data.equals(element)) {
			removeFirst();
			return true;
		}
		Node<T> temp = head;
		while (temp.next != null) {

			if (temp.next.data.equals(element)) {

				if (temp.next == tail) {
					tail = temp;
				}
				temp.next = temp.next.next;
				size--;
				return true;
			}

			temp = temp.next;
		}

		return false;
	}

	/**
	 * adds item to front of list
	 * 
	 * @param element
	 */
	public void addFirst(T element) {
		Node<T> newNode = new Node<>(element);
		newNode.next = head;
		head = newNode;
		if (tail == null) {
			tail = newNode;
		}
		size++;
	}

	/**
	 * adds item to end of list
	 * 
	 * @param element
	 */
	public void addLast(T element) {
		Node<T> newNode = new Node<>(element);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	/**
	 * clears the list
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * converts to an array
	 * 
	 * @return
	 */
	public Object[] toArray() {

		Object[] arr = new Object[size];
		Node<T> temp = head;

		for (int i = 0; i < size; i++) {
			arr[i] = temp.data;
			temp = temp.next;
		}

		return arr;
	}

	/**
	 * returns a new iterator
	 */
	public ListIterator<T> iterator() {
		return new GenericIterator();
	}

}