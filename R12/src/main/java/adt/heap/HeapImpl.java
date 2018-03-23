package adt.heap;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
 
import util.Util;
 
/**
 * O comportamento de qualquer heap � definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa compara��o n�o � feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {
 
	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador � utilizado para fazer as compara��es da heap. O ideal �
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap n�o precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;
 
	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
 
	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}
 
	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}
 
	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}
 
	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}
 
	@Override
	public boolean isEmpty() {
		return (index == -1);
	}
 
	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int e = 0; e <= index; e++)
			resp.add(heap[e]);
		return (T[]) resp.toArray(new Comparable[0]);
	}
 
	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private boolean valid_position(int position) {
		return position <= index;
	}
 
	private void heapify(int position) {
		int largest = position;
		int left = left(position);
		int right = right(position);
		if(valid_position(left) && comparator.compare(heap[left],heap[largest]) > 0){
			largest = left;
		}
		if(valid_position(right) && comparator.compare(heap[right],heap[largest]) > 0){
			largest = right;
		}
		if(largest != position){
			Util.swap(heap, position, largest);
			heapify(largest);
		}
		
	
	}
 
	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		if (element == null)
			return;
		heap[++index] = element;
		int i = index;
		while (i > 0 && comparator.compare(heap[parent(i)],element) < 0){
			Util.swap(heap, i, parent(i));
			i = parent(i);
		}
		
	
	}
 
	@Override
	public void buildHeap(T[] array) {
		heap = Arrays.copyOf(array, array.length);
		index = heap.length - 1;
		for (int e = index / 2; e >= 0; e--)
			heapify(e);
		
	}
 
	@Override
	public T extractRootElement() {
		// TODO Auto-generated method stub
		if (isEmpty())
			return null;
		T element = heap[0];
		heap[0] = heap[index];
		if (index != 0)
			heapify(0);
		index--;
		return element;
	}
 
	@Override
	public T rootElement() {
		// TODO Auto-generated method stub
		if (isEmpty())
			return null;
		return heap[0];
	}
 
	@Override
	public T[] heapsort(T[] array) {
		if (array == null)
			return array;
		// TODO Auto-generated method stub
		Comparator<T> cmp = this.comparator;
		this.comparator = (a, b) -> b.compareTo(a);
		int len = array.length;
		index = -1;
		buildHeap(array);
		for (int e = 0; e < len; e++)
			array[e] = extractRootElement();
		this.comparator = cmp;
		return array;
	}
 
	@Override
	public int size() {
		return index + 1;
	}
 
	public Comparator<T> getComparator() {
		return comparator;
	}
 
	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
 
	public T[] getHeap() {
		return heap;
	}
 
}