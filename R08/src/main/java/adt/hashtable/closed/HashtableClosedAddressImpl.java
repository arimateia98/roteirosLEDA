package adt.hashtable.closed;

import util.Util;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends
		AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize,
			HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
														// the immediate prime
														// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method,
				realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		if(Util.isPrime(number + 1)){
			return number + 1;
		}else{
			return getPrimeAbove(number + 1);
		}
	}

	@Override
	public void insert(T element) {
		if(element != null){
			int hash = Math.abs(((HashFunctionClosedAddress) this.hashFunction).hash(element));
			if(this.table[hash] == null){
				this.table[hash] = new LinkedList<T>();
				((LinkedList<T>) this.table[hash]).add(element);
				super.elements ++;
			}else{
				LinkedList<T> aux = (LinkedList<T>) this.table[hash];
				if(aux.contains(element)){
					aux.set(aux.indexOf(element), element);
				}else{
					aux.add(element);
					super.elements++;
					super.COLLISIONS ++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null){
			int hash = Math.abs(((HashFunctionClosedAddress) this.hashFunction).hash(element));
			if(table[hash] != null){
				LinkedList<T> aux = (LinkedList<T>) table[hash];
				if(aux.contains(element)){
					if(aux.size() > 1){
						super.COLLISIONS --;
					}
					aux.remove(element);
					elements --;
					if(aux.isEmpty()){
						aux = null;
					}
				}
			}
		}
	}

	@Override
	public T search(T element) {
		T retorno = null;
		int hash = Math.abs(((HashFunctionClosedAddress) this.hashFunction).hash(element));
		if(table[hash] != null){
			if(((LinkedList<T>)table[hash]).contains(element)){
				retorno = element;
			}
		}
		return retorno;
	}

	@Override
	public int indexOf(T element) {
		int retorno = -1;
		if(element != null){
			int hash = Math.abs(((HashFunctionClosedAddress) this.hashFunction).hash(element));
			if(table[hash] != null){
				if(((LinkedList<T>) table[hash]).contains(element)){
					retorno = hash;
				}
			}
		}
		return retorno;
	}

}
