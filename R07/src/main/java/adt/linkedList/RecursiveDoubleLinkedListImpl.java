package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}
	
	@Override
	public void insert(T element){
		if(isEmpty()){
			this.setData(element);
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
		}else if(this.getNext().isEmpty()){
			this.getNext().setData(element);
			this.getNext().setNext(new RecursiveDoubleLinkedListImpl<T>());
			((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
		}else{
			this.getNext().insert(element);
		}
		
	}
	
	@Override
	public void remove(T element){
		if(!this.isEmpty()){
			if(this.getData().equals(element)){
				if(this.getPrevious().isEmpty() && this.getNext().isEmpty()){
					this.setData(null);
				}else{
					this.setData(this.getNext().getData());
					this.setNext(this.getNext().getNext());
					if(this.getNext() != null){
						((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
					}
				}
			}else{
				this.getNext().remove(element);
			}
		}
	}
	
	
	

	@Override
	public void insertFirst(T element) {
		if(element != null){
			RecursiveDoubleLinkedListImpl<T> auxHead = new RecursiveDoubleLinkedListImpl<T>(this.getData(),this.getNext(),this.getPrevious());
			this.setData(element);
			this.setNext(auxHead);
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			auxHead.setPrevious(this);
			if(auxHead.getNext() != null){
				((RecursiveDoubleLinkedListImpl<T>) auxHead.getNext()).setPrevious(auxHead);
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!this.isEmpty())
			this.setData(this.getNext().getData());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			if(this.isEmpty()){
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
			}else{
				this.setNext(this.getNext().getNext());
		}
	}

	@Override
	public void removeLast() {
		if(!isEmpty()){
			if(this.getNext().isEmpty()){
				this.setData(null);
			}else{
				((DoubleLinkedList<T>) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
