package rajan.chapter3.exercises.binaryTree;

import java.util.Random;

public class BinarySearchTree<E extends Comparable<E>> {

	private Node<E> root;

	public void add(E item) {
		Node<E> newNode = new Node<>(item);
		if (isEmpty()) {
			root = newNode;
		} else {
			Node<E> temp = root;
			while (true) {
				if (temp.getLeft() != null && temp.getLeft().getData().compareTo(item) > 0) {
					temp = temp.getLeft();
				} else if (temp.getRight() != null && temp.getRight().getData().compareTo(item) <= 0) {
					temp = temp.getRight();
				} else {
					break;
				}
			}
			if (temp.getData().compareTo(item) > 0) {
				newNode.setLeft(temp.getLeft());
				temp.setLeft(newNode);
			} else {
				newNode.setRight(temp.getRight());
				temp.setRight(newNode);
			}
		}
	}

	public Node<E> addRecursive(E item, Node<E> parent) {
		if (parent != null && parent.getLeft() != null && parent.getData().compareTo(item) > 0) {
			return addRecursive(item, parent.getLeft());
		} else if (parent != null && parent.getRight() != null && parent.getData().compareTo(item) <= 0) {
			return addRecursive(item, parent.getRight());
		} else {
			Node<E> newNode = new Node<>(item);
			if (parent == null) {
				parent = newNode;
				root = parent;
			} else if (parent.getData().compareTo(item) < 0) {
				newNode.setRight(parent.getRight());
				parent.setRight(newNode);
			} else {
				newNode.setLeft(parent.getLeft());
				parent.setLeft(newNode);
			}
			return root;
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Node<E> getRoot() {
		return root;
	}

	public void setRoot(Node<E> root) {
		this.root = root;
	}

	public void display(Node<E> root) {
		if (root != null) {
			display(root.getLeft());
			System.out.println(root.getData());
			display(root.getRight());
		}
	}

	public Node<E> delete(E data) {
		Node<E> nodeToBeDeleted = find(data);
		if (root != null && nodeToBeDeleted != null) {
			if (root.getData().equals(data)) {
				Node<E> successor = findSuccessorInRightChild(root);
				if (successor != null) {
					successor = delete(successor.getData());
					successor.setLeft(root.getLeft());
					successor.setRight(root.getRight());
					root = successor;
				} else {
					root = root.getLeft();
				}
			} else {
				Node<E> successor = findSuccessorInRightChild(nodeToBeDeleted);
				Node<E> parent = findParent(data, root);
				if (successor != null) {
					successor = delete(successor.getData());
					successor.setLeft(nodeToBeDeleted.getLeft());
					successor.setRight(nodeToBeDeleted.getRight());
				}
				if (parent.getLeft() != null && parent.getLeft().getData().equals(nodeToBeDeleted.getData())) {
					parent.setLeft(successor);
				} else {
					parent.setRight(successor);
				}
			}
		}
		return nodeToBeDeleted;
	}

	private Node<E> findSuccessorInRightChild(Node<E> currentNode) {
		Node<E> successor = null;
		if (currentNode.getRight() != null) {
			currentNode = currentNode.getRight();
			while (currentNode.getLeft() != null) {
				currentNode = currentNode.getLeft();
			}
			successor = currentNode;
		}
		return successor;
	}

	/**
	 * this method finds the parent node of the given data. It doesn't work for the
	 * root of the tree.
	 * 
	 * @param data
	 * @param currentNode
	 * @return
	 */
	public Node<E> findParent(E data, Node<E> currentNode) {
		if (currentNode != null) {
			int diff = currentNode.getData().compareTo(data);
			if (diff < 0) {
				if (currentNode.getRight() != null) {
					if (currentNode.getRight().getData().equals(data)) {
						return currentNode;
					} else {
						return findParent(data, currentNode.getRight());
					}
				}
			} else if (diff > 0) {
				if (currentNode.getLeft() != null) {
					if (currentNode.getLeft().getData().equals(data)) {
						return currentNode;
					} else {
						return findParent(data, currentNode.getLeft());
					}
				}
			}
		}
		return null;
	}

	public Node<E> find(E data) {
		if (!isEmpty()) {
			Node<E> temp = root;
			while (temp != null) {
				if (temp.getData().compareTo(data) < 0) {
					temp = temp.getRight();
				} else if (temp.getData().compareTo(data) == 0) {
					break;
				} else {
					temp = temp.getLeft();
				}
			}
			return temp;
		} else {
			return null;
		}
	}

	public Node<E> successor(E data) {
		Node<E> root = find(data);
		return (root != null) ? findSuccessor(root.getRight(), data) : null;
	}

	public Node<E> findSuccessor(Node<E> root, E data) {
		if (root != null) {
			if (root.getLeft() != null) {
				return findSuccessor(root.getLeft(), data);
			} else {
				return root;
			}
		} else {
			return null;
		}
	}

	public static <P extends Comparable<P>> BinarySearchTree<P> concat(BinarySearchTree<P> smallTree,
			BinarySearchTree<P> largeTree) {
		if (smallTree != null && largeTree == null) {
			return smallTree;
		} else if (smallTree == null && largeTree != null) {
			return largeTree;
		} else if (smallTree != null && largeTree != null) {
			BinarySearchTree<P> newTree = new BinarySearchTree<>();
			if (!smallTree.isEmpty() && !largeTree.isEmpty()) {
				Node<P> temp = smallTree.getRoot().getRight();
				smallTree.getRoot().setRight(largeTree.getRoot());
				Node<P> successor = smallTree.findSuccessorInRightChild(smallTree.getRoot());
				smallTree.getRoot().setRight(temp);
				successor = largeTree.delete(successor.getData());
				successor.setLeft(smallTree.getRoot());
				successor.setRight(largeTree.getRoot());
				newTree.setRoot(successor);
			} else {
				newTree = (smallTree.isEmpty()) ? largeTree : smallTree;
			}
			return newTree;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		Random random = new Random();
		int[] randomInput = new int[1000];
		for (int i = 0; i < randomInput.length; i++) {
			randomInput[i] = random.nextInt();
			tree.addRecursive(randomInput[i], tree.root);
		}
		tree.display(tree.root);
		for (int i = 0; i < randomInput.length; i++) {
			tree.delete(randomInput[i]);
		}
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(), tree2 = new BinarySearchTree<>(), tree3;
		tree1.add(12);
		tree1.add(14);
		tree1.add(15);
		tree2.add(56);
		tree2.add(58);
		tree2.add(59);
		tree3 = concat(tree1, null);
		tree3.display(tree3.root);
	}
}
