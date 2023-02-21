package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		if (size == 0) {
			last = new QueueNode<>(e);
			last.next = last;
			size++;
		} else {
			QueueNode<E> n = new QueueNode<>(e);
			n.next = last.next;
			last.next = n;
			last = n;
			size++;
		}
		return true;}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size >= 1) {
			return last.next.element;
		} else
			return null;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		E e = null;
		if (size == 1) {
			e = last.element;
			last = null;
			size--;
		} else { if (size > 1) {
			e = last.next.element;
			last.next = last.next.next;
			size--;
			}
		}
		return e;
	}

	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q){
		if (this.last == null && !(q.last == null)) {
			this.last = q.last;
			this.last.next = q.last.next;
			size = q.size;
		} else if (!(this.last == null) && q.last == null) {
			return;
		}  else if ((this.last == null && q.last == null)) {
			throw new NullPointerException();
		} else if (this.last == q.last) {
			throw new IllegalArgumentException();
		} else {
			QueueNode<E> n = last.next;
			last.next = q.last.next;
			last = q.last;
			last.next = n;
			size += q.size;
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		/* Konstruktor */
		private QueueIterator() {
			if (last == null) {
				pos = null;
			} else
				pos = last.next;
		}

		public boolean hasNext() {
			return !(pos == null);
		}

		public E next() {
			E e = null;
			if (hasNext()) {
				e = pos.element;
				pos = pos.next;
				if (last.next == pos) {
					pos = null;
					}
				} else {
				throw new NoSuchElementException();
			}
			return e;
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
