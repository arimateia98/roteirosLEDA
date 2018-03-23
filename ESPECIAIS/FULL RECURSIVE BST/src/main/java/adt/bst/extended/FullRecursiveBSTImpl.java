package adt.bst.extended;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class FullRecursiveBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FullRecursiveBST<T> {

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> maximum(){
		return maximum((BSTNode<T>) this.getRoot());
	}
	
	public BSTNode<T> maximum(BSTNode<T> node){
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			if(node.getRight().isEmpty()){
				result = node;
			}else{
				result = maximum((BSTNode<T>) node.getRight());
			}
		}
		return result;
	}
	
	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> minimum(){
		return minimum((BSTNode<T>) this.getRoot());
	}

	public BSTNode<T> minimum(BSTNode<T> node){
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			if(node.getLeft().isEmpty()){
				result = node;
			}else{
				result = minimum((BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}
	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a direita
	 * entao o sucessor sera o minimum do filho a direita. Caso contrario
	 * o sucessor sera o primeiro ascendente a ter um valor maior.
	 */
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> achei = sucessor((BSTNode<T>) this.search(element));
		return achei;
		
	}
	
	public BSTNode<T> sucessor(BSTNode<T> node){
		BSTNode<T> aux = null;
		if(!node.isEmpty()){
			if(!node.getRight().isEmpty()){
				aux = minimum((BSTNode<T>) node.getRight());
			}else{
				aux = (BSTNode<T>) node.getParent();
				if(aux != null && !aux.isEmpty() && node.equals(aux.getRight())){
					aux = loopSucessor(aux,(BSTNode<T>) aux.getParent());				
				}
			}
		}
		return aux;
		
	}
	
	public BSTNode<T> loopSucessor(BSTNode<T> node, BSTNode<T> aux){
		if(aux != null && !aux.isEmpty() && node.equals(aux.getRight())){
			aux = loopSucessor(aux,(BSTNode<T>) aux.getParent());
		}
		return aux;
	}
			

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a esquerda
	 * entao o predecessor sera o maximum do filho a esquerda. Caso contrario
	 * o predecessor sera o primeiro ascendente a ter um valor menor.
	 */
	@Override
	public BSTNode<T> predecessor(T element) {
		return predecessor((BSTNode<T>) this.search(element));
	}
	
	public BSTNode<T> predecessor(BSTNode<T> node){
		BSTNode<T> aux = null;
		if(!node.isEmpty()){
			if(!node.getLeft().isEmpty()){
				aux = maximum((BSTNode<T>) node.getLeft());
			}else{
				aux = (BSTNode<T>) node.getParent();
				if(aux != null & !aux.isEmpty() && node.equals(aux.getLeft())){
					aux = loopPredecessor(aux,(BSTNode<T>) aux.getParent());
				}
			}
		}
		return aux;
	}
	
	public BSTNode<T> loopPredecessor(BSTNode<T> node,BSTNode<T> aux){
		if(aux != null & !aux.isEmpty() && node.equals(aux.getLeft())){
			aux = loopPredecessor(aux,(BSTNode<T>) aux.getParent());
		}
		return aux;
	}

	@Override
	public T[] elementsAtDistance(int k) {
		ArrayList<T> array = new ArrayList<T>();
		elementsAtDistance(array,k,this.getRoot());
		return (T[]) array.toArray(new Comparable[0]);
	}
	
	
	public void elementsAtDistance(ArrayList<T> array,int k, BSTNode<T> node){
		if(node.isEmpty()) return;
		if(k == 0){
			array.add(node.getData());
		}else{
			k--;
			elementsAtDistance(array,k,(BSTNode<T>) node.getLeft());
			elementsAtDistance(array,k,(BSTNode<T>) node.getRight());
		}
	}
	
	public BSTNode<T> estatisticaDeOrdem(int k ){
		
		BSTNode<T> achei = estatisticaDeOrdem(this.getRoot(),k);
		return achei;
	}
	
	public BSTNode<T> estatisticaDeOrdem(BSTNode<T> node,int k){
		int result = k - (size((BSTNode<T>) node.getLeft()) + 1);
		if(result == 0){
			return node;
		}else if(result > 0){
			return estatisticaDeOrdem((BSTNode<T>) node.getRight(), result);
		}else{
			return estatisticaDeOrdem((BSTNode<T>) node.getLeft(), k);
		}
		
	}
	
	public static void main(String[] args) {
		FullRecursiveBSTImpl<Integer> a = new FullRecursiveBSTImpl<>();
		a.insert(8);
		a.insert(4);
		a.insert(12);
		a.insert(3);
		a.insert(5);
		a.insert(11);
		a.insert(13);
		a.insert(2);
		a.insert(6);
		a.insert(10);
		a.insert(14);
		a.estatisticaDeOrdem(4);
		a.estatisticaDeOrdem(11);
		a.sucessor(6);
		a.elementsAtDistance(0);
		
	}
}
