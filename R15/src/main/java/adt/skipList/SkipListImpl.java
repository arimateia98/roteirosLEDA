package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T> [] update = new SkipListNode[maxHeight];
		SkipListNode<T> aux = this.root;
		for(int i = maxHeight - 1; i >= 0; i--){
			while(aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if(aux.getKey() == key){
			aux.setValue(newValue);
		}else{
			aux = new SkipListNode(key,height,newValue);
			for(int i = 0; i < height; i++){
				aux.getForward()[i] = update[i].getForward(i);
				update[i].getForward()[i] = aux;
			}		
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> aux = this.root;
		for(int i = maxHeight - 1; i >= 0; i--){
			while(aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if(aux.getKey() == key){
			int i = 0;
			while(i < this.maxHeight && update[i].getForward()[i].equals(aux)){
				update[i].getForward()[i] = aux.getForward(i);
				i++;
			}
		}
	}

	@Override
	public int height() {
		int height = 0;
		int i = maxHeight - 1;
		while(i >= 0 && height == 0){
			if(!root.getForward(i).equals(NIL)){
				height = i + 1;
			}else{
				i --;
			}
		}
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = this.root;
		SkipListNode<T> target = null;
		for(int i = maxHeight - 1; i >= 0; i--){
			while(aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
		}
		aux = aux.getForward(0);
		if(aux.getKey() == key){
			target = aux;
		}
		return target;
			
	}

	@Override
	public int size() {
		SkipListNode<T> aux = this.root.getForward(0);
		int size = 0;
		while(!aux.equals(NIL)){
			size ++;
			aux = aux.getForward(0);
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		int size = this.size() + 2;
		SkipListNode<T>[] array = new SkipListNode[size];
		SkipListNode<T> aux = this.root;
		for(int i = 0; i < size; i++){
			array[i] = aux;
			aux = aux.getForward(0);
		}
		return array;
		
	}
	
	public String imprimePorAltura(){
		String result = "";
		SkipListNode<T> aux = root;
		for(int i = maxHeight - 1; i >= 0; i--){
			aux = root;
			while(!aux.equals(NIL)){
				if(aux.height() == i+1 && !aux.equals(root)){
					result += aux.getValue() + ",";
				}
				aux = aux.getForward(i);
			}
		}
		return result;
	}
	

public static void main(String[] args) {
	SkipListImpl<Integer> a = new SkipListImpl<>(4);
	a.insert(8,8,4);
	a.insert(14,14,4);
	a.insert(21,21,4);
	a.insert(4,4,3);
	a.insert(12,12,3);
	a.insert(18,18,3);
	a.insert(17,17,2);
	a.insert(25,25,2);
	System.out.println(a.imprimePorAltura());
}

}
