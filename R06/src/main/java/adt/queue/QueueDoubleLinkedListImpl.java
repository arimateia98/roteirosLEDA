package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(element == null) return;
		if(isFull()){
			throw new QueueOverflowException();
		}else{
			this.list.insert(element);
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()){
			throw new QueueUnderflowException();
		}else{
			T retorno = this.head();
			this.list.removeFirst();
			return retorno;
		}
	}

	@Override
	public T head() {
		T retorno = null;
		if(!isEmpty()){
			retorno = ((SingleLinkedListImpl<T>) this.list).getHead().getData();
		}
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	@Override
	public boolean isFull() {
		return this.size == this.list.size();
	}
	

}
