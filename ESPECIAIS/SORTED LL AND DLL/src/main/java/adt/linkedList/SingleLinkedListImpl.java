package adt.linkedList;

public class SingleLinkedListImpl<T extends Comparable<T>> implements LinkedList<T>{

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int contador = 0;
		SingleLinkedListNode<T >auxHead = this.head;
		while(!auxHead.isNIL()){
			contador ++;
			auxHead = auxHead.next;
		}
		return contador;
	}

	@Override
	public T search(T element) {
		T retorno = null;
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.isNIL() && retorno == null){
			if(aux.getData().equals(element)){
				retorno = element;
			}
			aux = aux.getNext();
		}
		return retorno;
	}


	@Override
	public void insert(T element) {
		if(element == null) return;
		
		SingleLinkedListNode<T> auxHead = getHead();
		
		if(head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
			newHead.next = getHead();
			head = newHead;
		} else {
			if(auxHead.getData().compareTo(element) > 0){
				this.head = new SingleLinkedListNode<T>(element, auxHead);
			}else{
				boolean inserted = false;
				while(!auxHead.next.isNIL() && !inserted) {
					if(auxHead.getNext().getData().compareTo(element) > 0){
						SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element,auxHead.getNext());
						auxHead.setNext(newNode);
						inserted = true;
					}else{
						auxHead = auxHead.getNext();
					}
				}
				if(!inserted){
					SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
					newNode.next = auxHead.getNext();
					auxHead.next = newNode;
				}
			}
			
		}
	}


	@Override
	public void remove(T element) {
		if(isEmpty() || element == null) return;
		if(element == this.head.data){
			this.head = this.head.next;
		}else{
			SingleLinkedListNode<T> auxHead = this.head;
			SingleLinkedListNode<T> previous = auxHead;
			while(!auxHead.isNIL() && !auxHead.data.equals(element)){
				previous = auxHead;
				auxHead = auxHead.getNext();
			}
			if(!auxHead.isNIL()){
				previous.setNext(auxHead.getNext());
			}
		}
			
		
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		int i = 0;
		SingleLinkedListNode<T> auxHead = this.head;
		while(!auxHead.isNIL()){
			array[i] = auxHead.data;
			auxHead = auxHead.next;
			i++;
		}
		return array;
		
	}
	
	public int indexOf(T element){
		int retorno = -1;
		SingleLinkedListNode<T> auxhead = this.head;
		int i = 0;
		while(!auxhead.isNIL() && retorno == -1){
			if(auxhead.getData().equals(element)){
				retorno = i;
			}else{
				i ++;
				auxhead = auxhead.getNext();
			}
		}
		return retorno;
	}
	
	public SingleLinkedListNode<T> get(int index){
		SingleLinkedListNode<T> auxhead = this.head;
		for(int i = 0; i < index; i++){
			auxhead = auxhead.getNext();
		}
		return auxhead;
	
		
	}
	
	public void uniao(SingleLinkedListImpl<T> lista){
		for(int i = 0; i < lista.size(); i++){
			this.insert(lista.get(i).getData());
		}
	}
	
	public SingleLinkedListImpl<T> intersecao(SingleLinkedListImpl<T> lista){
		SingleLinkedListImpl<T> retorno = new SingleLinkedListImpl<T>();
		for(int i = 0; i < this.size(); i++){
			for (int j = 0; j < lista.size(); j++){
				if(this.get(i).getData().equals(lista.get(j).getData())){
					retorno.insert(this.get(i).getData());
				}
			}
		}
		return retorno;
		
	}
	
	public void removerDuplicados(){
		SingleLinkedListNode<T> auxNode = this.getHead();
		while(!auxNode.isNIL()){
			SingleLinkedListNode<T> searchNode = auxNode;
			while(!searchNode.isNIL() && !searchNode.getNext().isNIL()){
				if(searchNode.getNext().getData().equals(auxNode.getData())){
					searchNode.setNext(searchNode.getNext().getNext());
				}
				searchNode = searchNode.getNext();
			}
			auxNode = auxNode.getNext();
		}
		
	}
	
	public void reverse(){
		SingleLinkedListNode<T> aux1 = new SingleLinkedListNode<T>();
		SingleLinkedListNode<T> aux2 = this.head;
		SingleLinkedListNode<T> aux3 = new SingleLinkedListNode<T>();
		while(!aux2.isNIL()){
			aux1 = aux2.getNext();
			aux2.next = aux3;
			aux3 = aux2;
			aux2 = aux1;
		}
		this.setHead(aux3);
	}
	
	public void removeIndex(int index){
		SingleLinkedListNode<T> aux = this.getHead();
		for(int i = 0;i < index; i++){
			aux = aux.getNext();
		}
		aux.setData(aux.getNext().getData());
		if(!aux.getNext().isNIL()){
			aux.setNext(aux.getNext().getNext());
		}
	}
	

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
	public static void main(String[] args) {
		SingleLinkedListImpl<Integer> a = new SingleLinkedListImpl<Integer>();
			a.insert(5);
			a.insert(3);
			a.insert(4);
			a.insert(7);
			a.insert(-1);
			a.insert(-1);
			a.insert(4);
			a.insert(2);
			a.insert(8);
			a.insert(8);
			a.insert(5);
			a.insert(3);
			a.removerDuplicados();
			a.removeIndex(2);
			a.reverse();
	}
	

}
