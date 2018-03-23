package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(element != null){
			if (isFull()){
				throw new QueueOverflowException();
			}else{
				try {
					stack1.push(element);
				} catch (StackOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public T dequeue() throws QueueUnderflowException {
		T retorno = null;
		if (isEmpty()){
			throw new QueueUnderflowException();
		}else{
			try{
				while(!isEmpty()){
					stack2.push(stack1.pop());
				}
				retorno = stack2.pop();
				while(!stack2.isEmpty()){
					stack1.push(stack2.pop());
				}
			}catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
			}
		}
		return retorno;
	}

	@Override
	public T head() {
		T retorno = null;
		if(!isEmpty()){
			try{
				while(!isEmpty()){
					stack2.push(stack1.pop());
				}
				retorno = stack2.top();
				while(!stack2.isEmpty()){
					stack1.push(stack2.pop());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
