package rajan.chapter3.exercises;

import java.util.Arrays;

public class DictionaryWithArray<K, V> {
	private static final int INITIAL_SIZE = 10;
	@SuppressWarnings("unchecked")
	private K[] key = (K[]) new Object[INITIAL_SIZE];
	@SuppressWarnings("unchecked")
	private V[] value = (V[]) new Object[INITIAL_SIZE];

	private int filledSize = 0;

	private void ensureSize() {
		if (filledSize == key.length - 1) {
			key = Arrays.copyOf(key, 2 * key.length);
			value = Arrays.copyOf(value, 2 * value.length);
		}
	}

	public void insert(K key, V value) {
		ensureSize();
		this.key[filledSize] = key;
		this.value[filledSize] = value;
		filledSize++;
	}

	public V search(K key) {
		V foundValue = null;
		for (int i = 0; i < filledSize; i++) {
			if (this.key[i].equals(key)) {
				foundValue = value[i];
				break;
			}
		}
		return foundValue;
	}

	public void delete(V value) {
		int foundIndex = -1;
		for (int i = 0; i < filledSize; i++) {
			if (this.value[i].equals(value)) {
				foundIndex = i;
				break;
			}
		}
		if (foundIndex > -1) {
			for (int i = foundIndex; i < filledSize - 1; i++) {
				key[i] = key[i + 1];
				this.value[i] = this.value[i + 1];
			}
			key[filledSize - 1] = null;
			this.value[filledSize - 1] = null;
			filledSize--;
		}
	}

	public void display() {
		for (int i = 0; i < filledSize; i++) {
			System.out.println(key[i] + ": " + value[i]);
		}
	}

	public static void main(String[] args) {
		DictionaryWithArray<String, String> map = new DictionaryWithArray<>();
		map.insert("name", "Rajan chauhan");
		map.insert("age", "24");
		map.insert("profession", "Engineer");
		map.insert("sex", "Male");
		map.display();
		map.delete("Male");
		map.display();

	}
}
