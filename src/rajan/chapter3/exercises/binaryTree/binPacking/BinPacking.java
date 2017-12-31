package rajan.chapter3.exercises.binaryTree.binPacking;

import rajan.chapter3.exercises.binaryTree.BinarySearchTree;
import rajan.chapter3.exercises.binaryTree.Node;

/**
 * This class solves the bin packing packing problem in O(n*lg(n)) time. It
 * implements two strategy: best fit heuristic and worst fit heuristic. In best
 * fit heuristic object is inserted in bin which is least empty after insertion
 * and vice versa in worst fit heuristic. Its constructor takes heuristic enum
 * to insert the objects in bin pack. This class uses Binary search tree for
 * storing the bins and thus insertion takes O(lg(n)).
 * 
 * @author rajan
 *
 */
public class BinPacking {
	private PackingStrategy strategy;
	private BinarySearchTree<Bin> tree = new BinarySearchTree<>();
	private int totalBins = 0;

	public BinPacking(PackingStrategy strategy) {
		super();
		this.strategy = strategy;
	}

	/**
	 * First find the best bin and insert object in that.
	 * 
	 * @param object
	 */
	public void insertInBin(MetalObject object) {
		if (tree.isEmpty()) {
			Bin newBin = new Bin();
			newBin.addInBin(object);
			tree.add(newBin);
			totalBins++;
		} else {
			Node<Bin> bestFitBin = findBestFitBin(object, strategy);
			if (bestFitBin != null) {
				tree.delete(bestFitBin.getData());
			} else {
				bestFitBin = new Node<>(new Bin());
				totalBins++;
			}
			bestFitBin.getData().addInBin(object);
			tree.add(bestFitBin.getData());
		}
	}

	public Node<Bin> findBestFitBin(MetalObject object, PackingStrategy strategy) {
		return traverse(tree.getRoot(), null, object.getWeight(), strategy);
	}

	/**
	 * It traverses the whole tree and returns the best node found according to the
	 * strategy. If no object is found then it returns null;
	 * 
	 * @param root
	 * @param foundNode
	 * @param weight
	 * @param strategy
	 * @return
	 */
	private Node<Bin> traverse(Node<Bin> root, Node<Bin> foundNode, double weight, PackingStrategy strategy) {
		if (root != null) {
			double weightAfterInsert = root.getData().getTotalWeight() + weight;
			if (weightAfterInsert <= 1) {
				switch (strategy) {
				case BEST_FIT_HEURISTIC: {
					if ((foundNode == null || weightAfterInsert > foundNode.getData().getTotalWeight() + weight)) {
						foundNode = root;
					}
					break;
				}
				case WORST_FIT_HEURISTIC: {
					if ((foundNode == null || weightAfterInsert < foundNode.getData().getTotalWeight() + weight)) {
						foundNode = root;
					}
				}
				}
			}
			foundNode = traverse(root.getLeft(), foundNode, weight, strategy);
			return traverse(root.getRight(), foundNode, weight, strategy);
		} else {
			return foundNode;
		}
	}

	public int totalBinsUsed() {
		return totalBins;
	}

	public void printBins() {
		tree.display(tree.getRoot());
	}

	public static void main(String[] args) {
		BinPacking packing = new BinPacking(PackingStrategy.BEST_FIT_HEURISTIC);
		MetalObject[] objects = { new MetalObject(0.1), new MetalObject(0.4), new MetalObject(0.3),
				new MetalObject(0.7), new MetalObject(0.5), new MetalObject(0.6), new MetalObject(0.9),
				new MetalObject(0.1) };
		for (MetalObject object : objects) {
			if (object.getWeight() == 0.1) {
				packing.insertInBin(object);
			} else {
				packing.insertInBin(object);
			}
		}
		System.out.println("total bins are: " + packing.totalBinsUsed());
		packing.printBins();
	}
}
