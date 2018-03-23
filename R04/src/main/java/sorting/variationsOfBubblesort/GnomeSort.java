package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length){
            int pivot = leftIndex + 1;
            while(pivot < rightIndex + 1){
                if(pivot == leftIndex){
                    pivot ++;
                }
                if (array[pivot - 1].compareTo(array[pivot]) > 0){
                    Util.swap(array, pivot-1, pivot);
                    pivot --;
                }else{
                    pivot ++;
                }
            }
        }
           
       
    }
    
   
}