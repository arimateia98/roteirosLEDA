package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   protected BSTNode<T> root;

   public BSTImpl() {
      root = new BSTNode<T>();
   }

   public BSTNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return root.isEmpty();
   }

   @Override
   public int height() {
      return height(this.getRoot());
   }

   private int height(BSTNode<T> node) {
      int height = -1;
      if (!node.isEmpty()) {
         height = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
      }
      return height;
   }

   @Override
   public BSTNode<T> search(T element) {
      return search(this.getRoot(), element);

   }

   private BSTNode<T> search(BSTNode<T> node, T element) {
      BSTNode<T> retorno = null;
      if (node.isEmpty() || node.getData().equals(element)) {
         retorno = node;
      } else {
         if (element.compareTo(node.getData()) < 0) {
            retorno = search((BSTNode<T>) node.getLeft(), element);
         } else {
            retorno = search((BSTNode<T>) node.getRight(), element);
         }
      }
      return retorno;

   }

   @Override
   public void insert(T element) {
      insert(this.getRoot(), element);
   }

   private void insert(BSTNode<T> node, T element) {
      if (element != node.getData() && element != null) {
         if (node.isEmpty()) {
            node.setData(element);
            node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
            node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
         } else {
            if (element.compareTo(node.getData()) < 0) {
               insert((BSTNode<T>) node.getLeft(), element);
            } else {
               insert((BSTNode<T>) node.getRight(), element);
            }
         }
      }
   }

   @Override
   public BSTNode<T> maximum() {
      return maximum(this.getRoot());
   }

   public BSTNode<T> maximum(BSTNode<T> node) {
      if (node.isEmpty())
         return null;
      while (!node.getRight().isEmpty()) {
         node = (BSTNode<T>) node.getRight();
      }
      return node;
   }

   @Override
   public BSTNode<T> minimum() {
      return minimum(this.getRoot());
   }

   public BSTNode<T> minimum(BSTNode<T> node) {
      if (node.isEmpty())
         return null;
      while (!node.getLeft().isEmpty()) {
         node = (BSTNode<T>) node.getLeft();
      }
      return node;
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> aux = search(element);
      if (aux.isEmpty())
         return null;
      BSTNode<T> retorno = null;
      if (!aux.getRight().isEmpty()) {
         retorno = minimum((BSTNode<T>) aux.getRight());
      } else {
         retorno = (BSTNode<T>) aux.getParent();
         while (retorno != null && !retorno.isEmpty() && aux.equals(retorno.getRight())) {
            aux = retorno;
            retorno = (BSTNode<T>) retorno.getParent();
         }
      }
      return retorno;
   }

   @Override
   public BSTNode<T> predecessor(T element) {
      BSTNode<T> aux = search(element);
      if (aux.isEmpty())
         return null;
      BSTNode<T> retorno = null;
      if (!aux.getLeft().isEmpty()) {
         retorno = maximum((BSTNode<T>) aux.getLeft());
      } else {
         retorno = (BSTNode<T>) aux.getParent();
         while (retorno != null && !retorno.isEmpty() && aux.equals(retorno.getLeft())) {
            aux = retorno;
            retorno = (BSTNode<T>) retorno.getParent();
         }
      }
      return retorno;
   }

   @Override
   public void remove(T element) {
      remove(this.search(element));
   }

   private void remove(BSTNode<T> node) {
      if (node.isEmpty())
         return;
      if (node.isLeaf()) {
         node.setData(null);
         node.getLeft().setParent(null);
         node.getRight().setParent(null);
      } else if (child(node) == 1) {
         if (node.equals(this.getRoot())) {
            if (!node.getLeft().isEmpty()) {
               BSTNode<T> aux = (BSTNode<T>) node.getLeft();
               node.setData(aux.getData());
               node.setLeft(aux.getLeft());
               node.setRight(aux.getRight());
            } else {
            	BSTNode<T> aux = (BSTNode<T>) node.getRight();
                node.setData(aux.getData());
                node.setLeft(aux.getLeft());
               node.setRight(aux.getRight());
            }
         } else if (node.equals(node.getParent().getLeft())) {
            if (!node.getLeft().isEmpty()) {
               node.getParent().setLeft(node.getLeft());
               node.getLeft().setParent(node.getParent());
            } else {
               node.getParent().setLeft(node.getRight());
            }
         } else {
            if (!node.getLeft().isEmpty()) {
               node.getParent().setRight(node.getLeft());
            } else {
               node.getParent().setRight(node.getRight());
            }
         }
      } else {
         BSTNode<T> sucessor = sucessor(node.getData());
         T novovalor = sucessor.getData();
         remove(sucessor);
         node.setData(novovalor);
         sucessor.getRight().setParent(node);
         sucessor.getLeft().setParent(node);
      }

   }

   private int child(BSTNode<T> node) {
      int retorno = 0;
      if (!node.getRight().isEmpty())
         retorno++;
      if (!node.getLeft().isEmpty())
         retorno++;
      return retorno;
   }

   @Override
   public T[] preOrder() {
      T[] array = (T[]) new Comparable[this.size()];
      preOrder(array, this.getRoot(), 0);
      return array;
   }

   private int preOrder(T[] array, BSTNode<T> node, int index) {
      if (!node.isEmpty()) {
         array[index++] = (T) node.getData();
         index = preOrder(array, (BSTNode<T>) node.getLeft(), index);
         index = preOrder(array, (BSTNode<T>) node.getRight(), index);
      }
      return index;
   }

   @Override
   public T[] order() {
      T[] array = (T[]) new Comparable[this.size()];
      order(array, this.getRoot(), 0);
      return array;
   }

   private int order(T[] array, BSTNode<T> node, int index) {
      if (!node.isEmpty()) {
         index = order(array, (BSTNode<T>) node.getLeft(), index);
         array[index++] = (T) node.getData();
         index = order(array, (BSTNode<T>) node.getRight(), index);
      }
      return index;
   }

   @Override
   public T[] postOrder() {
      T[] array = (T[]) new Comparable[this.size()];
      postOrder(array, this.getRoot(), 0);
      return array;
   }

   private int postOrder(T[] array, BSTNode<T> node, int index) {
      if (!node.isEmpty()) {
         index = postOrder(array, (BSTNode<T>) node.getLeft(), index);
         index = postOrder(array, (BSTNode<T>) node.getRight(), index);
         array[index++] = (T) node.getData();
      }
      return index;
   }

   /**
    * This method is already implemented using recursion. You must understand
    * how it work and use similar idea with the other methods.
    */
   @Override
   public int size() {
      return size(root);
   }

   private int size(BSTNode<T> node) {
      int result = 0;
      // base case means doing nothing (return 0)
      if (!node.isEmpty()) { // indusctive case
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }
}
