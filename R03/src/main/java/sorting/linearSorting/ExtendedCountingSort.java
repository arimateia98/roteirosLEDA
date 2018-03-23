package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
    public void sort(Integer[] array, int tamanho, int minimo) {
       if (array.length > 1){
            int[] auxiliar = new int[tamanho + 1];
            for (int i = 0; i < array.length ; i++){
                auxiliar[array[i] - minimo] ++;
                System.out.println(Arrays.toString(auxiliar));
            }
            
            for (int i = 1; i < auxiliar.length; i++){
            	auxiliar[i] = auxiliar[i] + auxiliar[i-1];
            }
            
            System.out.println("Cumulativa do vetor de contagem - " + Arrays.toString(auxiliar));
            
            Integer[] auxiliarOrdenado = new Integer[array.length];
            for (int i = 0; i < array.length; i++){
            	auxiliar[array[i] - minimo ] --;
            	auxiliarOrdenado[auxiliar[array[i] - minimo]] = array[i];
            }
            
            System.out.println(Arrays.toString(auxiliar));
            
            for(int i = 0; i < array.length; i++){
            	array[i] = auxiliarOrdenado[i];
            }
            System.out.print(Arrays.toString(array));
      
       }
	}
 
	
	public static void main(String[] args) {
		
		b.sort(a,9,-2);
	}

}

