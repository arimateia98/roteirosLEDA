package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		if(isEmpty()){
			return 0;
		}else{
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		if(this.isEmpty()){
			return null;
		}else{
			if(this.getData().equals(element)){
				return element;
			}else{
				return this.getNext().search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null){
			if(this.isEmpty()){
				this.setData(element)		;
				this.setNext(new RecursiveSingleLinkedListImpl<T>());
			}else{
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!this.isEmpty() && element != null){
			if(this.getData().equals(element)){
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			}else{
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] retorno = (T[]) new Object[this.size()];
		toArray(retorno,this,0);
		return retorno;
	
	}
	
	private void toArray(T[] array,RecursiveSingleLinkedListImpl node,int index){
		if(!node.isEmpty()){
			array[index] = (T) node.getData();
			toArray(array,node.getNext(),index + 1);
		}
		
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
	


}
