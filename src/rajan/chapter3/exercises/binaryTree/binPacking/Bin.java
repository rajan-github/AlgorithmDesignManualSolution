package rajan.chapter3.exercises.binaryTree.binPacking;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a bin which can store @see MetalObject and total weight
 * of this bin cannot be more than 1 unit.
 * 
 * @author rajan
 *
 */
public class Bin implements Comparable<Bin> {
	private List<MetalObject> binContent = new ArrayList<>();
	private double totalWeight = 0;

	public double addInBin(MetalObject object) {
		double total = totalWeight + object.getWeight();
		if (total <= 1) {
			binContent.add(object);
			totalWeight = total;
		} else {
			System.out.println("Overflow of bag!");
			total = -1;
		}
		return total;
	}

	public double removeFromBin(MetalObject object) {
		boolean isDeleted = binContent.remove(object);
		double total = -1;
		if (isDeleted) {
			totalWeight -= object.getWeight();
			total = totalWeight;
		}
		return total;
	}

	public boolean isPartiallyFilled() {
		return totalWeight < 1;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	@Override
	public int compareTo(Bin other) {
		double diff = (this.totalWeight - other.totalWeight);
		int returnValue = 0;
		if (diff > 0) {
			returnValue = 1;
		} else if (diff < 0) {
			returnValue = -1;
		}
		return returnValue;
	}

	@Override
	public String toString() {
		return "Bin  totalWeight=" + totalWeight + "]";
	}

}
