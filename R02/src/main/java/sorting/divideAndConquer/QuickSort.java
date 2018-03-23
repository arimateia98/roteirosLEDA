package sorting.divideAndConquer;

import java.util.Random;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex){
			int m = partition(array,leftIndex,rightIndex);
			sort(array,leftIndex,m-1);
			sort(array,m+1,rightIndex);
		}
	}
	
	public int partition(T[] array,int leftIndex,int rightIndex){
		int pivot = leftIndex;
		int i = leftIndex;
		int j = leftIndex +1;
		Random random = new Random(24532123);
		Util.swap(array, leftIndex, random.nextInt(rightIndex - leftIndex + 1) + leftIndex);
		while (j <= rightIndex){
			if (array[pivot].compareTo(array[j]) > 0){
				i++;
				Util.swap(array, i, j);
			}
			j++;
		}
		Util.swap(array, pivot, i);
		return i;
	}
	
}
