package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(element == null) return;
		if(isFull()){
			throw new HashtableOverflowException();
		}
		int i = 0;
		boolean inserted = false;
		boolean not_equal = true;
		do{
			int posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element,i);
			if(this.table[posicao] == null || this.table[posicao].equals(new DELETED())){
				table[posicao] = element;
				super.elements++;
				inserted = true;
			} else if(this.table[posicao].equals(elements)){
	        	not_equal = false;
			}else{
				super.COLLISIONS++;
			}
			i++;
		}while(i < table.length && !inserted && not_equal);


	}

	@Override
	public void remove(T element) {
		if(element != null && !this.isEmpty()){
			int posicao = indexOf(element);
			if(posicao != -1){
				this.table[posicao] = new DELETED();
				super.elements --;
			}
		}
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if(indexOf(element) != -1){
			retorno = element;
		}
		return retorno;
		
	}

	@Override
	public int indexOf(T element) {
		if(element == null) return -1;
		int retorno = -1;
		int i = 0;
		boolean not_null = true;
		int posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element,i);
		while(retorno == -1 && i < table.length && not_null){
			if(this.table[posicao] == null){
				not_null = false;
			}else if(this.table[posicao].equals(element)){
				retorno = posicao;
			}else{
				i++;
				posicao = ((HashFunctionLinearProbing<T>) hashFunction).hash(element,i);
			}
		}
		return retorno;
	}
		
}