package adt.linkedList;

import java.util.Comparator;

public class DoubleLinkedListImpl<T extends Comparable<T>> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	
	
	@Override
	public void insert(T element){
		if(element== null) return;
		if (head.isNIL()) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(),last);
			this.setHead(newNode);
			this.setLast(newNode);
		}else if(this.head.getData().compareTo(element) > 0){
			this.insertFirst(element);		
		} else {
			DoubleLinkedListNode<T> auxHead= (DoubleLinkedListNode<T>) this.getHead();
			boolean inserted = false;
			while(!auxHead.getNext().isNIL() && !inserted){
				if(auxHead.getNext().getData().compareTo(element) > 0){
					DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) auxHead.getNext(),auxHead);
					((DoubleLinkedListNode<T>) auxHead.getNext()).setPrevious(newNode);
					auxHead.setNext(newNode);
					inserted = true;
					if(newNode.getData().compareTo(this.getLast().getData()) > 0){
						this.setLast(newNode);
					}
				}else{
					auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
				}
			}
			if(!inserted){
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element,new DoubleLinkedListNode<T>(),this.getLast());
				this.getLast().setNext(newNode);
				this.setLast(newNode);
			}
		}
	}
	
	@Override
	public T search(T element){
		T retorno = null;
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.getHead();
		DoubleLinkedListNode<T> auxLast = this.getLast();
		while(!auxHead.equals(auxLast) && !auxHead.getNext().equals(auxLast) 
				&& !auxHead.getData().equals(element) && !auxLast.getData().equals(element)){
			auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			auxLast = auxLast.getPrevious();
		}
		if(auxHead.getData().equals(element) || auxLast.getData().equals(element)){
			retorno = element;
		}
		return retorno;
		
	}
	
	@Override
	public void remove(T element){
		if(element != null){
			if (head.getData().equals(element)){
				this.setHead(this.getHead().getNext());
				
			}else{
				SingleLinkedListNode<T> auxHead = this.getHead();
				while(!auxHead.isNIL() && !auxHead.getData().equals(element)){
					auxHead = auxHead.getNext();
				}
				if(!auxHead.isNIL()){
					DoubleLinkedListNode<T> removido = (DoubleLinkedListNode<T>) auxHead;
					removido.getPrevious().setNext(auxHead.getNext());
					if(!removido.getNext().isNIL()){
						((DoubleLinkedListNode<T>) removido.getNext()).setPrevious(removido.getPrevious());
					}
					if(removido.equals(this.getLast())){
						this.setLast(removido.getPrevious());
					}
				}
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if(element != null){
			DoubleLinkedListNode<T> NIL = new DoubleLinkedListNode<T>();
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
			this.head = new DoubleLinkedListNode<T>(element,auxHead,NIL);
			auxHead.setPrevious((DoubleLinkedListNode<T>) this.head);
			if(this.getLast().isNIL()){
				this.setLast(auxHead);
			}else if(this.getLast().isNIL()){
				this.getLast().setPrevious(auxHead);
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()){
			if(this.size() == 1){
				this.head = new DoubleLinkedListNode<T>();
				this.last = new DoubleLinkedListNode<T>();
			}else{
				this.head = this.head.getNext();
				((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void removeLast() {
		if(!isEmpty()){
			if(this.size() == 1){
				this.head = new DoubleLinkedListNode<T>();
				this.last = new DoubleLinkedListNode<T>();
			}else{
				this.getLast().getPrevious().setNext(new DoubleLinkedListNode<T>());
				this.setLast(this.last.getPrevious());
			}
			
			
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	

	public static void main(String[] args) {
		DoubleLinkedListImpl<Integer> a = new DoubleLinkedListImpl<Integer>();
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
	}
	

}
