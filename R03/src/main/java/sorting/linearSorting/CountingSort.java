package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
       if (leftIndex <= rightIndex && leftIndex >= 0 && rightIndex < array.length && array.length > 1){
            int maximo = array[leftIndex];
            for(int i = leftIndex; i <= rightIndex; i++){
                if (array[i].compareTo(maximo) > 0){
                    maximo = array[i];
                }
            }
            int[] auxiliar = new int[maximo + 1];
            for (int i = leftIndex; i <= rightIndex; i++){
                auxiliar[array[i]] ++;
            }
            
            for (int i = 1; i < auxiliar.length; i++){
            	auxiliar[i] = auxiliar[i] + auxiliar[i-1];
            }
            
            Integer[] auxiliarOrdenado = new Integer[array.length];
            for (int i = leftIndex; i <= rightIndex; i++){
            	auxiliar[array[i]] --;
            	auxiliarOrdenado[auxiliar[array[i]] + leftIndex] = array[i];
            }
            
            for(int i = leftIndex; i <= rightIndex; i++){
            	array[i] = auxiliarOrdenado[i];
            }
       }
	}
	

}


