package gradingTools.comp533s21.assignment9.testcases.equals;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProgrammerDefinedCollection<E> implements Set<E> {
	protected Set<E> delegate = new HashSet<E>();

//	public default void forEach(Consumer<? super E> action) {
//		delegate.forEach(action);
//	}

	public int size() {
		return delegate.size();
	}

	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	public Iterator<E> iterator() {
		return delegate.iterator();
	}

	public Object[] toArray() {
		return delegate.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	public boolean add(E e) {
		return delegate.add(e);
	}

	public boolean remove(Object o) {
		return delegate.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		return delegate.addAll(c);
	}


	public boolean removeAll(Collection<?> c) {
		return delegate.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return delegate.retainAll(c);
	}

//	public default void replaceAll(UnaryOperator<E> operator) {
//		delegate.replaceAll(operator);
//	}
//
//	public default boolean removeIf(Predicate<? super E> filter) {
//		return delegate.removeIf(filter);
//	}
//
//	public default void sort(Comparator<? super E> c) {
//		delegate.sort(c);
//	}

	public void clear() {
		delegate.clear();
	}

	public boolean equals(Object o) {
		return delegate.equals(o);
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	
	
//	public default Stream<E> parallelStream() {
//		return delegate.parallelStream();
//	}



	@Override
	public String toString() {
		return delegate.toString();
	}
//	@Override
//	public boolean equals (Object o) {
//		return delegate.equals(o);
//	}
	
	public static Set createFilledInstance() {
		Set aList = new ProgrammerDefinedCollection<>();
		aList.add("leaf1");
		Set<String> aSet = new HashSet();
		aList.add(new HashMap());
		aSet.add("hello");
		aSet.add("goodbye");
		aList.add("leaf2");
		aList.add(aSet);
		return aList;
		
	}

//	public default Spliterator<E> spliterator() {
//		return delegate.spliterator();
//	}

}
