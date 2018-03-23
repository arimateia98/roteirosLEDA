package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length){
            double fator = 1.25;
            int gap = (int) (array.length/fator);
            while(gap != 0){
                for(int i = 0; i + gap <= rightIndex;i++){
                    if(array[i].compareTo(array[i + gap]) > 0){
                        Util.swap(array, i, i + gap);
                    }
                }
                gap = (int) (gap/fator);
            }
        }
       
    }
}
