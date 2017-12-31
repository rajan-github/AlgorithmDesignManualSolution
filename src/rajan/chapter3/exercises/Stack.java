package rajan.chapter3.exercises;

import java.util.Arrays;

public class Stack<T> {

	private int dataCount = 0;

	private T[] data;

	public Stack() {
		this(10);
	}

	@SuppressWarnings("unchecked")
	public Stack(int initialSize) {
		super();
		data = (T[]) new Object[initialSize];
	}

	public boolean isEmpty() {
		return dataCount == 0;
	}

	public T pop() {
		T temp = null;
		if (!isEmpty()) {
			temp = data[dataCount - 1];
			data[dataCount--] = null;
		}
		return temp;
	}

	public void ensureSize() {
		if (dataCount == data.length) {
			data = Arrays.copyOf(data, 2 * data.length);
		}
	}

	public void push(T item) {
		ensureSize();
		data[dataCount++] = item;
	}

	public void display() {
		for (int i = 0; i < dataCount; i++) {
			System.out.println(data[i]);
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}

}
