package sorting.divideAndConquer.threeWayQuicksort;

import java.util.Random;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex){
			int pivot = leftIndex;
			int lI = leftIndex;
			int rI = rightIndex;
			int j = leftIndex + 1;
			Random random = new Random(2345321);
			Util.swap(array, leftIndex, random.nextInt(rightIndex - leftIndex + 1) + leftIndex);
			while (j <= rI){
				if (array[pivot].compareTo(array[j]) > 0){
					lI++;
					Util.swap(array, lI, j);
					j++;
				}else if(array[j].compareTo(array[pivot]) > 0){
					Util.swap(array, rI, j);
					rI --;
				}else{
					j ++;
				}
			}
			Util.swap(array, pivot, lI);
			sort(array,leftIndex,lI-1);
			sort(array,rI+1,rightIndex);
		}
	}

	
}
