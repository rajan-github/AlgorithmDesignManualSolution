package rajan.chapter3.exercises;

import java.util.Comparator;
import java.util.Iterator;

public class SinglyList<T extends Comparable<T>> implements Iterable<Node<T>> {
	private Node<T> head = null;

	public void add(T item) {
		Node<T> newNode = new Node<>(item);
		newNode.setNext(head);
		head = newNode;
	}

	public void reverse() {
		Node<T> first = head;
		if (first != null) {
			Node<T> nextNode, previousNode = null;
			while (first.getNext() != null) {
				nextNode = first.getNext();
				first.setNext(previousNode);
				previousNode = first;
				first = nextNode;
			}
			first.setNext(previousNode);
			head = first;
		}
	}

	public Node<T> delete(T item) {
		Node<T> deletedNode = null;
		if (head != null) {
			Node<T> temp = head, previous = null;
			while (temp != null && !temp.getData().equals(item)) {
				previous = temp;
				temp = temp.getNext();
			}
			deletedNode = temp;
			if (previous == null) {
				head = temp.getNext();
				temp = null;
			} else {
				previous.setNext((temp != null) ? temp.getNext() : temp);
			}
		}
		return deletedNode;
	}

	public int badFibonacciNumberImpl(int n) {
		if (n < 2) {
			return n;
		}
		return badFibonacciNumberImpl(n - 1) + badFibonacciNumberImpl(n - 2);
	}

	public Comparator<Node<T>> dataComparator = new Comparator<Node<T>>() {
		@Override
		public int compare(Node<T> o1, Node<T> o2) {
			return o1.getData().compareTo(o2.getData());
		}
	};

	public void display() {
		Node<T> temp = head;
		while (temp != null) {
			System.out.print(temp.getData() + "-> ");
			temp = temp.getNext();
		}
		System.out.println();
	}

	@Override
	public Iterator<Node<T>> iterator() {
		return new Iterator<Node<T>>() {
			Node<T> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Node<T> next() {
				Node<T> temp = current;
				current = current.getNext();
				return temp;
			}
		};
	}

	public static void main(String[] args) {
		SinglyList<Integer> list = new SinglyList<>();
		list.add(1);
		list.add(2);
		SinglyList<Integer> list2 = new SinglyList<>();
		list2.add(1);
		list2.add(2);
		for (Node<Integer> node : list) {
			System.out.println(node.getData() + "-> ");
		}
	}

}
