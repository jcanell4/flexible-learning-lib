/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elsquatrecaps.flexiblelearning.zsyncdata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 *
 * @author professor
 */
public class SyncList <V> implements List <V>{
    private List <V> list = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Spliterator<V> spliterator() {
        return list.spliterator(); 
    }

    @Override
    public void sort(Comparator<? super V> c) {
        list.sort(c); 
    }

    @Override
    public void replaceAll(UnaryOperator<V> operator) {
        list.replaceAll(operator); 
    }

    @Override
    public Stream<V> parallelStream() {
        return list.parallelStream(); 
    }

    @Override
    public Stream<V> stream() {
        return list.stream(); 
    }

    @Override
    public boolean removeIf(Predicate<? super V> filter) {
        return list.removeIf(filter); 
    }

    @Override
    public void forEach(Consumer<? super V> action) {
        list.forEach(action); 
    }

    @Override
    public int size() {
        return list.size(); 
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<V> iterator() {
       return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(V e) {
       return list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
       return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends V> c) {
       return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
       return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
       return list.retainAll(c);
    }

    @Override
    public void clear() {
       list.clear();
    }

    @Override
    public V get(int index) {
        return list.get(index);
    }

    @Override
    public V set(int index, V element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, V element) {
        list.add(index, element);
    }

    @Override
    public V remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<V> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<V> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<V> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

}
